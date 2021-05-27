package rs.ac.uns.ftn.tim5.service;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.web.HttpOp;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import rs.ac.uns.ftn.tim5.config.RDFDBConnectionProperties;
import rs.ac.uns.ftn.tim5.helper.MetadataExtractor;
import rs.ac.uns.ftn.tim5.util.SparqlUtil;

import javax.annotation.PostConstruct;
import javax.xml.transform.TransformerException;
import java.io.*;

@Service
public class RDFService {

    @Autowired
    private RDFDBConnectionProperties rdfdbConnectionProperties;

    @PostConstruct
    public void initAuth(){
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        Credentials credentials = new UsernamePasswordCredentials("admin", System.getenv("RDF_DB_PASSWORD"));
        credsProvider.setCredentials(AuthScope.ANY, credentials);
        HttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();
        HttpOp.setDefaultHttpClient(httpclient);
    }

    // From a RDFa XML file extracts RDF/XML
    public boolean save(String rdfa, String named_graph_uri) {
        String tmpFilename = "" + System.currentTimeMillis() + ".xml";
        try {
            PrintWriter p = new PrintWriter(new FileOutputStream(tmpFilename, true));
            p.println(rdfa);
            p.close();

            MetadataExtractor metadataExtractor = new MetadataExtractor();
            ByteArrayOutputStream xmlrdf = new ByteArrayOutputStream();
            System.out.println("[INFO] Extracting metadata from RDFa attributes...");
            metadataExtractor.extractMetadata(
                    new FileInputStream(new File(tmpFilename)),
                    xmlrdf);
            FileOutputStream xmlrdfOut = new FileOutputStream(new File(tmpFilename));
            xmlrdfOut.write(xmlrdf.toByteArray());
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (SAXException e) {
            e.printStackTrace();
            return false;
        } catch (TransformerException e) {
            e.printStackTrace();
            return false;
        }

        // Loading a default model with extracted metadata
        Model model = ModelFactory.createDefaultModel();
        try {
            FileInputStream xmlrdfIn = new FileInputStream(new File(tmpFilename));
            model.read(xmlrdfIn, "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream outNTRIPLES = new ByteArrayOutputStream();
        model.write(outNTRIPLES, SparqlUtil.NTRIPLES);

        System.out.println("[INFO] Extracted metadata as RDF/XML...");
        model.write(System.out, SparqlUtil.RDF_XML);

        // Writing the named graph
        System.out.println("[INFO] Populating named graph \"" + named_graph_uri + "\" with extracted metadata.");
        String sparqlUpdate = SparqlUtil.insertData(rdfdbConnectionProperties.getDataEndpoint() + named_graph_uri, new String(outNTRIPLES.toByteArray()));
        System.out.println(sparqlUpdate);

        // UpdateRequest represents a unit of execution
        UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, rdfdbConnectionProperties.getUpdateEndpoint());

        System.out.println(rdfdbConnectionProperties.getUpdateEndpoint());

        processor.execute();

        return true;
    }
}

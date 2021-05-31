package rs.ac.uns.ftn.tim5.service;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.riot.web.HttpOp;
import org.apache.jena.sparql.resultset.ResultsFormat;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import rs.ac.uns.ftn.tim5.config.RDFDBConnectionProperties;
import rs.ac.uns.ftn.tim5.helper.MetadataExtractor;
import rs.ac.uns.ftn.tim5.helper.SparqlQueryResult;
import rs.ac.uns.ftn.tim5.util.SparqlUtil;

import javax.annotation.PostConstruct;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public List<SparqlQueryResult> run(String queryString) throws IOException {

        // Create a QueryExecution that will access a SPARQL service over HTTP
        QueryExecution query = QueryExecutionFactory.sparqlService(this.rdfdbConnectionProperties.getQueryEndpoint(), queryString);
        // Query the SPARQL endpoint, iterate over the result set...
        ResultSet results = query.execSelect();

        String varName;
        RDFNode varValue;
        List<SparqlQueryResult> retval = new ArrayList<>();

        while(results.hasNext()) {

            // A single answer from a SELECT query
            QuerySolution querySolution = results.next() ;
            Iterator<String> variableBindings = querySolution.varNames();

            // Retrieve variable bindings
            while (variableBindings.hasNext()) {

                varName = variableBindings.next();
                varValue = querySolution.get(varName);

                System.out.println(varName + ": " + varValue);

                SparqlQueryResult sparqlQueryResult = new SparqlQueryResult();
                sparqlQueryResult.setVarName(varName);
                sparqlQueryResult.setVarValue(varValue);
                retval.add(sparqlQueryResult);
            }
        }
        query.close();
        return retval;
    }

    public RDFDBConnectionProperties getRdfdbConnectionProperties() {
        return rdfdbConnectionProperties;
    }

    public void setRdfdbConnectionProperties(RDFDBConnectionProperties rdfdbConnectionProperties) {
        this.rdfdbConnectionProperties = rdfdbConnectionProperties;
    }

    public void runAndExportInGivenFormat(String sparqlQuery, String outputFilePath, ResultsFormat resultsFormat) {
        // Create a QueryExecution that will access a SPARQL service over HTTP
        QueryExecution query = QueryExecutionFactory.sparqlService(this.rdfdbConnectionProperties.getQueryEndpoint(), sparqlQuery);
        // Query the SPARQL endpoint, iterate over the result set...
        ResultSet results = query.execSelect();

        OutputStream output = null;
        try {
            output = new FileOutputStream(outputFilePath);
            ResultSetFormatter.output(output, results, resultsFormat);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        query.close();
    }

    public void runAndExportInNativeFormat(String sparqlQuery, String filePath) {
        QueryExecution query = QueryExecutionFactory.sparqlService(this.rdfdbConnectionProperties.getQueryEndpoint(), sparqlQuery);
        Model model = query.execDescribe();
        try {
            model.write(new FileOutputStream(filePath), "TURTLE");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        query.close();
    }
}

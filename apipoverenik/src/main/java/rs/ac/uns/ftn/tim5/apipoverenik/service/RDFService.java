package rs.ac.uns.ftn.tim5.apipoverenik.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.transform.TransformerException;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import rs.ac.uns.ftn.tim5.apipoverenik.config.RDFDBConnectionProperties;
import rs.ac.uns.ftn.tim5.apipoverenik.helper.MetadataExtractor;
import rs.ac.uns.ftn.tim5.apipoverenik.util.SparqlUtil;

@Service
public class RDFService {
    
    @Autowired
    private RDFDBConnectionProperties rdfdbConnectionProperties;

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
        processor.execute();
        
        return true;
    }
}

package rs.ac.uns.ftn.tim5.helper;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.springframework.util.ResourceUtils;
import org.xml.sax.SAXException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * Primer demonstrira ekstrakciju RDFa metapodataka iz
 * XML dokumenta primenom GRDDL (Gleaning Resource Descriptions
 * from Dialects of Languages) transformacije.
 * <p>
 * Primer:
 * InputStream in = new FileInputStream(new File("data/rdfa/contacts.xml"));
 * OutputStream out = new FileOutputStream(filePath);
 * extractMetadata(in, out);
 */
public class MetadataExtractor {

    private TransformerFactory transformerFactory;

    private static final String XSLT_FILE = "classpath:transformations/xsl/grddl.xsl";

    public MetadataExtractor() throws SAXException, IOException {

        // Setup the XSLT transformer factory
        transformerFactory = new TransformerFactoryImpl();
    }

    /**
     * Generates RDF/XML based on RDFa metadata from an XML containing
     * input stream by applying GRDDL XSL transformation.
     *
     * @param in  XML containing input stream
     * @param out RDF/XML output stream
     */
    public void extractMetadata(InputStream in, OutputStream out) throws FileNotFoundException, TransformerException {

        // Create transformation source
        StreamSource transformSource = new StreamSource(ResourceUtils.getFile(XSLT_FILE));

        // Initialize GRDDL transformer object
        Transformer grddlTransformer = transformerFactory.newTransformer(transformSource);

        // Set the indentation properties
        grddlTransformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        grddlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // Initialize transformation subject
        StreamSource source = new StreamSource(in);

        // Initialize result stream
        StreamResult result = new StreamResult(out);

        // Trigger the transformation
        grddlTransformer.transform(source, result);

    }

}

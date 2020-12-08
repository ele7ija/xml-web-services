package dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;


public class DOMWritterResenje {
    private static String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/tim5/resenje";

    private static String XSI_NAMESPACE = "http://www.w3.org/2001/XMLSchema-instance";

    private static DocumentBuilderFactory factory;

    private static TransformerFactory transformerFactory;

    private Document document;

    /*
     * Factory initialization static-block
     */
    static {
        factory = DocumentBuilderFactory.newInstance();

        transformerFactory = TransformerFactory.newInstance();
    }

    /**
     * Generates document object model for a given XML file.
     */
    public void createDocument() {

        try {

            DocumentBuilder builder = factory.newDocumentBuilder();
            // Kreiranje novog dokumenta
            document = builder.newDocument();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates sample document object model
     * programmatically using DOM API methods.
     */
    public void generateDOM() {

        // Kreiranje i postavljanje korenskog elementa
        Element resenje = document.createElementNS(TARGET_NAMESPACE, "Resenje");
        document.appendChild(resenje);

        resenje.setAttributeNS(XSI_NAMESPACE, "xsi:schemaLocation", "http://ftn.uns.ac.rs/tim5/resenje ../xms_seme/resenje.xsd");
        resenje.setAttribute("xmlns:util", "http://ftn.uns.ac.rs/tim5/util");

        Element Broj_resenja = document.createElementNS(TARGET_NAMESPACE, "Broj_resenja");
        Broj_resenja.appendChild(document.createTextNode("071-01-4075/2018-03"));
        resenje.appendChild(Broj_resenja);

        Element datum = document.createElementNS(TARGET_NAMESPACE, "Datum");
        resenje.appendChild(datum);

        Element dan = document.createElementNS("http://ftn.uns.ac.rs/tim5/util","util:dan");
        dan.appendChild(document.createTextNode("---" + 21));
        datum.appendChild(dan);

        Element mesec = document.createElementNS("http://ftn.uns.ac.rs/tim5/util","util:mesec");
        mesec.appendChild(document.createTextNode("--0" + 5));
        datum.appendChild(mesec);

        Element godina = document.createElementNS("http://ftn.uns.ac.rs/tim5/util","util:godina");
        godina.appendChild(document.createTextNode("2018"));
        datum.appendChild(godina);

        Element poverenik = document.createElementNS(TARGET_NAMESPACE, "Poverenik");
        Element ime = document.createElementNS(TARGET_NAMESPACE, "Ime");
        ime.appendChild(document.createTextNode("Пера"));
        poverenik.appendChild(ime);

        Element prezime = document.createElementNS(TARGET_NAMESPACE, "Prezime");
        prezime.appendChild(document.createTextNode("Петровић"));
        poverenik.appendChild(prezime);

        resenje.appendChild(poverenik);

        try {
            SchemaFactory factorySchema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source schemaFile = new StreamSource(new File("xms_seme/resenje.xsd"));
            Schema schema = factorySchema.newSchema(schemaFile);

            // create a Validator instance, which can be used to validate an instance document
            Validator validator = schema.newValidator();

            // validate the DOM tree

            validator.validate(new DOMSource(document));
        } catch (SAXException | IOException e) {
            // instance document is invalid!
            System.out.println("[ERROR] " + e.getMessage() );
            System.out.print("[ERROR] Embedded exception: ");

            // Print stack trace...
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Serializes DOM tree to an arbitrary OutputStream.
     */
    public void transform(OutputStream out) {
        try {

            // Kreiranje instance objekta zaduzenog za serijalizaciju DOM modela
            Transformer transformer = transformerFactory.newTransformer();

            // Indentacija serijalizovanog izlaza
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Nad "source" objektom (DOM stablo) vrši se transformacija
            DOMSource source = new DOMSource(document);

            // Rezultujući stream (argument metode)
            StreamResult result = new StreamResult(out);

            // Poziv metode koja vrši opisanu transformaciju
            transformer.transform(source, result);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        String filePath = null;

        System.out.println("[INFO] DOM Parser");

        if (args.length != 1) {

            filePath = "xml_incijalni_primeri/resenje_gen.xml";

            System.out.println("[INFO] No input file, using default \""	+ filePath + "\"");

        } else {
            filePath = args[0];
        }

        DOMWritterResenje handler = new DOMWritterResenje();

        // Kreiranje Document čvora
        handler.createDocument();

        // Generisanje DOM stabla
        handler.generateDOM();

        // Prikaz sadržaja (isprobati sa FileOutputStream-om)
        handler.transform(System.out);


		try {
			handler.transform(new FileOutputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

    }
}


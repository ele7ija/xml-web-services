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


public class DOMWritterZahtev {
    private static String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/tim5/zahtev";
    private static String UTIL_NAMESPACE = "http://ftn.uns.ac.rs/tim5/util";

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
        Element zahtev = document.createElementNS(TARGET_NAMESPACE, "Zahtev");
        document.appendChild(zahtev);

        zahtev.setAttributeNS(XSI_NAMESPACE, "xsi:schemaLocation", "http://ftn.uns.ac.rs/tim5/zahtev ../xms_seme/Zahtev.xsd");
        zahtev.setAttribute("xmlns:util", "http://ftn.uns.ac.rs/tim5/util");

        // Trazilac
        Element trazilac = document.createElementNS(TARGET_NAMESPACE, "Trazilac");
        Element adresa = document.createElementNS(UTIL_NAMESPACE, "util:Adresa");
        Element mesto = document.createElementNS(UTIL_NAMESPACE, "util:Mesto");
        mesto.appendChild(document.createTextNode("NS"));
        adresa.appendChild(mesto);
        Element postanski_broj = document.createElementNS(UTIL_NAMESPACE, "util:Postanski_broj");
        postanski_broj.appendChild(document.createTextNode("21000"));
        adresa.appendChild(postanski_broj);
        Element ulica = document.createElementNS(UTIL_NAMESPACE, "util:Ulica");
        ulica.appendChild(document.createTextNode("Nesto"));
        adresa.appendChild(ulica);
        Element broj = document.createElementNS(UTIL_NAMESPACE, "util:Broj");
        broj.appendChild(document.createTextNode("121"));
        adresa.appendChild(broj);
        trazilac.appendChild(adresa);
        Element ime = document.createElementNS(UTIL_NAMESPACE, "util:Ime");        
        ime.appendChild(document.createTextNode("Mirko"));
        trazilac.appendChild(ime);
        Element prezime = document.createElementNS(UTIL_NAMESPACE, "util:Prezime");
        prezime.appendChild(document.createTextNode("NekoPrezime"));
        trazilac.appendChild(prezime);
        zahtev.appendChild(trazilac);

        // Organ
        Element organ = document.createElementNS(TARGET_NAMESPACE, "Organ");
        Element adresa2 = document.createElementNS(UTIL_NAMESPACE, "util:Adresa");
        Element mesto2 = document.createElementNS(UTIL_NAMESPACE, "util:Mesto");
        mesto2.appendChild(document.createTextNode("Mesto2"));
        adresa2.appendChild(mesto2);
        Element postanski_broj2 = document.createElementNS(UTIL_NAMESPACE, "util:Postanski_broj");
        postanski_broj2.appendChild(document.createTextNode("21000"));
        adresa2.appendChild(postanski_broj2);
        Element ulica2 = document.createElementNS(UTIL_NAMESPACE, "util:Ulica");
        ulica2.appendChild(document.createTextNode("NekaUlica"));
        adresa2.appendChild(ulica2);
        Element broj2 = document.createElementNS(UTIL_NAMESPACE, "util:Broj");
        broj2.appendChild(document.createTextNode("1222"));
        adresa2.appendChild(broj2);
        organ.appendChild(adresa2);
        Element naziv = document.createElementNS(UTIL_NAMESPACE, "util:Naziv");
        naziv.appendChild(document.createTextNode("NBS"));
        organ.appendChild(naziv);
        zahtev.appendChild(organ);

        // Pravni osnov
        Element pravni_osnov = document.createElementNS(TARGET_NAMESPACE, "Pravni_Osnov");
        Element naziv2 = document.createElementNS(UTIL_NAMESPACE, "util:Naziv");
        naziv2.appendChild(document.createTextNode("Zakon 123"));
        pravni_osnov.appendChild(naziv2);
        Element clan = document.createElementNS(UTIL_NAMESPACE, "util:Clan");
        clan.appendChild(document.createTextNode("15"));
        pravni_osnov.appendChild(clan);
        Element strana = document.createElementNS(UTIL_NAMESPACE, "util:Strana");
        strana.appendChild(document.createTextNode("1"));
        pravni_osnov.appendChild(strana);
        Element dopune = document.createElementNS(UTIL_NAMESPACE, "util:Dopune");        
        Element dopuna1 = document.createElementNS(UTIL_NAMESPACE, "util:Dopuna");
        Element broj3 = document.createElementNS(UTIL_NAMESPACE, "util:Broj_dopune");
        broj3.appendChild(document.createTextNode("120"));
        dopuna1.appendChild(broj3);
        Element godina3 = document.createElementNS(UTIL_NAMESPACE, "util:Godina");
        godina3.appendChild(document.createTextNode("2004"));
        dopuna1.appendChild(godina3);
        dopune.appendChild(dopuna1);
        Element dopuna2 = document.createElementNS(UTIL_NAMESPACE, "util:Dopuna");
        Element broj4 = document.createElementNS(UTIL_NAMESPACE, "util:Broj_dopune");
        broj4.appendChild(document.createTextNode("54"));
        dopuna2.appendChild(broj4);
        Element godina4 = document.createElementNS(UTIL_NAMESPACE, "util:Godina");
        godina4.appendChild(document.createTextNode("2007"));
        dopuna2.appendChild(godina4);
        dopune.appendChild(dopuna2);
        Element dopuna3 = document.createElementNS(UTIL_NAMESPACE, "util:Dopuna");
        Element broj5 = document.createElementNS(UTIL_NAMESPACE, "util:Broj_dopune");
        broj5.appendChild(document.createTextNode("104"));
        dopuna3.appendChild(broj5);
        Element godina5 = document.createElementNS(UTIL_NAMESPACE, "util:Godina");
        godina5.appendChild(document.createTextNode("2009"));
        dopuna3.appendChild(godina5);       
        dopune.appendChild(dopuna3);
        Element dopuna4 = document.createElementNS(UTIL_NAMESPACE, "util:Dopuna");
        Element broj6 = document.createElementNS(UTIL_NAMESPACE, "util:Broj_dopune");
        broj6.appendChild(document.createTextNode("36"));
        dopuna4.appendChild(broj6);
        Element godina6 = document.createElementNS(UTIL_NAMESPACE, "util:Godina");
        godina6.appendChild(document.createTextNode("2010"));
        dopuna4.appendChild(godina6);
        dopune.appendChild(dopuna4);
        pravni_osnov.appendChild(dopune);
        zahtev.appendChild(pravni_osnov);

        // Opis zahteva
        Element opis_zahteva = document.createElementNS(TARGET_NAMESPACE, "Opis_Zahteva");
        opis_zahteva.appendChild(document.createTextNode("Da li su se banke, koje su u periodu 2006, 2007 i 2008 plasirale kredite u švajcarskim francima na tržištu Srbije, \nu istom tom periodu zaduživale u švajcarskim francima, i ako jesu, \ndostavite mi podatke o tome (kog datuma, od koga, u kom iznosu, na koji vremenski rok i pod kojim kamatama)."));
        zahtev.appendChild(opis_zahteva);

        // Elementi zahteva
        Element elementi_zahteva = document.createElementNS(TARGET_NAMESPACE, "Elementi_Zahteva");
        Element element_zahteva1 = document.createElementNS(TARGET_NAMESPACE, "Element_Zahteva");
        Element tekst3 = document.createElementNS(UTIL_NAMESPACE, "util:Tekst");
        tekst3.appendChild(document.createTextNode("Uvid u dokument koji sadrzi trazenu informaciju"));
        element_zahteva1.appendChild(tekst3);
        Element element_zahteva2 = document.createElementNS(TARGET_NAMESPACE, "Element_Zahteva");
        Element tekst4 = document.createElementNS(UTIL_NAMESPACE, "util:Tekst");
        tekst4.appendChild(document.createTextNode("Uvid u kopiju dokument koji sadrzi trazenu informaciju"));
        element_zahteva2.appendChild(tekst4);
        Element metod_dostave1 = document.createElementNS(UTIL_NAMESPACE, "util:Metod_Dostave");
        metod_dostave1.appendChild(document.createTextNode("Faks"));
        element_zahteva2.appendChild(metod_dostave1);
        Element metod_dostave2 = document.createElementNS(UTIL_NAMESPACE, "util:Metod_Dostave");
        metod_dostave2.appendChild(document.createTextNode("Posta"));
        element_zahteva2.appendChild(metod_dostave2);
        elementi_zahteva.appendChild(element_zahteva2);
        zahtev.appendChild(elementi_zahteva);

        Element datum = document.createElementNS(TARGET_NAMESPACE, "Datum");
        Element dan = document.createElementNS(UTIL_NAMESPACE,"util:dan");
        dan.appendChild(document.createTextNode("---" + 21));
        datum.appendChild(dan);
        Element mesec = document.createElementNS(UTIL_NAMESPACE,"util:mesec");
        mesec.appendChild(document.createTextNode("--0" + 5));
        datum.appendChild(mesec);
        Element godina = document.createElementNS(UTIL_NAMESPACE,"util:godina");
        godina.appendChild(document.createTextNode("2018"));
        datum.appendChild(godina);
        zahtev.appendChild(datum);

        Element mesto6 = document.createElementNS(TARGET_NAMESPACE, "Mesto");
        mesto6.appendChild(document.createTextNode("Neusatz"));
        zahtev.appendChild(mesto6);
    

        try {
            SchemaFactory factorySchema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source schemaFile = new StreamSource(new File("xms_seme/Zahtev.xsd"));
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

            filePath = "xml_incijalni_primeri/zahtev_gen.xml";

            System.out.println("[INFO] No input file, using default \""	+ filePath + "\"");

        } else {
            filePath = args[0];
        }

        DOMWritterZahtev handler = new DOMWritterZahtev();

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



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

import static org.apache.xerces.jaxp.JAXPConstants.JAXP_SCHEMA_LANGUAGE;
import static org.apache.xerces.jaxp.JAXPConstants.W3C_XML_SCHEMA;

public class DOMWritterZalbaCutanja {
    private static String TARGET_NAMESPACE = "http://www.zalba_cutanja.com";

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
        Element zalba_cutanja = document.createElementNS(TARGET_NAMESPACE, "zalba_cutanja");
        document.appendChild(zalba_cutanja);

        zalba_cutanja.setAttributeNS(XSI_NAMESPACE, "xsi:schemaLocation", "http://www.zalba_cutanja.com ../xms_seme/Zalbe_cutanja.xsd");
        zalba_cutanja.setAttribute("xmlns:util", "http://ftn.uns.ac.rs/tim5/util");

        Element poverenik = document.createElementNS(TARGET_NAMESPACE, "poverenik");
        zalba_cutanja.appendChild(poverenik);

        Element naziv = document.createElementNS(TARGET_NAMESPACE, "naziv");
        naziv.appendChild(document.createTextNode("Повереникy за информације од јавног значаја и заштиту података о личности"));
        poverenik.appendChild(naziv);

        Element adresaPoverenika = document.createElementNS(TARGET_NAMESPACE, "adresa");
        poverenik.appendChild(adresaPoverenika);

        Element mesto = document.createElementNS(TARGET_NAMESPACE, "mesto");
        mesto.appendChild(document.createTextNode("Београд"));
        adresaPoverenika.appendChild(mesto);

        Element ulica = document.createElementNS(TARGET_NAMESPACE, "ulica");
        ulica.appendChild(document.createTextNode("Булевар краља Александр"));
        adresaPoverenika.appendChild(ulica);

        Element broj = document.createElementNS(TARGET_NAMESPACE, "broj");
        broj.appendChild(document.createTextNode("" + 15));
        adresaPoverenika.appendChild(broj);

        Element zakon = document.createElementNS(TARGET_NAMESPACE, "zakon");
        zalba_cutanja.appendChild(zakon);

        Element nazivZakona = document.createElementNS("http://ftn.uns.ac.rs/tim5/util","util:Naziv");
        //nazivZakona.setPrefix("util");

        nazivZakona.appendChild(document.createTextNode("Закона о слободном приступу информацијама од јавног значаја"));
        zakon.appendChild(nazivZakona);

        Element clan = document.createElementNS("http://ftn.uns.ac.rs/tim5/util","util:Clan");
        clan.appendChild(document.createTextNode("22"));
        zakon.appendChild(clan);

        Element organ = document.createElementNS(TARGET_NAMESPACE, "organ_vlasti");
        organ.appendChild(document.createTextNode("Пореска управа"));
        zalba_cutanja.appendChild(organ);

        Element razlog = document.createElementNS(TARGET_NAMESPACE, "razlog");
        razlog.appendChild(document.createTextNode("у законском року"));
        zalba_cutanja.appendChild(razlog);

        Element datum_zahteva = document.createElementNS(TARGET_NAMESPACE, "datum_zahteva");
        zalba_cutanja.appendChild(datum_zahteva);

        Element dan = document.createElementNS("http://ftn.uns.ac.rs/tim5/util","util:dan");
        dan.appendChild(document.createTextNode("---" + 21));
        datum_zahteva.appendChild(dan);

        Element mesec = document.createElementNS("http://ftn.uns.ac.rs/tim5/util","util:mesec");
        mesec.appendChild(document.createTextNode("--0" + 5));
        datum_zahteva.appendChild(mesec);

        Element godina = document.createElementNS("http://ftn.uns.ac.rs/tim5/util","util:godina");
        godina.appendChild(document.createTextNode("2018"));
        datum_zahteva.appendChild(godina);

        Element zahtevana_informacija = document.createElementNS(TARGET_NAMESPACE, "zahtevana_informacije");
        zahtevana_informacija.appendChild(document.createTextNode("Заостала камата на картицама"));
        zalba_cutanja.appendChild(zahtevana_informacija);

        Element podnosioc_zalbe = document.createElementNS(TARGET_NAMESPACE, "podnosioc_zalbe");
        zalba_cutanja.appendChild(podnosioc_zalbe);

        Element ime = document.createElementNS(TARGET_NAMESPACE, "ime");
        ime.appendChild(document.createTextNode("Пера"));
        podnosioc_zalbe.appendChild(ime);

        Element prezime = document.createElementNS(TARGET_NAMESPACE, "prezime");
        prezime.appendChild(document.createTextNode("Петровић"));
        podnosioc_zalbe.appendChild(prezime);

        Element adresaZalbenika = document.createElementNS(TARGET_NAMESPACE, "adresa");
        podnosioc_zalbe.appendChild(adresaZalbenika);

        Element mestoZalbenika = document.createElementNS(TARGET_NAMESPACE, "mesto");
        mestoZalbenika.appendChild(document.createTextNode("Нови Сад"));
        adresaZalbenika.appendChild(mestoZalbenika);

        Element ulicaZalbenika = document.createElementNS(TARGET_NAMESPACE, "ulica");
        ulicaZalbenika.appendChild(document.createTextNode("Булевар Михајла Пупина"));
        adresaZalbenika.appendChild(ulicaZalbenika);

        Element brojZalbenika = document.createElementNS(TARGET_NAMESPACE, "broj");
        brojZalbenika.appendChild(document.createTextNode("" + 11));
        adresaZalbenika.appendChild(brojZalbenika);

        /*Element drugi_podaci_za_kontakt = document.createElementNS(TARGET_NAMESPACE, "drugi_podaci_za_kontakt");
        drugi_podaci_za_kontakt.appendChild(document.createTextNode("телефон: 021/4657-235"));
        podnosioc_zalbe.appendChild(drugi_podaci_za_kontakt);*/

        Element mestoZalbe = document.createElementNS(TARGET_NAMESPACE, "mesto");
        mestoZalbe.appendChild(document.createTextNode("Нови Сад"));
        zalba_cutanja.appendChild(mestoZalbe);

        Element datum_zalbe = document.createElementNS(TARGET_NAMESPACE, "datum_zalbe");
        zalba_cutanja.appendChild(datum_zalbe);

        Element danZalbe = document.createElementNS("http://ftn.uns.ac.rs/tim5/util","util:dan");
        danZalbe.appendChild(document.createTextNode("---" + 15));
        datum_zalbe.appendChild(danZalbe);

        Element mesecZalbe = document.createElementNS("http://ftn.uns.ac.rs/tim5/util","util:mesec");
        mesecZalbe.appendChild(document.createTextNode("--0" + 8));
        datum_zalbe.appendChild(mesecZalbe);

        Element godinaZalbe = document.createElementNS("http://ftn.uns.ac.rs/tim5/util","util:godina");
        godinaZalbe.appendChild(document.createTextNode("2018"));
        datum_zalbe.appendChild(godinaZalbe);

        try {
            SchemaFactory factorySchema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source schemaFile = new StreamSource(new File("xms_seme/Zalbe_cutanja.xsd"));
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

            filePath = "xml_incijalni_primeri/zalba_cutanja3.xml";

            System.out.println("[INFO] No input file, using default \""	+ filePath + "\"");

        } else {
            filePath = args[0];
        }

        DOMWritterZalbaCutanja handler = new DOMWritterZalbaCutanja();

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

package dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

public class DOMWriterZalbaOdluka {
    private static String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/tim5/zalba_na_odluku";
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
        Element zalba_odluka = document.createElementNS(TARGET_NAMESPACE, "Zalba_na_odluku");
        document.appendChild(zalba_odluka);

        zalba_odluka.setAttributeNS(XSI_NAMESPACE, "xsi:schemaLocation", "http://ftn.uns.ac.rs/tim5/zalba_na_odluku ../xms_seme/Zalba%20na%20odluku.xsd");
        zalba_odluka.setAttribute("xmlns:util", "http://ftn.uns.ac.rs/tim5/util");

        Element poverenik = document.createElementNS(TARGET_NAMESPACE, "Poverenik");
        zalba_odluka.appendChild(poverenik);

        Element adresaPoverenika = document.createElementNS(UTIL_NAMESPACE, "util:Adresa");
        poverenik.appendChild(adresaPoverenika);

        Element mesto = document.createElementNS(UTIL_NAMESPACE, "util:Mesto");
        mesto.appendChild(document.createTextNode("Београд"));
        adresaPoverenika.appendChild(mesto);

        Element ulica = document.createElementNS(UTIL_NAMESPACE, "util:Ulica");
        ulica.appendChild(document.createTextNode("Булевар краља Александр"));
        adresaPoverenika.appendChild(ulica);

        Element broj = document.createElementNS(UTIL_NAMESPACE, "util:Broj");
        broj.appendChild(document.createTextNode("" + 15));
        adresaPoverenika.appendChild(broj);

        Element zalilac = document.createElementNS(TARGET_NAMESPACE, "Zalilac");
        zalba_odluka.appendChild(zalilac);

        Element adresaZalioca = document.createElementNS(UTIL_NAMESPACE, "util:Adresa");
        zalilac.appendChild(adresaZalioca);

        Element mestoZalioca = document.createElementNS(UTIL_NAMESPACE, "util:Mesto");
        mestoZalioca.appendChild(document.createTextNode("Нови Сад"));
        adresaZalioca.appendChild(mestoZalioca);

        Element ulicaZalioca = document.createElementNS(UTIL_NAMESPACE, "util:Ulica");
        ulicaZalioca.appendChild(document.createTextNode("Булевар Михајла Пупина"));
        adresaZalioca.appendChild(ulicaZalioca);

        Element brojZalioca = document.createElementNS(UTIL_NAMESPACE, "util:Broj");
        brojZalioca.appendChild(document.createTextNode("" + 11));
        adresaZalioca.appendChild(brojZalioca);

        Element imeZalioca = document.createElementNS(UTIL_NAMESPACE, "util:Ime");
        imeZalioca.appendChild(document.createTextNode("Пера"));
        zalilac.appendChild(imeZalioca);

        Element prezimeZalioca = document.createElementNS(UTIL_NAMESPACE, "util:Prezime");
        prezimeZalioca.appendChild(document.createTextNode("Петровић"));
        zalilac.appendChild(prezimeZalioca);

        Element organ_vlasti = document.createElementNS(TARGET_NAMESPACE, "organ_vlasti");
        organ_vlasti.appendChild(document.createTextNode("Пореска управа"));
        zalba_odluka.appendChild(organ_vlasti);

        Element odluka = document.createElementNS(TARGET_NAMESPACE, "odluka");
        zalba_odluka.appendChild(odluka);

        Element broj_odluke = document.createElementNS(TARGET_NAMESPACE, "broj_odluke");
        broj_odluke.appendChild(document.createTextNode("5"));
        odluka.appendChild(broj_odluke);

        Element godina_odluke = document.createElementNS(TARGET_NAMESPACE, "godina");
        godina_odluke.appendChild(document.createTextNode("2017"));
        odluka.appendChild(godina_odluke);

        Element datum = document.createElementNS(TARGET_NAMESPACE, "Datum");
        zalba_odluka.appendChild(datum);

        Element danZalbe = document.createElementNS("http://ftn.uns.ac.rs/tim5/util","util:dan");
        danZalbe.appendChild(document.createTextNode("---" + 15));
        datum.appendChild(danZalbe);

        Element mesecZalbe = document.createElementNS("http://ftn.uns.ac.rs/tim5/util","util:mesec");
        mesecZalbe.appendChild(document.createTextNode("--0" + 8));
        datum.appendChild(mesecZalbe);

        Element godinaZalbe = document.createElementNS("http://ftn.uns.ac.rs/tim5/util","util:godina");
        godinaZalbe.appendChild(document.createTextNode("2018"));
        datum.appendChild(godinaZalbe);

        Element osnova_za_zaljenje = document.createElementNS(TARGET_NAMESPACE, "Osnova_za_zaljenje");
        osnova_za_zaljenje.appendChild(document.createTextNode("Neka osnova"));
        zalba_odluka.appendChild(osnova_za_zaljenje);

        Element zakon = document.createElementNS(TARGET_NAMESPACE, "Zakon");
        zalba_odluka.appendChild(zakon);

        Element nazivZakona = document.createElementNS(TARGET_NAMESPACE,"Naziv");
        //nazivZakona.setPrefix("util");

        nazivZakona.appendChild(document.createTextNode("Закона о слободном приступу информацијама од јавног значаја"));
        zakon.appendChild(nazivZakona);

        Element clan = document.createElementNS(TARGET_NAMESPACE,"Clan");
        clan.appendChild(document.createTextNode("22"));
        zakon.appendChild(clan);

        Element strana = document.createElementNS(TARGET_NAMESPACE,"Strana");
        strana.appendChild(document.createTextNode("5"));
        zakon.appendChild(strana);

        Element mestoZalbe = document.createElementNS(TARGET_NAMESPACE, "Mesto");
        mestoZalbe.appendChild(document.createTextNode("Нови Сад"));
        zalba_odluka.appendChild(mestoZalbe);

        Element datum_zahteva = document.createElementNS(TARGET_NAMESPACE, "Datum_zahteva");
        zalba_odluka.appendChild(datum_zahteva);

        Element dan = document.createElementNS("http://ftn.uns.ac.rs/tim5/util","util:dan");
        dan.appendChild(document.createTextNode("---" + 21));
        datum_zahteva.appendChild(dan);

        Element mesec = document.createElementNS("http://ftn.uns.ac.rs/tim5/util","util:mesec");
        mesec.appendChild(document.createTextNode("--0" + 5));
        datum_zahteva.appendChild(mesec);

        Element godina = document.createElementNS("http://ftn.uns.ac.rs/tim5/util","util:godina");
        godina.appendChild(document.createTextNode("2018"));
        datum_zahteva.appendChild(godina);


        Element podnosioc_zalbe = document.createElementNS(TARGET_NAMESPACE, "Podnosilac_zalbe");
        zalba_odluka.appendChild(podnosioc_zalbe);

        Element obavezni_podaci = document.createElementNS(TARGET_NAMESPACE, "Obavezni_podaci");
        podnosioc_zalbe.appendChild(obavezni_podaci);

        Element adresa = document.createElementNS(UTIL_NAMESPACE, "util:Adresa");
        obavezni_podaci.appendChild(adresa);

        Element mesto2 = document.createElementNS(UTIL_NAMESPACE, "util:Mesto");
        mesto2.appendChild(document.createTextNode("Нови Сад"));
        adresa.appendChild(mesto2);

        Element ulica2 = document.createElementNS(UTIL_NAMESPACE, "util:Ulica");
        ulica2.appendChild(document.createTextNode("Булевар Михајла Пупина"));
        adresa.appendChild(ulica2);

        Element broj2 = document.createElementNS(UTIL_NAMESPACE, "util:Broj");
        broj2.appendChild(document.createTextNode("" + 11));
        adresa.appendChild(broj2);

        Element ime = document.createElementNS(UTIL_NAMESPACE, "util:Ime");
        ime.appendChild(document.createTextNode("Пера"));
        obavezni_podaci.appendChild(ime);

        Element prezime = document.createElementNS(UTIL_NAMESPACE, "util:Prezime");
        prezime.appendChild(document.createTextNode("Петровић"));
        obavezni_podaci.appendChild(prezime);



        try {
            SchemaFactory factorySchema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source schemaFile = new StreamSource(new File("xms_seme/Zalba na odluku.xsd"));
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

            filePath = "xml_incijalni_primeri/zalba_na_odluku2.xml";

            System.out.println("[INFO] No input file, using default \""	+ filePath + "\"");

        } else {
            filePath = args[0];
        }

        DOMWriterZalbaOdluka handler = new DOMWriterZalbaOdluka();

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

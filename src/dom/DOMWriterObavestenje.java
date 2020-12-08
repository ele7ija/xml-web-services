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

public class DOMWriterObavestenje {
    private static String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/tim5/obavestenje";
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
        Element obavestenje = document.createElementNS(TARGET_NAMESPACE, "Obavestenje");
        document.appendChild(obavestenje);

        obavestenje.setAttributeNS(XSI_NAMESPACE, "xsi:schemaLocation", "http://ftn.uns.ac.rs/tim5/obavestenje ../xms_seme/Obavestenje.xsd");
        obavestenje.setAttribute("xmlns:util", "http://ftn.uns.ac.rs/tim5/util");

        // Trazilac
        Element trazilac = document.createElementNS(TARGET_NAMESPACE, "Trazilac");
        Element adresa = document.createElementNS(UTIL_NAMESPACE, "util:Adresa");
        Element mesto = document.createElementNS(UTIL_NAMESPACE, "util:Mesto");
        mesto.appendChild(document.createTextNode("Novi Sad"));
        adresa.appendChild(mesto);
        Element postanski_broj = document.createElementNS(UTIL_NAMESPACE, "util:Postanski_broj");
        postanski_broj.appendChild(document.createTextNode("21000"));
        adresa.appendChild(postanski_broj);
        Element ulica = document.createElementNS(UTIL_NAMESPACE, "util:Ulica");
        ulica.appendChild(document.createTextNode("Jiricekova"));
        adresa.appendChild(ulica);
        Element broj = document.createElementNS(UTIL_NAMESPACE, "util:Broj");
        broj.appendChild(document.createTextNode("50"));
        adresa.appendChild(broj);
        trazilac.appendChild(adresa);
        Element ime = document.createElementNS(UTIL_NAMESPACE, "util:Ime");
        ime.appendChild(document.createTextNode("Mirko"));
        trazilac.appendChild(ime);
        Element prezime = document.createElementNS(UTIL_NAMESPACE, "util:Prezime");
        prezime.appendChild(document.createTextNode("Mirkovic"));
        trazilac.appendChild(prezime);
        obavestenje.appendChild(trazilac);

        // Organ
        Element organ = document.createElementNS(TARGET_NAMESPACE, "Organ");
        Element adresa2 = document.createElementNS(UTIL_NAMESPACE, "util:Adresa");
        Element mesto2 = document.createElementNS(UTIL_NAMESPACE, "util:Mesto");
        mesto2.appendChild(document.createTextNode("Novi Sad"));
        adresa2.appendChild(mesto2);
        Element postanski_broj2 = document.createElementNS(UTIL_NAMESPACE, "util:Postanski_broj");
        postanski_broj2.appendChild(document.createTextNode("21000"));
        adresa2.appendChild(postanski_broj2);
        Element ulica2 = document.createElementNS(UTIL_NAMESPACE, "util:Ulica");
        ulica2.appendChild(document.createTextNode("Jiricekova"));
        adresa2.appendChild(ulica2);
        Element broj2 = document.createElementNS(UTIL_NAMESPACE, "util:Broj");
        broj2.appendChild(document.createTextNode("51"));
        adresa2.appendChild(broj2);
        organ.appendChild(adresa2);
        Element naziv = document.createElementNS(UTIL_NAMESPACE, "util:Naziv");
        naziv.appendChild(document.createTextNode("Narodna Banka Srbije"));
        organ.appendChild(naziv);
        obavestenje.appendChild(organ);

        //Predmet
        Element predmet = document.createElementNS(TARGET_NAMESPACE, "Predmet");

        Element brojPredmeta = document.createElementNS(TARGET_NAMESPACE, "Broj_Predmeta");
        brojPredmeta.appendChild(document.createTextNode("123"));
        predmet.appendChild(brojPredmeta);

        Element datum = document.createElementNS(TARGET_NAMESPACE, "Datum");
        Element dan = document.createElementNS(UTIL_NAMESPACE,"util:dan");
        dan.appendChild(document.createTextNode("---09"));
        datum.appendChild(dan);
        Element mesec = document.createElementNS(UTIL_NAMESPACE,"util:mesec");
        mesec.appendChild(document.createTextNode("--12"));
        datum.appendChild(mesec);
        Element godina = document.createElementNS(UTIL_NAMESPACE,"util:godina");
        godina.appendChild(document.createTextNode("2020"));
        datum.appendChild(godina);
        predmet.appendChild(datum);

        Element opisTrazeneInformacije = document.createElementNS(TARGET_NAMESPACE, "Opis_Trazene_Informacije");
        opisTrazeneInformacije.appendChild(document.createTextNode("Da li su se banke, koje su u periodu 2006, 2007 i 2008 plasirale kredite u švajcarskim francima na tržištu Srbije, \nu istom tom periodu zaduživale u švajcarskim francima, i ako jesu, \ndostavite mi podatke o tome (kog datuma, od koga, u kom iznosu, na koji vremenski rok i pod kojim kamatama)."));
        predmet.appendChild(opisTrazeneInformacije);

        Element pravni_osnov = document.createElementNS(TARGET_NAMESPACE, "Pravni_Osnov");
        Element naziv2 = document.createElementNS(UTIL_NAMESPACE, "util:Naziv");
        naziv2.appendChild(document.createTextNode("Zakon o slobodnom pristupu informacijama od javnog znacaja"));
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
        predmet.appendChild(pravni_osnov);

        //prvi odgovor
        Element odgovor = document.createElementNS(TARGET_NAMESPACE, "Odgovor");

        Element odobren = document.createElementNS(UTIL_NAMESPACE, "util:Odobren");
        odobren.appendChild(document.createTextNode("true"));
        odgovor.appendChild(odobren);

        Element elementZahteva = document.createElementNS(UTIL_NAMESPACE, "util:Element_Zahteva");
        Element tekst = document.createElementNS(UTIL_NAMESPACE, "util:Tekst");
        tekst.appendChild(document.createTextNode("Uvid u dokument koji sadrzi trazenu informaciju"));
        elementZahteva.appendChild(tekst);
        odgovor.appendChild(elementZahteva);

        Element uvid = document.createElementNS(UTIL_NAMESPACE, "util:Uvid");
        Element datumUvida = document.createElementNS(UTIL_NAMESPACE, "util:Datum_Uvida");
        Element danDatumaUvida = document.createElementNS(UTIL_NAMESPACE,"util:dan");
        danDatumaUvida.appendChild(document.createTextNode("---15"));
        datumUvida.appendChild(danDatumaUvida);
        Element mesecDatumaUvida = document.createElementNS(UTIL_NAMESPACE,"util:mesec");
        mesecDatumaUvida.appendChild(document.createTextNode("--12"));
        datumUvida.appendChild(mesecDatumaUvida);
        Element godinaDatumaUvida = document.createElementNS(UTIL_NAMESPACE,"util:godina");
        godinaDatumaUvida.appendChild(document.createTextNode("2020"));
        datumUvida.appendChild(godinaDatumaUvida);
        uvid.appendChild(datumUvida);
        Element vremeUvida = document.createElementNS(UTIL_NAMESPACE, "util:Vreme_Uvida");
        Element sat = document.createElementNS(UTIL_NAMESPACE,"util:Sat");
        sat.appendChild(document.createTextNode("14"));
        vremeUvida.appendChild(sat);
        Element minut = document.createElementNS(UTIL_NAMESPACE,"util:Minut");
        minut.appendChild(document.createTextNode("05" ));
        vremeUvida.appendChild(minut);
        uvid.appendChild(vremeUvida);
        odgovor.appendChild(uvid);

        predmet.appendChild(odgovor);

        //drugi odgovor
        Element odgovor2 = document.createElementNS(TARGET_NAMESPACE, "Odgovor");

        Element odobren2 = document.createElementNS(UTIL_NAMESPACE, "util:Odobren");
        odobren2.appendChild(document.createTextNode("true"));
        odgovor2.appendChild(odobren2);

        Element elementZahteva2 = document.createElementNS(UTIL_NAMESPACE, "util:Element_Zahteva");
        Element tekst2 = document.createElementNS(UTIL_NAMESPACE, "util:Tekst");
        tekst2.appendChild(document.createTextNode("Dostavljanje kopije dokumenta koji sadrzi trazenu informaciju"));
        elementZahteva2.appendChild(tekst2);
        Element metodDostave1 = document.createElementNS(UTIL_NAMESPACE, "util:Metod_Dostave");
        metodDostave1.appendChild(document.createTextNode("Faks"));
        elementZahteva2.appendChild(metodDostave1);
        Element metodDostave2 = document.createElementNS(UTIL_NAMESPACE, "util:Metod_Dostave");
        metodDostave2.appendChild(document.createTextNode("Posta"));
        elementZahteva2.appendChild(metodDostave2);
        odgovor2.appendChild(elementZahteva2);

        Element trosak = document.createElementNS(UTIL_NAMESPACE, "util:Trosak");
        Element iznos = document.createElementNS(UTIL_NAMESPACE, "util:Iznos");
        iznos.appendChild(document.createTextNode("100"));
        trosak.appendChild(iznos);
        Element valuta = document.createElementNS(UTIL_NAMESPACE, "util:Valuta");
        valuta.appendChild(document.createTextNode("RSD"));
        trosak.appendChild(valuta);

        Element pravniOsnov2 = document.createElementNS(UTIL_NAMESPACE, "util:Pravni_Osnov");
        Element nazivPravnogOsnova2 = document.createElementNS(UTIL_NAMESPACE, "util:Naziv");
        nazivPravnogOsnova2.appendChild(document.createTextNode("Uredba o troskovima Vlade Republike Srbije"));
        pravniOsnov2.appendChild(nazivPravnogOsnova2);
        Element dopune2 = document.createElementNS(UTIL_NAMESPACE, "util:Dopune");
        Element dopuna21 = document.createElementNS(UTIL_NAMESPACE, "util:Dopuna");
        Element broj23 = document.createElementNS(UTIL_NAMESPACE, "util:Broj_dopune");
        broj23.appendChild(document.createTextNode("8"));
        dopuna21.appendChild(broj23);
        Element godina23 = document.createElementNS(UTIL_NAMESPACE, "util:Godina");
        godina23.appendChild(document.createTextNode("2006"));
        dopuna21.appendChild(godina23);
        dopune2.appendChild(dopuna21);
        pravniOsnov2.appendChild(dopune2);

        trosak.appendChild(pravniOsnov2);

        odgovor2.appendChild(trosak);

        predmet.appendChild(odgovor2);

        Element uplata = document.createElementNS(TARGET_NAMESPACE, "Uplata");
        Element racun = document.createElementNS(UTIL_NAMESPACE, "util:Racun");
        Element brojRacuna = document.createElementNS(UTIL_NAMESPACE, "util:Broj_Racuna");
        brojRacuna.appendChild(document.createTextNode("200-123456-00"));
        racun.appendChild(brojRacuna);
        uplata.appendChild(racun);
        Element iznosZaUplatu = document.createElementNS(UTIL_NAMESPACE, "util:Iznos");
        iznosZaUplatu.appendChild(document.createTextNode("100"));
        uplata.appendChild(iznosZaUplatu);
        Element pozivNaBroj = document.createElementNS(UTIL_NAMESPACE, "util:Poziv_Na_Broj");
        pozivNaBroj.appendChild(document.createTextNode("97"));
        uplata.appendChild(pozivNaBroj);

        Element pravniOsnov3 = document.createElementNS(UTIL_NAMESPACE, "util:Pravni_Osnov");
        Element nazivPravnogOsnova3 = document.createElementNS(UTIL_NAMESPACE, "util:Naziv");
        nazivPravnogOsnova3.appendChild(document.createTextNode("Pravilnik o uslovima vodjenja racuna"));
        pravniOsnov3.appendChild(nazivPravnogOsnova3);

        Element dopune3 = document.createElementNS(UTIL_NAMESPACE, "util:Dopune");

        Element dopuna31 = document.createElementNS(UTIL_NAMESPACE, "util:Dopuna");
        Element broj33 = document.createElementNS(UTIL_NAMESPACE, "util:Broj_dopune");
        broj33.appendChild(document.createTextNode("20"));
        dopuna31.appendChild(broj33);
        Element godina33 = document.createElementNS(UTIL_NAMESPACE, "util:Godina");
        godina33.appendChild(document.createTextNode("2007"));
        dopuna31.appendChild(godina33);
        dopune3.appendChild(dopuna31);

        Element dopuna32 = document.createElementNS(UTIL_NAMESPACE, "util:Dopuna");
        Element broj34 = document.createElementNS(UTIL_NAMESPACE, "util:Broj_dopune");
        broj34.appendChild(document.createTextNode("40"));
        dopuna32.appendChild(broj34);
        Element godina34 = document.createElementNS(UTIL_NAMESPACE, "util:Godina");
        godina34.appendChild(document.createTextNode("2010"));
        dopuna32.appendChild(godina34);
        dopune3.appendChild(dopuna32);

        Element dopuna33 = document.createElementNS(UTIL_NAMESPACE, "util:Dopuna");
        Element broj35 = document.createElementNS(UTIL_NAMESPACE, "util:Broj_dopune");
        broj35.appendChild(document.createTextNode("104"));
        dopuna33.appendChild(broj35);
        Element godina35 = document.createElementNS(UTIL_NAMESPACE, "util:Godina");
        godina35.appendChild(document.createTextNode("2009"));
        dopuna33.appendChild(godina35);
        dopune3.appendChild(dopuna33);

        Element dopuna34 = document.createElementNS(UTIL_NAMESPACE, "util:Dopuna");
        Element broj36 = document.createElementNS(UTIL_NAMESPACE, "util:Broj_dopune");
        broj36.appendChild(document.createTextNode("36"));
        dopuna34.appendChild(broj36);
        Element godina36 = document.createElementNS(UTIL_NAMESPACE, "util:Godina");
        godina36.appendChild(document.createTextNode("2010"));
        dopuna34.appendChild(godina36);
        dopune3.appendChild(dopuna34);

        pravniOsnov3.appendChild(dopune3);

        uplata.appendChild(pravniOsnov3);

        predmet.appendChild(uplata);

        obavestenje.appendChild(predmet);

        try {
            SchemaFactory factorySchema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source schemaFile = new StreamSource(new File("xms_seme/Obavestenje.xsd"));
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

            filePath = "xml_incijalni_primeri/obavestenje_gen.xml";

            System.out.println("[INFO] No input file, using default \""	+ filePath + "\"");

        } else {
            filePath = args[0];
        }

        DOMWriterObavestenje handler = new DOMWriterObavestenje();

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

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.27 at 03:09:41 PM CEST 
//


package rs.ac.uns.ftn.tim5.model.resenje;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import rs.ac.uns.ftn.tim5.model.util.TPravniOsnov;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.tim5.model.resenje package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _OdlukaObrazlozenje_QNAME = new QName("http://ftn.uns.ac.rs/tim5/model/resenje", "Obrazlozenje");
    private final static QName _OdlukaIshod_QNAME = new QName("http://ftn.uns.ac.rs/tim5/model/resenje", "Ishod");
    private final static QName _OdlukaObrazlozenjePravniOsnov_QNAME = new QName("http://ftn.uns.ac.rs/tim5/model/resenje", "Pravni_osnov");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.tim5.model.resenje
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Resenje }
     * 
     */
    public Resenje createResenje() {
        return new Resenje();
    }

    /**
     * Create an instance of {@link Kontekst }
     * 
     */
    public Kontekst createKontekst() {
        return new Kontekst();
    }

    /**
     * Create an instance of {@link Odluka }
     * 
     */
    public Odluka createOdluka() {
        return new Odluka();
    }

    /**
     * Create an instance of {@link KolekcijaResenja }
     * 
     */
    public KolekcijaResenja createKolekcijaResenja() {
        return new KolekcijaResenja();
    }

    /**
     * Create an instance of {@link Resenje.DatumResenja }
     * 
     */
    public Resenje.DatumResenja createResenjeDatumResenja() {
        return new Resenje.DatumResenja();
    }

    /**
     * Create an instance of {@link Kontekst.TekstKonteksta }
     * 
     */
    public Kontekst.TekstKonteksta createKontekstTekstKonteksta() {
        return new Kontekst.TekstKonteksta();
    }

    /**
     * Create an instance of {@link Kontekst.Zalba }
     * 
     */
    public Kontekst.Zalba createKontekstZalba() {
        return new Kontekst.Zalba();
    }

    /**
     * Create an instance of {@link Odluka.Obrazlozenje }
     * 
     */
    public Odluka.Obrazlozenje createOdlukaObrazlozenje() {
        return new Odluka.Obrazlozenje();
    }

    /**
     * Create an instance of {@link Resenje.Poverenik }
     * 
     */
    public Resenje.Poverenik createResenjePoverenik() {
        return new Resenje.Poverenik();
    }

    /**
     * Create an instance of {@link TOsoba }
     * 
     */
    public TOsoba createTOsoba() {
        return new TOsoba();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Odluka.Obrazlozenje }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/tim5/model/resenje", name = "Obrazlozenje", scope = Odluka.class)
    public JAXBElement<Odluka.Obrazlozenje> createOdlukaObrazlozenje(Odluka.Obrazlozenje value) {
        return new JAXBElement<Odluka.Obrazlozenje>(_OdlukaObrazlozenje_QNAME, Odluka.Obrazlozenje.class, Odluka.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TIshodOdluke }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/tim5/model/resenje", name = "Ishod", scope = Odluka.class)
    public JAXBElement<TIshodOdluke> createOdlukaIshod(TIshodOdluke value) {
        return new JAXBElement<TIshodOdluke>(_OdlukaIshod_QNAME, TIshodOdluke.class, Odluka.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TPravniOsnov }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/tim5/model/resenje", name = "Pravni_osnov", scope = Odluka.Obrazlozenje.class)
    public JAXBElement<TPravniOsnov> createOdlukaObrazlozenjePravniOsnov(TPravniOsnov value) {
        return new JAXBElement<TPravniOsnov>(_OdlukaObrazlozenjePravniOsnov_QNAME, TPravniOsnov.class, Odluka.Obrazlozenje.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TPravniOsnov }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/tim5/model/resenje", name = "Pravni_osnov", scope = Kontekst.TekstKonteksta.class)
    public JAXBElement<TPravniOsnov> createKontekstTekstKontekstaPravniOsnov(TPravniOsnov value) {
        return new JAXBElement<TPravniOsnov>(_OdlukaObrazlozenjePravniOsnov_QNAME, TPravniOsnov.class, Kontekst.TekstKonteksta.class, value);
    }

}

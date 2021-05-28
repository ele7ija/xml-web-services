//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.27 at 08:07:47 PM CEST 
//


package rs.ac.uns.ftn.tim5.model.resenje;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import rs.ac.uns.ftn.tim5.interfaces.Identifiable;
import rs.ac.uns.ftn.tim5.model.util.Datum;
import rs.ac.uns.ftn.tim5.model.util.TFizickoLice;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="datum_resenja">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://ftn.uns.ac.rs/tim5/model/util}Datum">
 *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" default="pred:datum" />
 *                 &lt;attribute name="content" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="broj_resenja" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/tim5/model/resenje}Kontekst"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/tim5/model/resenje}Odluka"/>
 *         &lt;element name="Poverenik">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://ftn.uns.ac.rs/tim5/model/util}TFizicko_Lice">
 *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" default="pred:poverenik" />
 *                 &lt;attribute name="content" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="id_zalbe" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="id_poverenika" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="about" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="vocab" type="{http://www.w3.org/2001/XMLSchema}string" fixed="http://ftn.uns.ac.rs.tim5/model/predicate" />
 *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" fixed="pred:zalba_url" />
 *       &lt;attribute name="content" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="tip_zalbe" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "datumResenja",
    "brojResenja",
    "kontekst",
    "odluka",
    "poverenik"
})
@XmlRootElement(name = "Resenje")
public class Resenje implements Identifiable {

    @XmlElement(name = "datum_resenja", required = true)
    protected Resenje.DatumResenja datumResenja;
    @XmlElement(name = "broj_resenja", required = true)
    protected Object brojResenja;
    @XmlElement(name = "Kontekst", required = true)
    protected Kontekst kontekst;
    @XmlElement(name = "Odluka", required = true)
    protected Odluka odluka;
    @XmlElement(name = "Poverenik", required = true)
    protected Resenje.Poverenik poverenik;
    @XmlAttribute(name = "id")
    protected Long id;
    @XmlAttribute(name = "id_zalbe")
    protected Long idZalbe;
    @XmlAttribute(name = "id_poverenika")
    protected Long idPoverenika;
    @XmlAttribute(name = "about")
    @XmlSchemaType(name = "anyURI")
    protected String about;
    @XmlAttribute(name = "vocab")
    protected String vocab;
    @XmlAttribute(name = "property")
    protected String property;
    @XmlAttribute(name = "content")
    protected String content;
    @XmlAttribute(name = "tip_zalbe")
    protected String tipZalbe;

    /**
     * Gets the value of the datumResenja property.
     * 
     * @return
     *     possible object is
     *     {@link Resenje.DatumResenja }
     *     
     */
    public Resenje.DatumResenja getDatumResenja() {
        return datumResenja;
    }

    /**
     * Sets the value of the datumResenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Resenje.DatumResenja }
     *     
     */
    public void setDatumResenja(Resenje.DatumResenja value) {
        this.datumResenja = value;
    }

    /**
     * Gets the value of the brojResenja property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getBrojResenja() {
        return brojResenja;
    }

    /**
     * Sets the value of the brojResenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setBrojResenja(Object value) {
        this.brojResenja = value;
    }

    /**
     * Gets the value of the kontekst property.
     * 
     * @return
     *     possible object is
     *     {@link Kontekst }
     *     
     */
    public Kontekst getKontekst() {
        return kontekst;
    }

    /**
     * Sets the value of the kontekst property.
     * 
     * @param value
     *     allowed object is
     *     {@link Kontekst }
     *     
     */
    public void setKontekst(Kontekst value) {
        this.kontekst = value;
    }

    /**
     * Gets the value of the odluka property.
     * 
     * @return
     *     possible object is
     *     {@link Odluka }
     *     
     */
    public Odluka getOdluka() {
        return odluka;
    }

    /**
     * Sets the value of the odluka property.
     * 
     * @param value
     *     allowed object is
     *     {@link Odluka }
     *     
     */
    public void setOdluka(Odluka value) {
        this.odluka = value;
    }

    /**
     * Gets the value of the poverenik property.
     * 
     * @return
     *     possible object is
     *     {@link Resenje.Poverenik }
     *     
     */
    public Resenje.Poverenik getPoverenik() {
        return poverenik;
    }

    /**
     * Sets the value of the poverenik property.
     * 
     * @param value
     *     allowed object is
     *     {@link Resenje.Poverenik }
     *     
     */
    public void setPoverenik(Resenje.Poverenik value) {
        this.poverenik = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the idZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdZalbe() {
        return idZalbe;
    }

    /**
     * Sets the value of the idZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdZalbe(Long value) {
        this.idZalbe = value;
    }

    /**
     * Gets the value of the idPoverenika property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdPoverenika() {
        return idPoverenika;
    }

    /**
     * Sets the value of the idPoverenika property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdPoverenika(Long value) {
        this.idPoverenika = value;
    }

    /**
     * Gets the value of the about property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbout() {
        return about;
    }

    /**
     * Sets the value of the about property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbout(String value) {
        this.about = value;
    }

    /**
     * Gets the value of the vocab property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVocab() {
        if (vocab == null) {
            return "http://ftn.uns.ac.rs.tim5/model/predicate";
        } else {
            return vocab;
        }
    }

    /**
     * Sets the value of the vocab property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVocab(String value) {
        this.vocab = value;
    }

    /**
     * Gets the value of the property property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProperty() {
        if (property == null) {
            return "pred:zalba_url";
        } else {
            return property;
        }
    }

    /**
     * Sets the value of the property property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProperty(String value) {
        this.property = value;
    }

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the tipZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipZalbe() {
        return tipZalbe;
    }

    /**
     * Sets the value of the tipZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipZalbe(String value) {
        this.tipZalbe = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://ftn.uns.ac.rs/tim5/model/util}Datum">
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" default="pred:datum" />
     *       &lt;attribute name="content" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class DatumResenja
        extends Datum
    {

        @XmlAttribute(name = "property")
        protected String property;
        @XmlAttribute(name = "content")
        protected String content;

        /**
         * Gets the value of the property property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProperty() {
            if (property == null) {
                return "pred:datum";
            } else {
                return property;
            }
        }

        /**
         * Sets the value of the property property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProperty(String value) {
            this.property = value;
        }

        /**
         * Gets the value of the content property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getContent() {
            return content;
        }

        /**
         * Sets the value of the content property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setContent(String value) {
            this.content = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://ftn.uns.ac.rs/tim5/model/util}TFizicko_Lice">
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" default="pred:poverenik" />
     *       &lt;attribute name="content" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Poverenik
        extends TFizickoLice
    {

        @XmlAttribute(name = "property")
        protected String property;
        @XmlAttribute(name = "content")
        protected String content;

        /**
         * Gets the value of the property property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProperty() {
            if (property == null) {
                return "pred:poverenik";
            } else {
                return property;
            }
        }

        /**
         * Sets the value of the property property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProperty(String value) {
            this.property = value;
        }

        /**
         * Gets the value of the content property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getContent() {
            return content;
        }

        /**
         * Sets the value of the content property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setContent(String value) {
            this.content = value;
        }

    }

}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.27 at 08:04:17 PM CEST 
//


package rs.ac.uns.ftn.tim5.model.zahtev;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import rs.ac.uns.ftn.tim5.interfaces.Identifiable;
import rs.ac.uns.ftn.tim5.model.util.TElementZahteva;
import rs.ac.uns.ftn.tim5.model.util.TFizickoLice;
import rs.ac.uns.ftn.tim5.model.util.TPravniOsnov;
import rs.ac.uns.ftn.tim5.model.util.TPravnoLice;


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
 *         &lt;element name="trazilac">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://ftn.uns.ac.rs/tim5/model/util}TFizicko_Lice">
 *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" fixed="pred:email_trazioca" />
 *                 &lt;attribute name="content" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="organ">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://ftn.uns.ac.rs/tim5/model/util}TPravno_Lice">
 *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" fixed="pred:naziv_organa_vlasti" />
 *                 &lt;attribute name="content" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="pravni_osnov" type="{http://ftn.uns.ac.rs/tim5/model/util}TPravni_Osnov" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="opis_zahteva" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="elementi_zahteva">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Element_Zahteva" type="{http://ftn.uns.ac.rs/tim5/model/util}TElement_Zahteva" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="datum">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://ftn.uns.ac.rs/tim5/model/util}Datum">
 *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" fixed="pred:datum" />
 *                 &lt;attribute name="content" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="mesto">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" fixed="pred:mesto" />
 *                 &lt;attribute name="content" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="about" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="vocab" type="{http://www.w3.org/2001/XMLSchema}string" fixed="http://ftn.uns.ac.rs.tim5/model/predicate" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "trazilac",
    "organ",
    "pravniOsnov",
    "opisZahteva",
    "elementiZahteva",
    "datum",
    "mesto"
})
@XmlRootElement(name = "Zahtev")
public class Zahtev implements Identifiable {

    @XmlElement(required = true)
    protected Zahtev.Trazilac trazilac;
    @XmlElement(required = true)
    protected Zahtev.Organ organ;
    @XmlElement(name = "pravni_osnov")
    protected List<TPravniOsnov> pravniOsnov;
    @XmlElement(name = "opis_zahteva", required = true)
    protected String opisZahteva;
    @XmlElement(name = "elementi_zahteva", required = true)
    protected Zahtev.ElementiZahteva elementiZahteva;
    @XmlElement(required = true)
    protected Zahtev.Datum datum;
    @XmlElement(required = true)
    protected Zahtev.Mesto mesto;
    @XmlAttribute(name = "id")
    protected Long id;
    @XmlAttribute(name = "about")
    @XmlSchemaType(name = "anyURI")
    protected String about;
    @XmlAttribute(name = "vocab")
    protected String vocab;

    /**
     * Gets the value of the trazilac property.
     * 
     * @return
     *     possible object is
     *     {@link Zahtev.Trazilac }
     *     
     */
    public Zahtev.Trazilac getTrazilac() {
        return trazilac;
    }

    /**
     * Sets the value of the trazilac property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zahtev.Trazilac }
     *     
     */
    public void setTrazilac(Zahtev.Trazilac value) {
        this.trazilac = value;
    }

    /**
     * Gets the value of the organ property.
     * 
     * @return
     *     possible object is
     *     {@link Zahtev.Organ }
     *     
     */
    public Zahtev.Organ getOrgan() {
        return organ;
    }

    /**
     * Sets the value of the organ property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zahtev.Organ }
     *     
     */
    public void setOrgan(Zahtev.Organ value) {
        this.organ = value;
    }

    /**
     * Gets the value of the pravniOsnov property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pravniOsnov property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPravniOsnov().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TPravniOsnov }
     * 
     * 
     */
    public List<TPravniOsnov> getPravniOsnov() {
        if (pravniOsnov == null) {
            pravniOsnov = new ArrayList<TPravniOsnov>();
        }
        return this.pravniOsnov;
    }

    /**
     * Gets the value of the opisZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpisZahteva() {
        return opisZahteva;
    }

    /**
     * Sets the value of the opisZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpisZahteva(String value) {
        this.opisZahteva = value;
    }

    /**
     * Gets the value of the elementiZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link Zahtev.ElementiZahteva }
     *     
     */
    public Zahtev.ElementiZahteva getElementiZahteva() {
        return elementiZahteva;
    }

    /**
     * Sets the value of the elementiZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zahtev.ElementiZahteva }
     *     
     */
    public void setElementiZahteva(Zahtev.ElementiZahteva value) {
        this.elementiZahteva = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link Zahtev.Datum }
     *     
     */
    public Zahtev.Datum getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zahtev.Datum }
     *     
     */
    public void setDatum(Zahtev.Datum value) {
        this.datum = value;
    }

    /**
     * Gets the value of the mesto property.
     * 
     * @return
     *     possible object is
     *     {@link Zahtev.Mesto }
     *     
     */
    public Zahtev.Mesto getMesto() {
        return mesto;
    }

    /**
     * Sets the value of the mesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zahtev.Mesto }
     *     
     */
    public void setMesto(Zahtev.Mesto value) {
        this.mesto = value;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://ftn.uns.ac.rs/tim5/model/util}Datum">
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" fixed="pred:datum" />
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
    public static class Datum
        extends rs.ac.uns.ftn.tim5.model.util.Datum
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
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Element_Zahteva" type="{http://ftn.uns.ac.rs/tim5/model/util}TElement_Zahteva" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "elementZahteva"
    })
    public static class ElementiZahteva {

        @XmlElement(name = "Element_Zahteva", required = true)
        protected List<TElementZahteva> elementZahteva;

        /**
         * Gets the value of the elementZahteva property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the elementZahteva property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getElementZahteva().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TElementZahteva }
         * 
         * 
         */
        public List<TElementZahteva> getElementZahteva() {
            if (elementZahteva == null) {
                elementZahteva = new ArrayList<TElementZahteva>();
            }
            return this.elementZahteva;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" fixed="pred:mesto" />
     *       &lt;attribute name="content" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Mesto {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "property")
        protected String property;
        @XmlAttribute(name = "content")
        protected String content;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
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
                return "pred:mesto";
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
     *     &lt;extension base="{http://ftn.uns.ac.rs/tim5/model/util}TPravno_Lice">
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" fixed="pred:naziv_organa_vlasti" />
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
    public static class Organ
        extends TPravnoLice
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
                return "pred:naziv_organa_vlasti";
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
     *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" fixed="pred:email_trazioca" />
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
    public static class Trazilac
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
                return "pred:email_trazioca";
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

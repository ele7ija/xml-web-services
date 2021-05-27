//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.27 at 03:09:41 PM CEST 
//


package rs.ac.uns.ftn.tim5.model.obavestenje;

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
import rs.ac.uns.ftn.tim5.model.util.TFizickoLice;
import rs.ac.uns.ftn.tim5.model.util.TOdgovorNaZahtev;
import rs.ac.uns.ftn.tim5.model.util.TPravniOsnov;
import rs.ac.uns.ftn.tim5.model.util.TPravnoLice;
import rs.ac.uns.ftn.tim5.model.util.TUplata;


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
 *         &lt;element name="odbijen">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
 *                 &lt;attribute name="predicate" type="{http://www.w3.org/2001/XMLSchema}string" default="pred:odbijen" />
 *                 &lt;attribute name="content" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="istekao">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
 *                 &lt;attribute name="predicate" type="{http://www.w3.org/2001/XMLSchema}string" default="pred:istekao" />
 *                 &lt;attribute name="content" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Trazilac">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://ftn.uns.ac.rs/tim5/model/util}TFizicko_Lice">
 *                 &lt;attribute name="predicate" type="{http://www.w3.org/2001/XMLSchema}string" default="pred:email_trazioca" />
 *                 &lt;attribute name="content" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Organ">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://ftn.uns.ac.rs/tim5/model/util}TPravno_Lice">
 *                 &lt;attribute name="predicate" type="{http://www.w3.org/2001/XMLSchema}string" default="pred:naziv_organa_vlasti" />
 *                 &lt;attribute name="content" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Predmet">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Broj_Predmeta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="Datum">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;extension base="{http://ftn.uns.ac.rs/tim5/model/util}Datum">
 *                           &lt;attribute name="predicate" type="{http://www.w3.org/2001/XMLSchema}string" default="pred:datum" />
 *                           &lt;attribute name="content" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Opis_Trazene_Informacije" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Pravni_Osnov" type="{http://ftn.uns.ac.rs/tim5/model/util}TPravni_Osnov" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="Odgovor" type="{http://ftn.uns.ac.rs/tim5/model/util}TOdgovor_Na_Zahtev" maxOccurs="unbounded"/>
 *                   &lt;element name="Uplata" type="{http://ftn.uns.ac.rs/tim5/model/util}TUplata" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="id_zahteva" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="about" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="vocab" type="{http://www.w3.org/2001/XMLSchema}string" fixed="http://ftn.uns.ac.rs.tim5/model/predicate" />
 *       &lt;attribute name="property" type="{http://www.w3.org/2001/XMLSchema}string" fixed="pred:zahtev_url" />
 *       &lt;attribute name="content" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "odbijen",
    "istekao",
    "trazilac",
    "organ",
    "predmet"
})
@XmlRootElement(name = "Obavestenje")
public class Obavestenje implements Identifiable {

    @XmlElement(required = true)
    protected Obavestenje.Odbijen odbijen;
    @XmlElement(required = true)
    protected Obavestenje.Istekao istekao;
    @XmlElement(name = "Trazilac", required = true)
    protected Obavestenje.Trazilac trazilac;
    @XmlElement(name = "Organ", required = true)
    protected Obavestenje.Organ organ;
    @XmlElement(name = "Predmet", required = true)
    protected Obavestenje.Predmet predmet;
    @XmlAttribute(name = "id")
    protected Long id;
    @XmlAttribute(name = "id_zahteva")
    protected Long idZahteva;
    @XmlAttribute(name = "about")
    @XmlSchemaType(name = "anyURI")
    protected String about;
    @XmlAttribute(name = "vocab")
    protected String vocab;
    @XmlAttribute(name = "property")
    protected String property;
    @XmlAttribute(name = "content")
    protected String content;

    /**
     * Gets the value of the odbijen property.
     * 
     * @return
     *     possible object is
     *     {@link Obavestenje.Odbijen }
     *     
     */
    public Obavestenje.Odbijen getOdbijen() {
        return odbijen;
    }

    /**
     * Sets the value of the odbijen property.
     * 
     * @param value
     *     allowed object is
     *     {@link Obavestenje.Odbijen }
     *     
     */
    public void setOdbijen(Obavestenje.Odbijen value) {
        this.odbijen = value;
    }

    /**
     * Gets the value of the istekao property.
     * 
     * @return
     *     possible object is
     *     {@link Obavestenje.Istekao }
     *     
     */
    public Obavestenje.Istekao getIstekao() {
        return istekao;
    }

    /**
     * Sets the value of the istekao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Obavestenje.Istekao }
     *     
     */
    public void setIstekao(Obavestenje.Istekao value) {
        this.istekao = value;
    }

    /**
     * Gets the value of the trazilac property.
     * 
     * @return
     *     possible object is
     *     {@link Obavestenje.Trazilac }
     *     
     */
    public Obavestenje.Trazilac getTrazilac() {
        return trazilac;
    }

    /**
     * Sets the value of the trazilac property.
     * 
     * @param value
     *     allowed object is
     *     {@link Obavestenje.Trazilac }
     *     
     */
    public void setTrazilac(Obavestenje.Trazilac value) {
        this.trazilac = value;
    }

    /**
     * Gets the value of the organ property.
     * 
     * @return
     *     possible object is
     *     {@link Obavestenje.Organ }
     *     
     */
    public Obavestenje.Organ getOrgan() {
        return organ;
    }

    /**
     * Sets the value of the organ property.
     * 
     * @param value
     *     allowed object is
     *     {@link Obavestenje.Organ }
     *     
     */
    public void setOrgan(Obavestenje.Organ value) {
        this.organ = value;
    }

    /**
     * Gets the value of the predmet property.
     * 
     * @return
     *     possible object is
     *     {@link Obavestenje.Predmet }
     *     
     */
    public Obavestenje.Predmet getPredmet() {
        return predmet;
    }

    /**
     * Sets the value of the predmet property.
     * 
     * @param value
     *     allowed object is
     *     {@link Obavestenje.Predmet }
     *     
     */
    public void setPredmet(Obavestenje.Predmet value) {
        this.predmet = value;
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
     * Gets the value of the idZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdZahteva() {
        return idZahteva;
    }

    /**
     * Sets the value of the idZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdZahteva(Long value) {
        this.idZahteva = value;
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
            return "pred:zahtev_url";
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
     *       &lt;attribute name="predicate" type="{http://www.w3.org/2001/XMLSchema}string" default="pred:istekao" />
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
    public static class Istekao {

        @XmlValue
        protected boolean value;
        @XmlAttribute(name = "predicate")
        protected String predicate;
        @XmlAttribute(name = "content")
        protected String content;

        /**
         * Gets the value of the value property.
         * 
         */
        public boolean isValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         */
        public void setValue(boolean value) {
            this.value = value;
        }

        /**
         * Gets the value of the predicate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPredicate() {
            if (predicate == null) {
                return "pred:istekao";
            } else {
                return predicate;
            }
        }

        /**
         * Sets the value of the predicate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPredicate(String value) {
            this.predicate = value;
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
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>boolean">
     *       &lt;attribute name="predicate" type="{http://www.w3.org/2001/XMLSchema}string" default="pred:odbijen" />
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
    public static class Odbijen {

        @XmlValue
        protected boolean value;
        @XmlAttribute(name = "predicate")
        protected String predicate;
        @XmlAttribute(name = "content")
        protected String content;

        /**
         * Gets the value of the value property.
         * 
         */
        public boolean isValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         */
        public void setValue(boolean value) {
            this.value = value;
        }

        /**
         * Gets the value of the predicate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPredicate() {
            if (predicate == null) {
                return "pred:odbijen";
            } else {
                return predicate;
            }
        }

        /**
         * Sets the value of the predicate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPredicate(String value) {
            this.predicate = value;
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
     *       &lt;attribute name="predicate" type="{http://www.w3.org/2001/XMLSchema}string" default="pred:naziv_organa_vlasti" />
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

        @XmlAttribute(name = "predicate")
        protected String predicate;
        @XmlAttribute(name = "content")
        protected String content;

        /**
         * Gets the value of the predicate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPredicate() {
            if (predicate == null) {
                return "pred:naziv_organa_vlasti";
            } else {
                return predicate;
            }
        }

        /**
         * Sets the value of the predicate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPredicate(String value) {
            this.predicate = value;
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
     *         &lt;element name="Broj_Predmeta" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="Datum">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;extension base="{http://ftn.uns.ac.rs/tim5/model/util}Datum">
     *                 &lt;attribute name="predicate" type="{http://www.w3.org/2001/XMLSchema}string" default="pred:datum" />
     *                 &lt;attribute name="content" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/extension>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Opis_Trazene_Informacije" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Pravni_Osnov" type="{http://ftn.uns.ac.rs/tim5/model/util}TPravni_Osnov" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="Odgovor" type="{http://ftn.uns.ac.rs/tim5/model/util}TOdgovor_Na_Zahtev" maxOccurs="unbounded"/>
     *         &lt;element name="Uplata" type="{http://ftn.uns.ac.rs/tim5/model/util}TUplata" minOccurs="0"/>
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
        "brojPredmeta",
        "datum",
        "opisTrazeneInformacije",
        "pravniOsnov",
        "odgovor",
        "uplata"
    })
    public static class Predmet {

        @XmlElement(name = "Broj_Predmeta")
        protected int brojPredmeta;
        @XmlElement(name = "Datum", required = true)
        protected Obavestenje.Predmet.Datum datum;
        @XmlElement(name = "Opis_Trazene_Informacije", required = true)
        protected String opisTrazeneInformacije;
        @XmlElement(name = "Pravni_Osnov")
        protected List<TPravniOsnov> pravniOsnov;
        @XmlElement(name = "Odgovor", required = true)
        protected List<TOdgovorNaZahtev> odgovor;
        @XmlElement(name = "Uplata")
        protected TUplata uplata;

        /**
         * Gets the value of the brojPredmeta property.
         * 
         */
        public int getBrojPredmeta() {
            return brojPredmeta;
        }

        /**
         * Sets the value of the brojPredmeta property.
         * 
         */
        public void setBrojPredmeta(int value) {
            this.brojPredmeta = value;
        }

        /**
         * Gets the value of the datum property.
         * 
         * @return
         *     possible object is
         *     {@link Obavestenje.Predmet.Datum }
         *     
         */
        public Obavestenje.Predmet.Datum getDatum() {
            return datum;
        }

        /**
         * Sets the value of the datum property.
         * 
         * @param value
         *     allowed object is
         *     {@link Obavestenje.Predmet.Datum }
         *     
         */
        public void setDatum(Obavestenje.Predmet.Datum value) {
            this.datum = value;
        }

        /**
         * Gets the value of the opisTrazeneInformacije property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOpisTrazeneInformacije() {
            return opisTrazeneInformacije;
        }

        /**
         * Sets the value of the opisTrazeneInformacije property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOpisTrazeneInformacije(String value) {
            this.opisTrazeneInformacije = value;
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
         * Gets the value of the odgovor property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the odgovor property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOdgovor().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TOdgovorNaZahtev }
         * 
         * 
         */
        public List<TOdgovorNaZahtev> getOdgovor() {
            if (odgovor == null) {
                odgovor = new ArrayList<TOdgovorNaZahtev>();
            }
            return this.odgovor;
        }

        /**
         * Gets the value of the uplata property.
         * 
         * @return
         *     possible object is
         *     {@link TUplata }
         *     
         */
        public TUplata getUplata() {
            return uplata;
        }

        /**
         * Sets the value of the uplata property.
         * 
         * @param value
         *     allowed object is
         *     {@link TUplata }
         *     
         */
        public void setUplata(TUplata value) {
            this.uplata = value;
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
         *       &lt;attribute name="predicate" type="{http://www.w3.org/2001/XMLSchema}string" default="pred:datum" />
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

            @XmlAttribute(name = "predicate")
            protected String predicate;
            @XmlAttribute(name = "content")
            protected String content;

            /**
             * Gets the value of the predicate property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPredicate() {
                if (predicate == null) {
                    return "pred:datum";
                } else {
                    return predicate;
                }
            }

            /**
             * Sets the value of the predicate property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPredicate(String value) {
                this.predicate = value;
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


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://ftn.uns.ac.rs/tim5/model/util}TFizicko_Lice">
     *       &lt;attribute name="predicate" type="{http://www.w3.org/2001/XMLSchema}string" default="pred:email_trazioca" />
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

        @XmlAttribute(name = "predicate")
        protected String predicate;
        @XmlAttribute(name = "content")
        protected String content;

        /**
         * Gets the value of the predicate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPredicate() {
            if (predicate == null) {
                return "pred:email_trazioca";
            } else {
                return predicate;
            }
        }

        /**
         * Sets the value of the predicate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPredicate(String value) {
            this.predicate = value;
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

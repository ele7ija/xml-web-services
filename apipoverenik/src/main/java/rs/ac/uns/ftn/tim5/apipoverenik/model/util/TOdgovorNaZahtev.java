//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.08 at 03:23:35 PM CET 
//


package rs.ac.uns.ftn.tim5.apipoverenik.model.util;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TOdgovor_Na_Zahtev complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TOdgovor_Na_Zahtev">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Odobren" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Element_Zahteva" type="{http://ftn.uns.ac.rs/tim5/apipoverenik/model/util}TElement_Zahteva"/>
 *         &lt;element name="Uvid" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Datum_Uvida" type="{http://ftn.uns.ac.rs/tim5/apipoverenik/model/util}Datum"/>
 *                   &lt;element name="Vreme_Uvida" type="{http://ftn.uns.ac.rs/tim5/apipoverenik/model/util}TVreme"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Trosak" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Iznos" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *                   &lt;element name="Valuta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Pravni_Osnov" type="{http://ftn.uns.ac.rs/tim5/apipoverenik/model/util}TPravni_Osnov" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TOdgovor_Na_Zahtev", propOrder = {
    "odobren",
    "elementZahteva",
    "uvid",
    "trosak"
})
public class TOdgovorNaZahtev {

    @XmlElement(name = "Odobren")
    protected boolean odobren;
    @XmlElement(name = "Element_Zahteva", required = true)
    protected TElementZahteva elementZahteva;
    @XmlElement(name = "Uvid")
    protected TOdgovorNaZahtev.Uvid uvid;
    @XmlElement(name = "Trosak")
    protected TOdgovorNaZahtev.Trosak trosak;

    /**
     * Gets the value of the odobren property.
     * 
     */
    public boolean isOdobren() {
        return odobren;
    }

    /**
     * Sets the value of the odobren property.
     * 
     */
    public void setOdobren(boolean value) {
        this.odobren = value;
    }

    /**
     * Gets the value of the elementZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link TElementZahteva }
     *     
     */
    public TElementZahteva getElementZahteva() {
        return elementZahteva;
    }

    /**
     * Sets the value of the elementZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link TElementZahteva }
     *     
     */
    public void setElementZahteva(TElementZahteva value) {
        this.elementZahteva = value;
    }

    /**
     * Gets the value of the uvid property.
     * 
     * @return
     *     possible object is
     *     {@link TOdgovorNaZahtev.Uvid }
     *     
     */
    public TOdgovorNaZahtev.Uvid getUvid() {
        return uvid;
    }

    /**
     * Sets the value of the uvid property.
     * 
     * @param value
     *     allowed object is
     *     {@link TOdgovorNaZahtev.Uvid }
     *     
     */
    public void setUvid(TOdgovorNaZahtev.Uvid value) {
        this.uvid = value;
    }

    /**
     * Gets the value of the trosak property.
     * 
     * @return
     *     possible object is
     *     {@link TOdgovorNaZahtev.Trosak }
     *     
     */
    public TOdgovorNaZahtev.Trosak getTrosak() {
        return trosak;
    }

    /**
     * Sets the value of the trosak property.
     * 
     * @param value
     *     allowed object is
     *     {@link TOdgovorNaZahtev.Trosak }
     *     
     */
    public void setTrosak(TOdgovorNaZahtev.Trosak value) {
        this.trosak = value;
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
     *         &lt;element name="Iznos" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
     *         &lt;element name="Valuta" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Pravni_Osnov" type="{http://ftn.uns.ac.rs/tim5/apipoverenik/model/util}TPravni_Osnov" minOccurs="0"/>
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
        "iznos",
        "valuta",
        "pravniOsnov"
    })
    public static class Trosak {

        @XmlElement(name = "Iznos", required = true)
        @XmlSchemaType(name = "nonNegativeInteger")
        protected BigInteger iznos;
        @XmlElement(name = "Valuta", required = true)
        protected String valuta;
        @XmlElement(name = "Pravni_Osnov")
        protected TPravniOsnov pravniOsnov;

        /**
         * Gets the value of the iznos property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getIznos() {
            return iznos;
        }

        /**
         * Sets the value of the iznos property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setIznos(BigInteger value) {
            this.iznos = value;
        }

        /**
         * Gets the value of the valuta property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValuta() {
            return valuta;
        }

        /**
         * Sets the value of the valuta property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValuta(String value) {
            this.valuta = value;
        }

        /**
         * Gets the value of the pravniOsnov property.
         * 
         * @return
         *     possible object is
         *     {@link TPravniOsnov }
         *     
         */
        public TPravniOsnov getPravniOsnov() {
            return pravniOsnov;
        }

        /**
         * Sets the value of the pravniOsnov property.
         * 
         * @param value
         *     allowed object is
         *     {@link TPravniOsnov }
         *     
         */
        public void setPravniOsnov(TPravniOsnov value) {
            this.pravniOsnov = value;
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
     *         &lt;element name="Datum_Uvida" type="{http://ftn.uns.ac.rs/tim5/apipoverenik/model/util}Datum"/>
     *         &lt;element name="Vreme_Uvida" type="{http://ftn.uns.ac.rs/tim5/apipoverenik/model/util}TVreme"/>
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
        "datumUvida",
        "vremeUvida"
    })
    public static class Uvid {

        @XmlElement(name = "Datum_Uvida", required = true)
        protected Datum datumUvida;
        @XmlElement(name = "Vreme_Uvida", required = true)
        protected TVreme vremeUvida;

        /**
         * Gets the value of the datumUvida property.
         * 
         * @return
         *     possible object is
         *     {@link Datum }
         *     
         */
        public Datum getDatumUvida() {
            return datumUvida;
        }

        /**
         * Sets the value of the datumUvida property.
         * 
         * @param value
         *     allowed object is
         *     {@link Datum }
         *     
         */
        public void setDatumUvida(Datum value) {
            this.datumUvida = value;
        }

        /**
         * Gets the value of the vremeUvida property.
         * 
         * @return
         *     possible object is
         *     {@link TVreme }
         *     
         */
        public TVreme getVremeUvida() {
            return vremeUvida;
        }

        /**
         * Sets the value of the vremeUvida property.
         * 
         * @param value
         *     allowed object is
         *     {@link TVreme }
         *     
         */
        public void setVremeUvida(TVreme value) {
            this.vremeUvida = value;
        }

    }

}

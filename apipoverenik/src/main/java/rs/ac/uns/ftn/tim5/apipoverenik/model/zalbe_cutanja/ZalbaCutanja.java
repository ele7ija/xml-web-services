//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.09 at 12:15:06 PM CET 
//


package rs.ac.uns.ftn.tim5.apipoverenik.model.zalbe_cutanja;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import rs.ac.uns.ftn.tim5.apipoverenik.interfaces.Identifiable;
import rs.ac.uns.ftn.tim5.apipoverenik.model.util.Adresa;
import rs.ac.uns.ftn.tim5.apipoverenik.model.util.Datum;
import rs.ac.uns.ftn.tim5.apipoverenik.model.util.Razlog;
import rs.ac.uns.ftn.tim5.apipoverenik.model.util.TPravniOsnov;


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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="poverenik">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="naziv" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="adresa" type="{http://ftn.uns.ac.rs/tim5/apipoverenik/model/util}Adresa"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="zakon" type="{http://ftn.uns.ac.rs/tim5/apipoverenik/model/util}TPravni_Osnov"/>
 *         &lt;element name="organ_vlasti" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="razlog" type="{http://ftn.uns.ac.rs/tim5/apipoverenik/model/util}Razlog"/>
 *         &lt;element name="datum_zahteva" type="{http://ftn.uns.ac.rs/tim5/apipoverenik/model/util}Datum"/>
 *         &lt;element name="zahtevana_informacije" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="podnosioc_zalbe">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="prezime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="adresa" type="{http://ftn.uns.ac.rs/tim5/apipoverenik/model/util}Adresa"/>
 *                   &lt;element name="drugi_podaci_za_kontakt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="mesto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="datum_zalbe" type="{http://ftn.uns.ac.rs/tim5/apipoverenik/model/util}Datum"/>
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
    "id",
    "poverenik",
    "zakon",
    "organVlasti",
    "razlog",
    "datumZahteva",
    "zahtevanaInformacije",
    "podnosiocZalbe",
    "mesto",
    "datumZalbe"
})
@XmlRootElement(name = "zalba_cutanja")
public class ZalbaCutanja implements Identifiable {

    protected long id;
    @XmlElement(required = true)
    protected ZalbaCutanja.Poverenik poverenik;
    @XmlElement(required = true)
    protected TPravniOsnov zakon;
    @XmlElement(name = "organ_vlasti", required = true)
    protected String organVlasti;
    @XmlElement(required = true, defaultValue = "nije postupio")
    protected Razlog razlog;
    @XmlElement(name = "datum_zahteva", required = true)
    protected Datum datumZahteva;
    @XmlElement(name = "zahtevana_informacije", required = true)
    protected String zahtevanaInformacije;
    @XmlElement(name = "podnosioc_zalbe", required = true)
    protected ZalbaCutanja.PodnosiocZalbe podnosiocZalbe;
    @XmlElement(required = true)
    protected String mesto;
    @XmlElement(name = "datum_zalbe", required = true)
    protected Datum datumZalbe;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the poverenik property.
     * 
     * @return
     *     possible object is
     *     {@link ZalbaCutanja.Poverenik }
     *     
     */
    public ZalbaCutanja.Poverenik getPoverenik() {
        return poverenik;
    }

    /**
     * Sets the value of the poverenik property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZalbaCutanja.Poverenik }
     *     
     */
    public void setPoverenik(ZalbaCutanja.Poverenik value) {
        this.poverenik = value;
    }

    /**
     * Gets the value of the zakon property.
     * 
     * @return
     *     possible object is
     *     {@link TPravniOsnov }
     *     
     */
    public TPravniOsnov getZakon() {
        return zakon;
    }

    /**
     * Sets the value of the zakon property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPravniOsnov }
     *     
     */
    public void setZakon(TPravniOsnov value) {
        this.zakon = value;
    }

    /**
     * Gets the value of the organVlasti property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganVlasti() {
        return organVlasti;
    }

    /**
     * Sets the value of the organVlasti property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganVlasti(String value) {
        this.organVlasti = value;
    }

    /**
     * Gets the value of the razlog property.
     * 
     * @return
     *     possible object is
     *     {@link Razlog }
     *     
     */
    public Razlog getRazlog() {
        return razlog;
    }

    /**
     * Sets the value of the razlog property.
     * 
     * @param value
     *     allowed object is
     *     {@link Razlog }
     *     
     */
    public void setRazlog(Razlog value) {
        this.razlog = value;
    }

    /**
     * Gets the value of the datumZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link Datum }
     *     
     */
    public Datum getDatumZahteva() {
        return datumZahteva;
    }

    /**
     * Sets the value of the datumZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link Datum }
     *     
     */
    public void setDatumZahteva(Datum value) {
        this.datumZahteva = value;
    }

    /**
     * Gets the value of the zahtevanaInformacije property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZahtevanaInformacije() {
        return zahtevanaInformacije;
    }

    /**
     * Sets the value of the zahtevanaInformacije property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZahtevanaInformacije(String value) {
        this.zahtevanaInformacije = value;
    }

    /**
     * Gets the value of the podnosiocZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link ZalbaCutanja.PodnosiocZalbe }
     *     
     */
    public ZalbaCutanja.PodnosiocZalbe getPodnosiocZalbe() {
        return podnosiocZalbe;
    }

    /**
     * Sets the value of the podnosiocZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZalbaCutanja.PodnosiocZalbe }
     *     
     */
    public void setPodnosiocZalbe(ZalbaCutanja.PodnosiocZalbe value) {
        this.podnosiocZalbe = value;
    }

    /**
     * Gets the value of the mesto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMesto() {
        return mesto;
    }

    /**
     * Sets the value of the mesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMesto(String value) {
        this.mesto = value;
    }

    /**
     * Gets the value of the datumZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link Datum }
     *     
     */
    public Datum getDatumZalbe() {
        return datumZalbe;
    }

    /**
     * Sets the value of the datumZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link Datum }
     *     
     */
    public void setDatumZalbe(Datum value) {
        this.datumZalbe = value;
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
     *         &lt;element name="ime" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="prezime" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="adresa" type="{http://ftn.uns.ac.rs/tim5/apipoverenik/model/util}Adresa"/>
     *         &lt;element name="drugi_podaci_za_kontakt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "ime",
        "prezime",
        "adresa",
        "drugiPodaciZaKontakt"
    })
    public static class PodnosiocZalbe {

        @XmlElement(required = true)
        protected String ime;
        @XmlElement(required = true)
        protected String prezime;
        @XmlElement(required = true)
        protected Adresa adresa;
        @XmlElement(name = "drugi_podaci_za_kontakt")
        protected String drugiPodaciZaKontakt;

        /**
         * Gets the value of the ime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIme() {
            return ime;
        }

        /**
         * Sets the value of the ime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIme(String value) {
            this.ime = value;
        }

        /**
         * Gets the value of the prezime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPrezime() {
            return prezime;
        }

        /**
         * Sets the value of the prezime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPrezime(String value) {
            this.prezime = value;
        }

        /**
         * Gets the value of the adresa property.
         * 
         * @return
         *     possible object is
         *     {@link Adresa }
         *     
         */
        public Adresa getAdresa() {
            return adresa;
        }

        /**
         * Sets the value of the adresa property.
         * 
         * @param value
         *     allowed object is
         *     {@link Adresa }
         *     
         */
        public void setAdresa(Adresa value) {
            this.adresa = value;
        }

        /**
         * Gets the value of the drugiPodaciZaKontakt property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDrugiPodaciZaKontakt() {
            return drugiPodaciZaKontakt;
        }

        /**
         * Sets the value of the drugiPodaciZaKontakt property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDrugiPodaciZaKontakt(String value) {
            this.drugiPodaciZaKontakt = value;
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
     *         &lt;element name="naziv" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="adresa" type="{http://ftn.uns.ac.rs/tim5/apipoverenik/model/util}Adresa"/>
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
        "naziv",
        "adresa"
    })
    public static class Poverenik {

        @XmlElement(required = true)
        protected String naziv;
        @XmlElement(required = true)
        protected Adresa adresa;

        /**
         * Gets the value of the naziv property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNaziv() {
            return naziv;
        }

        /**
         * Sets the value of the naziv property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNaziv(String value) {
            this.naziv = value;
        }

        /**
         * Gets the value of the adresa property.
         * 
         * @return
         *     possible object is
         *     {@link Adresa }
         *     
         */
        public Adresa getAdresa() {
            return adresa;
        }

        /**
         * Sets the value of the adresa property.
         * 
         * @param value
         *     allowed object is
         *     {@link Adresa }
         *     
         */
        public void setAdresa(Adresa value) {
            this.adresa = value;
        }

    }

}

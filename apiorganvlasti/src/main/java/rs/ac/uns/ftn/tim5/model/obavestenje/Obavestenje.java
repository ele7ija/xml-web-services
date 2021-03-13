//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.03.04 at 09:48:55 AM CET 
//


package rs.ac.uns.ftn.tim5.model.obavestenje;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import rs.ac.uns.ftn.tim5.interfaces.Identifiable;
import rs.ac.uns.ftn.tim5.model.util.Datum;
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
 *         &lt;element name="odbijen" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="istekao" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Trazilac" type="{http://ftn.uns.ac.rs/tim5/model/util}TFizicko_Lice"/>
 *         &lt;element name="Organ" type="{http://ftn.uns.ac.rs/tim5/model/util}TPravno_Lice"/>
 *         &lt;element name="Predmet">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Broj_Predmeta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="Datum" type="{http://ftn.uns.ac.rs/tim5/model/util}Datum"/>
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

    protected boolean odbijen;
    protected boolean istekao;
    @XmlElement(name = "Trazilac", required = true)
    protected TFizickoLice trazilac;
    @XmlElement(name = "Organ", required = true)
    protected TPravnoLice organ;
    @XmlElement(name = "Predmet", required = true)
    protected Obavestenje.Predmet predmet;
    @XmlAttribute(name = "id")
    protected Long id;
    @XmlAttribute(name = "id_zahteva")
    protected Long idZahteva;

    /**
     * Gets the value of the odbijen property.
     * 
     */
    public boolean isOdbijen() {
        return odbijen;
    }

    /**
     * Sets the value of the odbijen property.
     * 
     */
    public void setOdbijen(boolean value) {
        this.odbijen = value;
    }

    /**
     * Gets the value of the istekao property.
     * 
     */
    public boolean isIstekao() {
        return istekao;
    }

    /**
     * Sets the value of the istekao property.
     * 
     */
    public void setIstekao(boolean value) {
        this.istekao = value;
    }

    /**
     * Gets the value of the trazilac property.
     * 
     * @return
     *     possible object is
     *     {@link TFizickoLice }
     *     
     */
    public TFizickoLice getTrazilac() {
        return trazilac;
    }

    /**
     * Sets the value of the trazilac property.
     * 
     * @param value
     *     allowed object is
     *     {@link TFizickoLice }
     *     
     */
    public void setTrazilac(TFizickoLice value) {
        this.trazilac = value;
    }

    /**
     * Gets the value of the organ property.
     * 
     * @return
     *     possible object is
     *     {@link TPravnoLice }
     *     
     */
    public TPravnoLice getOrgan() {
        return organ;
    }

    /**
     * Sets the value of the organ property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPravnoLice }
     *     
     */
    public void setOrgan(TPravnoLice value) {
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
     *         &lt;element name="Datum" type="{http://ftn.uns.ac.rs/tim5/model/util}Datum"/>
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
        protected Datum datum;
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
         *     {@link Datum }
         *     
         */
        public Datum getDatum() {
            return datum;
        }

        /**
         * Sets the value of the datum property.
         * 
         * @param value
         *     allowed object is
         *     {@link Datum }
         *     
         */
        public void setDatum(Datum value) {
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

    }

}
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.09 at 11:16:02 AM CET 
//


package rs.ac.uns.ftn.tim5.apipoverenik.model.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TPravni_Osnov complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPravni_Osnov">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Naziv" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Clan" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="Strana" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="Dopune" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Dopuna" type="{http://ftn.uns.ac.rs/tim5/apipoverenik/model/util}TDopuna_Pravnog_Osnova" maxOccurs="unbounded"/>
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
@XmlType(name = "TPravni_Osnov", propOrder = {
    "naziv",
    "clan",
    "strana",
    "dopune"
})
public class TPravniOsnov {

    @XmlElement(name = "Naziv", required = true)
    protected String naziv;
    @XmlElement(name = "Clan")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger clan;
    @XmlElement(name = "Strana")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger strana;
    @XmlElement(name = "Dopune")
    protected TPravniOsnov.Dopune dopune;

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
     * Gets the value of the clan property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getClan() {
        return clan;
    }

    /**
     * Sets the value of the clan property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setClan(BigInteger value) {
        this.clan = value;
    }

    /**
     * Gets the value of the strana property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStrana() {
        return strana;
    }

    /**
     * Sets the value of the strana property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStrana(BigInteger value) {
        this.strana = value;
    }

    /**
     * Gets the value of the dopune property.
     * 
     * @return
     *     possible object is
     *     {@link TPravniOsnov.Dopune }
     *     
     */
    public TPravniOsnov.Dopune getDopune() {
        return dopune;
    }

    /**
     * Sets the value of the dopune property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPravniOsnov.Dopune }
     *     
     */
    public void setDopune(TPravniOsnov.Dopune value) {
        this.dopune = value;
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
     *         &lt;element name="Dopuna" type="{http://ftn.uns.ac.rs/tim5/apipoverenik/model/util}TDopuna_Pravnog_Osnova" maxOccurs="unbounded"/>
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
        "dopuna"
    })
    public static class Dopune {

        @XmlElement(name = "Dopuna", required = true)
        protected List<TDopunaPravnogOsnova> dopuna;

        /**
         * Gets the value of the dopuna property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the dopuna property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDopuna().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TDopunaPravnogOsnova }
         * 
         * 
         */
        public List<TDopunaPravnogOsnova> getDopuna() {
            if (dopuna == null) {
                dopuna = new ArrayList<TDopunaPravnogOsnova>();
            }
            return this.dopuna;
        }

    }

}

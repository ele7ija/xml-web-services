//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.13 at 08:32:19 PM CET 
//


package ftn.xmlws.tim5.apiorganvlasti.model.util;

import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TDopuna_Pravnog_Osnova complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TDopuna_Pravnog_Osnova"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Broj_dopune" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="Godina"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
 *               &lt;pattern value="[12][0-9]{3}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TDopuna_Pravnog_Osnova", propOrder = {
    "brojDopune",
    "godina"
})
public class TDopunaPravnogOsnova {

    @XmlElement(name = "Broj_dopune", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger brojDopune;
    @XmlElement(name = "Godina", required = true)
    protected BigInteger godina;

    /**
     * Gets the value of the brojDopune property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBrojDopune() {
        return brojDopune;
    }

    /**
     * Sets the value of the brojDopune property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBrojDopune(BigInteger value) {
        this.brojDopune = value;
    }

    /**
     * Gets the value of the godina property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getGodina() {
        return godina;
    }

    /**
     * Sets the value of the godina property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setGodina(BigInteger value) {
        this.godina = value;
    }

}
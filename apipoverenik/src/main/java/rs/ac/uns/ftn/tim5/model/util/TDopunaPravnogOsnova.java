//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.27 at 08:07:47 PM CEST 
//


package rs.ac.uns.ftn.tim5.model.util;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TDopuna_Pravnog_Osnova complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TDopuna_Pravnog_Osnova">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Broj_dopune" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="Godina">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *               &lt;pattern value="[12][0-9]{3}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
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

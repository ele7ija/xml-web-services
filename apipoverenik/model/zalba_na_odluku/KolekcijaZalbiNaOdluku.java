//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.06.25 at 12:46:09 PM CEST 
//


package rs.ac.uns.ftn.tim5.model.zalba_na_odluku;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element ref="{http://ftn.uns.ac.rs/tim5/model/zalba_na_odluku}Zalba_na_odluku" maxOccurs="unbounded" minOccurs="0"/>
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
    "zalbaNaOdluku"
})
@XmlRootElement(name = "Kolekcija_zalbi_na_Odluku")
public class KolekcijaZalbiNaOdluku {

    @XmlElement(name = "Zalba_na_odluku")
    protected List<ZalbaNaOdluku> zalbaNaOdluku;

    /**
     * Gets the value of the zalbaNaOdluku property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the zalbaNaOdluku property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getZalbaNaOdluku().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ZalbaNaOdluku }
     * 
     * 
     */
    public List<ZalbaNaOdluku> getZalbaNaOdluku() {
        if (zalbaNaOdluku == null) {
            zalbaNaOdluku = new ArrayList<ZalbaNaOdluku>();
        }
        return this.zalbaNaOdluku;
    }

    public void setZalbaNaOdluku(List<ZalbaNaOdluku> zalbaNaOdluku) {
        this.zalbaNaOdluku = zalbaNaOdluku;
    }
}

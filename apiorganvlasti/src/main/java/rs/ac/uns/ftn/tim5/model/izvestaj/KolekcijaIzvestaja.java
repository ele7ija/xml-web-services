//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.27 at 08:04:17 PM CEST 
//


package rs.ac.uns.ftn.tim5.model.izvestaj;

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
 *         &lt;element ref="{http://ftn.uns.ac.rs/tim5/model/izvestaj}Izvestaj" maxOccurs="unbounded" minOccurs="0"/>
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
    "izvestaj"
})
@XmlRootElement(name = "Kolekcija_Izvestaja")
public class KolekcijaIzvestaja {

    @XmlElement(name = "Izvestaj")
    protected List<Izvestaj> izvestaj;

    /**
     * Gets the value of the izvestaj property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the izvestaj property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIzvestaj().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Izvestaj }
     * 
     * 
     */
    public List<Izvestaj> getIzvestaj() {
        if (izvestaj == null) {
            izvestaj = new ArrayList<Izvestaj>();
        }
        return this.izvestaj;
    }

    public void setIzvestaj(List<Izvestaj> izvestaj) {
        this.izvestaj = izvestaj;
    }
}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.09 at 12:15:06 PM CET 
//


package rs.ac.uns.ftn.tim5.apipoverenik.model.zalbe_cutanja;

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
 *         &lt;element ref="{http://ftn.uns.ac.rs/tim5/apipoverenik/model/zalbe_cutanja}zalba_cutanja" maxOccurs="unbounded" minOccurs="0"/>
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
    "zalbaCutanja"
})
@XmlRootElement(name = "Kolekcija_zalbi_cutanja")
public class KolekcijaZalbiCutanja {

    @XmlElement(name = "zalba_cutanja")
    protected List<ZalbaCutanja> zalbaCutanja;

    /**
     * Gets the value of the zalbaCutanja property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the zalbaCutanja property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getZalbaCutanja().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ZalbaCutanja }
     * 
     * 
     */
    public List<ZalbaCutanja> getZalbaCutanja() {
        if (zalbaCutanja == null) {
            zalbaCutanja = new ArrayList<ZalbaCutanja>();
        }
        return this.zalbaCutanja;
    }

    public void setZalbaCutanja(List<ZalbaCutanja> zalbaCutanja) {
        this.zalbaCutanja = zalbaCutanja;
    }
}

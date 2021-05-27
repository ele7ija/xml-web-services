//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.27 at 03:28:30 PM CEST 
//


package rs.ac.uns.ftn.tim5.model.resenje;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TIshod_odluke.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TIshod_odluke">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="osnovana_zalba"/>
 *     &lt;enumeration value="neosnovana_zalba"/>
 *     &lt;enumeration value="ponistena_zalba"/>
 *     &lt;enumeration value="odbijen_zahtev"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TIshod_odluke")
@XmlEnum
public enum TIshodOdluke {

    @XmlEnumValue("osnovana_zalba")
    OSNOVANA_ZALBA("osnovana_zalba"),
    @XmlEnumValue("neosnovana_zalba")
    NEOSNOVANA_ZALBA("neosnovana_zalba"),
    @XmlEnumValue("ponistena_zalba")
    PONISTENA_ZALBA("ponistena_zalba"),
    @XmlEnumValue("odbijen_zahtev")
    ODBIJEN_ZAHTEV("odbijen_zahtev");
    private final String value;

    TIshodOdluke(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TIshodOdluke fromValue(String v) {
        for (TIshodOdluke c: TIshodOdluke.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

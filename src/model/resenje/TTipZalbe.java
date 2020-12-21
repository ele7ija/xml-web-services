//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.13 at 08:22:08 PM CET 
//


package model.resenje;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TTip_zalbe.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="TTip_zalbe"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="odbacivanje"/&gt;
 *     &lt;enumeration value="nepostupanje"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TTip_zalbe", namespace = "http://ftn.uns.ac.rs/tim5/resenje")
@XmlEnum
public enum TTipZalbe {

    @XmlEnumValue("odbacivanje")
    ODBACIVANJE("odbacivanje"),
    @XmlEnumValue("nepostupanje")
    NEPOSTUPANJE("nepostupanje");
    private final String value;

    TTipZalbe(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TTipZalbe fromValue(String v) {
        for (TTipZalbe c: TTipZalbe.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

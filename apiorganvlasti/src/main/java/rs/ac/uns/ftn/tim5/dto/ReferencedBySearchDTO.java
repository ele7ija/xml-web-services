package rs.ac.uns.ftn.tim5.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "about" })
@XmlRootElement(name = "referenced-by-search")
public class ReferencedBySearchDTO {

    @XmlElement(required = true)
    private String about;

    public ReferencedBySearchDTO() {
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}




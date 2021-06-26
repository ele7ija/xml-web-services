package rs.ac.uns.ftn.tim5.dto;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "original", "dokument" })
@XmlRootElement(name = "referenced-by")
public class ReferencedByDTO {

    @XmlElement(required = true)
    private String original;

    @XmlElement()
    private List<String> dokument;

    public ReferencedByDTO() {
    }

    public ReferencedByDTO(String original, List<String> dokument) {
        this.original = original;
        this.dokument = dokument;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public List<String> getDokument() {
        return dokument;
    }

    public void setDokument(List<String> dokument) {
        this.dokument = dokument;
    }
}



package rs.ac.uns.ftn.tim5.dto;


import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "term", "metadata" })
@XmlRootElement(name = "pretraga")
public class PretragaDTO {

    @XmlElement(required = true)
    private String term;

    @XmlElement(required = true)
    private String metadata;

    public PretragaDTO(String term, String metadata) {
        this.term = term;
        this.metadata = metadata;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public PretragaDTO() {
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }


}


package rs.ac.uns.ftn.tim5.apipoverenik.model.dummy;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dummy", propOrder = {
        "field"
})
@XmlRootElement
public class Dummy {

    @XmlElement(name = "Field")
    private int field;

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }
}

package rs.ac.uns.ftn.tim5.apipoverenik.model.wrapper;

import rs.ac.uns.ftn.tim5.apipoverenik.model.zalbe_cutanja.ZalbaCutanja;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ZalbaCutanjaXmlListWrapper {

    List<ZalbaCutanja> data;

    public ZalbaCutanjaXmlListWrapper() {
    }

    public ZalbaCutanjaXmlListWrapper(List<ZalbaCutanja> data) {
        this.data = data;
    }

    public List<ZalbaCutanja> getData() {
        return data;
    }

    public void setData(List<ZalbaCutanja> data) {
        this.data = data;
    }
}

package rs.ac.uns.ftn.tim5.SOAP.zalbaCutanje;

import rs.ac.uns.ftn.tim5.model.zalba_cutanja.ZalbaCutanja;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(targetNamespace = "http://www.sistem.org/ws/zalba_cutanje", name = "ZalbaCutanjePort")
@XmlSeeAlso(rs.ac.uns.ftn.tim5.model.zalba_cutanja.ObjectFactory.class)
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ZalbaCutanjePort {
    @WebMethod
    public void sendZalba(
            @WebParam(partName = "zalba_cutanje", name = "zalba_cutanje")
            ZalbaCutanja zalbaCutanja
    );
}

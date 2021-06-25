package rs.ac.uns.ftn.tim5.SOAP.zalbaOdluka;

import rs.ac.uns.ftn.tim5.model.zalba_cutanja.ZalbaCutanja;
import rs.ac.uns.ftn.tim5.model.zalba_na_odluku.ZalbaNaOdluku;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(targetNamespace = "http://www.sistem.org/ws/zalba_odluka", name = "ZalbaOdlukaPort")
@XmlSeeAlso(rs.ac.uns.ftn.tim5.model.zalba_na_odluku.ObjectFactory.class)
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ZalbaOdlukaPort {
    @WebMethod
    public void sendZalba(
            @WebParam(partName = "zalba_odluka", name = "zalba_odluka")
                    ZalbaNaOdluku zalbaNaOdluku
    );
}

package rs.ac.uns.ftn.tim5.SOAP.resenje;

import rs.ac.uns.ftn.tim5.model.resenje.Resenje;
import rs.ac.uns.ftn.tim5.model.zalba_na_odluku.ZalbaNaOdluku;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(targetNamespace = "http://www.sistem.org/ws/resenje", name = "ResnjePort")
@XmlSeeAlso(rs.ac.uns.ftn.tim5.model.resenje.ObjectFactory.class)
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ResenjePort {

    @WebMethod
    public void sendResenje(
            @WebParam(partName = "resenje", name = "resenje")
                    Resenje resenje
    );
}

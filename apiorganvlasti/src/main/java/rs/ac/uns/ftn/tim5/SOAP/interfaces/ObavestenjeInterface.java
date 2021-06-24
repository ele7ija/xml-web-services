package rs.ac.uns.ftn.tim5.SOAP.interfaces;

import rs.ac.uns.ftn.tim5.model.obavestenje.Obavestenje;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(targetNamespace = "http://www.sistem.org/ws/obavestenje", name = "Obavestenje")
@XmlSeeAlso({rs.ac.uns.ftn.tim5.model.obavestenje.ObjectFactory.class})
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ObavestenjeInterface {

    @WebMethod
    public void sendObavestenje(
            @WebParam(partName = "obavestenje", name = "obavestenje")
                    Obavestenje obavestenje
    );
}

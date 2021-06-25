package rs.ac.uns.ftn.tim5.SOAP.interfaces;

import rs.ac.uns.ftn.tim5.model.izvestaj.Izvestaj;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(targetNamespace = "http://www.sistem.org/ws/izvestaj", name = "IzvestajPort")
@XmlSeeAlso({rs.ac.uns.ftn.tim5.model.izvestaj.ObjectFactory.class})
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IzvestajInterface {
    @WebMethod
    public void sendIzvestaj(
            @WebParam(partName = "izvestaj", name = "izvestaj")
                    Izvestaj izvestaj
    );
}

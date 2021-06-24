package rs.ac.uns.ftn.tim5.SOAP.izvestaj;

import rs.ac.uns.ftn.tim5.model.izvestaj.Izvestaj;
import rs.ac.uns.ftn.tim5.model.obavestenje.Obavestenje;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

@WebService(targetNamespace = "http://www.sistem.org/ws/izvestaj", name = "Izvestaj")
@XmlSeeAlso(rs.ac.uns.ftn.tim5.model.izvestaj.ObjectFactory.class)
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IzvestajPort {
    @WebMethod
    public void sendIzvestaj(
        @WebParam(partName = "izvestaj", name = "izvestaj")
        Izvestaj izvestaj
    );
}

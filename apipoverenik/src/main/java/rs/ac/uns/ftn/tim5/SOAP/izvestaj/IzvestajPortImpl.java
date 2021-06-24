package rs.ac.uns.ftn.tim5.SOAP.izvestaj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.tim5.model.izvestaj.Izvestaj;
import rs.ac.uns.ftn.tim5.model.obavestenje.Obavestenje;
import rs.ac.uns.ftn.tim5.service.IzvestajService;
import rs.ac.uns.ftn.tim5.service.ObavestenjeService;

@javax.jws.WebService(
        serviceName = "IzvestajService",
        portName = "IzvestajPort",
        targetNamespace = "http://www.sistem.org/ws/izvestaj",
        // wsdlLocation = "classpath:wsdl/Izvestaj.wsdl",
        endpointInterface = "rs.ac.uns.ftn.tim5.SOAP.izvestaj.IzvestajPort")
@Service
public class IzvestajPortImpl implements IzvestajPort {

    @Autowired
    private IzvestajService service;

    @Override
    public void sendIzvestaj(Izvestaj izvestaj) {
        System.out.println("dosao poziv");
        service.create(izvestaj);
    }
}

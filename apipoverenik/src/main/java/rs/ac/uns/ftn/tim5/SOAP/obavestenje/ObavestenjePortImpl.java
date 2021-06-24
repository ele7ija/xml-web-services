package rs.ac.uns.ftn.tim5.SOAP.obavestenje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.tim5.model.obavestenje.Obavestenje;
import rs.ac.uns.ftn.tim5.service.ObavestenjeService;

@javax.jws.WebService(
        serviceName = "ObavestenjeService",
        portName = "ObavestenjePort",
        targetNamespace = "http://www.project.org/ws/obavestenje",
        // wsdlLocation = "classpath:wsdl/Izvestaj.wsdl",
        endpointInterface = "rs.ac.uns.ftn.tim5.SOAP.obavestenje.ObavestenjePort")
@Service
public class ObavestenjePortImpl implements ObavestenjePort{

    @Autowired
    private ObavestenjeService service;

    @Override
    public void sendObavestenje(Obavestenje obavestenje) {
        System.out.println("dosao poziv");
        service.create(obavestenje);
    }
}

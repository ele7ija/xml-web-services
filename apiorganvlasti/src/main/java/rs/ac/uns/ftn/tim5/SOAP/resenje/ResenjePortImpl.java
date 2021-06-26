package rs.ac.uns.ftn.tim5.SOAP.resenje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.tim5.model.resenje.Resenje;
import rs.ac.uns.ftn.tim5.service.ResenjeService;

@javax.jws.WebService(
        serviceName = "ResenjeService",
        portName = "ResenjePort",
        targetNamespace = "http://www.sistem.org/ws/resenje",
        endpointInterface = "rs.ac.uns.ftn.tim5.SOAP.resenje.ResenjePort")
@Service
public class ResenjePortImpl implements ResenjePort{

    @Autowired
    private ResenjeService service;
    @Override
    public void sendResenje(Resenje resenje) {
        service.create(resenje);
    }
}

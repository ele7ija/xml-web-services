package rs.ac.uns.ftn.tim5.SOAP.zalbaCutanje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.tim5.model.zalba_cutanja.ZalbaCutanja;
import rs.ac.uns.ftn.tim5.service.ZalbaNaCutanjeService;

@javax.jws.WebService(
        serviceName = "ZalbaCutanjeService",
        portName = "ZalbaCutanjePort",
        targetNamespace = "http://www.sistem.org/ws/zalba_cutanje",
        endpointInterface = "rs.ac.uns.ftn.tim5.SOAP.zalbaCutanje.ZalbaCutanjePort")
@Service
public class ZalbaCutanjePortImpl implements ZalbaCutanjePort {
    @Autowired
    private ZalbaNaCutanjeService service;

    @Override
    public void sendZalba(ZalbaCutanja zalbaCutanja) {
        service.create(zalbaCutanja);
    }
}

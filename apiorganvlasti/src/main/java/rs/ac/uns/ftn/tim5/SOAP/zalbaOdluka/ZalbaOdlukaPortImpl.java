package rs.ac.uns.ftn.tim5.SOAP.zalbaOdluka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.tim5.model.zalba_na_odluku.ZalbaNaOdluku;
import rs.ac.uns.ftn.tim5.service.ZalbaNaOdlukuService;

@javax.jws.WebService(
        serviceName = "ZalbaOdlukaService",
        portName = "ZalbaOdlukaPort",
        targetNamespace = "http://www.sistem.org/ws/zalba_odluka",
        endpointInterface = "rs.ac.uns.ftn.tim5.SOAP.zalbaOdluka.ZalbaOdlukaPort")
@Service
public class ZalbaOdlukaPortImpl implements ZalbaOdlukaPort {
    @Autowired
    private ZalbaNaOdlukuService service;

    @Override
    public void sendZalba(ZalbaNaOdluku zalbaNaOdluku) {
        System.out.println("Stigao");
        System.out.println(zalbaNaOdluku.getId());
        service.create(zalbaNaOdluku);
    }
}

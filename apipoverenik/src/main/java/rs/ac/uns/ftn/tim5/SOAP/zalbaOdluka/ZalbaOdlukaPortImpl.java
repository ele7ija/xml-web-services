package rs.ac.uns.ftn.tim5.SOAP.zalbaOdluka;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.tim5.model.zalba_na_odluku.ZalbaNaOdluku;

@javax.jws.WebService(
        serviceName = "ZalbaOdlukaService",
        portName = "ZalbaOdlukaPort",
        targetNamespace = "http://www.sistem.org/ws/zalba_odluka",
        endpointInterface = "rs.ac.uns.ftn.tim5.SOAP.zalbaOdluka.ZalbaOdlukaPort")
@Service
public class ZalbaOdlukaPortImpl implements ZalbaOdlukaPort {
    @Override
    public void sendZalba(ZalbaNaOdluku zalbaNaOdluku) {
        //TODO isto ko kod zalbe cutanja
    }
}

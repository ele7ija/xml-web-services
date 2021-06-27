package rs.ac.uns.ftn.tim5.SOAP.client;

import org.springframework.scheduling.annotation.Async;
import rs.ac.uns.ftn.tim5.SOAP.zalbaOdluka.ZalbaOdlukaPort;
import rs.ac.uns.ftn.tim5.model.zalba_na_odluku.ZalbaNaOdluku;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

@org.springframework.stereotype.Service
public class ZalbaOdlukaClient {

    @Async
    public void sendZalba(ZalbaNaOdluku zalbaNaOdluku) {
        try {
            URL wsdlLocation = new URL("http://organ-vlasti:8080/ws/zalba_odluka?wsdl");
            QName serviceName = new QName("http://www.sistem.org/ws/zalba_odluka", "ZalbaOdlukaService");
            QName portName = new QName("http://www.sistem.org/ws/zalba_odluka", "ZalbaOdlukaPort");

            Service service = Service.create(wsdlLocation, serviceName);
            ZalbaOdlukaPort zalbaOdlukaPort = service.getPort(portName, ZalbaOdlukaPort.class);

            zalbaOdlukaPort.sendZalba(zalbaNaOdluku);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}

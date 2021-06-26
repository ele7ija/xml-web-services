package rs.ac.uns.ftn.tim5.SOAP.client;

import rs.ac.uns.ftn.tim5.SOAP.zalbaCutanje.ZalbaCutanjePort;
import rs.ac.uns.ftn.tim5.model.zalba_cutanja.ZalbaCutanja;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

@org.springframework.stereotype.Service
public class ZalbaCutanjeClient {
    public void sendZalba(ZalbaCutanja zalbaCutanja) {
        try {
            URL wsdlLocation = new URL("http://organ-vlasti:8080/ws/zalba_cutanje?wsdl");
            QName serviceName = new QName("http://www.sistem.org/ws/zalba_cutanje", "ZalbaCutanjeService");
            QName portName = new QName("http://www.sistem.org/ws/zalba_cutanje", "ZalbaCutanjePort");

            Service service = Service.create(wsdlLocation, serviceName);
            ZalbaCutanjePort zalbaCutanjeInterface = service.getPort(portName, ZalbaCutanjePort.class);

            zalbaCutanjeInterface.sendZalba(zalbaCutanja);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

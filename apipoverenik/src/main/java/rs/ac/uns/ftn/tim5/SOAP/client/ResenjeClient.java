package rs.ac.uns.ftn.tim5.SOAP.client;

import rs.ac.uns.ftn.tim5.SOAP.intefaces.ResenjeInterface;
import rs.ac.uns.ftn.tim5.model.izvestaj.Izvestaj;
import rs.ac.uns.ftn.tim5.model.resenje.Resenje;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;


@org.springframework.stereotype.Service
public class ResenjeClient {

    public void sendIzvestaj(Resenje resenje) {
        try {
            URL wsdlLocation = new URL("http://organ-vlasti:8080/ws/resenje?wsdl");
            QName serviceName = new QName("http://www.sistem.org/ws/resenje", "ResenjeService");
            QName portName = new QName("http://www.sistem.org/ws/resenje", "ResenjePort");

            Service service = Service.create(wsdlLocation, serviceName);
            ResenjeInterface resenjeInterface = service.getPort(portName, ResenjeInterface.class);

            resenjeInterface.sendResenje(resenje);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

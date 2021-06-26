package rs.ac.uns.ftn.tim5.SOAP.client;

import rs.ac.uns.ftn.tim5.SOAP.interfaces.IzvestajInterface;
import rs.ac.uns.ftn.tim5.SOAP.interfaces.ObavestenjeInterface;
import rs.ac.uns.ftn.tim5.model.izvestaj.Izvestaj;
import rs.ac.uns.ftn.tim5.model.obavestenje.Obavestenje;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

@org.springframework.stereotype.Service
public class IzvestajClient {
    public void sendIzvestaj(Izvestaj izvestaj) {
        try {
            URL wsdlLocation = new URL("http://poverenik:8080/ws/izvestaj?wsdl");
            QName serviceName = new QName("http://www.sistem.org/ws/izvestaj", "IzvestajService");
            QName portName = new QName("http://www.sistem.org/ws/izvestaj", "IzvestajPort");

            Service service = Service.create(wsdlLocation, serviceName);
            IzvestajInterface izvestajInterface = service.getPort(portName, IzvestajInterface.class);

            izvestajInterface.sendIzvestaj(izvestaj);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

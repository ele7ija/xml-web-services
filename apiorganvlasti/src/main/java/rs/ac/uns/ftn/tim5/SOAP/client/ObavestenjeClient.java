package rs.ac.uns.ftn.tim5.SOAP.client;


import org.apache.jena.base.Sys;
import rs.ac.uns.ftn.tim5.SOAP.interfaces.ObavestenjeInterface;
import rs.ac.uns.ftn.tim5.model.obavestenje.Obavestenje;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

@org.springframework.stereotype.Service
public class ObavestenjeClient {

    public void sendObavestenje(Obavestenje obavestenje) {
        try {
            URL wsdlLocation = new URL("http://poverenik:8080/ws/zalbaObavestenja?wsdl");
            QName serviceName = new QName("http://www.sistem.org/ws/obavestenje", "ObavestenjeService");
            QName portName = new QName("http://www.sistem.org/ws/obavestenje", "ObavestenjePort");

            System.out.println("pre");
            Service service = Service.create(wsdlLocation, serviceName);
            System.out.println("posle service");
            ObavestenjeInterface obavestenjeInterface = service.getPort(portName, ObavestenjeInterface.class);
            System.out.println("posle int");
            obavestenjeInterface.sendObavestenje(obavestenje);
            System.out.println("zavrsio");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

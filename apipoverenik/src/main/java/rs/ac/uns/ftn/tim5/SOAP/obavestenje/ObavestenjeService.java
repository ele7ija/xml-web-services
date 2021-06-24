package rs.ac.uns.ftn.tim5.SOAP.obavestenje;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.URL;

@WebServiceClient(name = "ObavestenjeService",
        wsdlLocation = "classpath:wsdl/Obavestenje.wsdl",
        targetNamespace = "http://www.sistem.org/ws/obavestenje")
public class ObavestenjeService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.sistem.org/ws/obavestenje", "ObavestenjeService");
    public final static QName ObavestenjePort = new QName("http://www.sistem.org/ws/obavestenje", "ObavestenjePort");
    static {
        URL url = ObavestenjeService.class.getClassLoader().getResource("wsdl/Obavestenje.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(ObavestenjeService.class.getName())
                    .log(java.util.logging.Level.INFO,
                            "Can not initialize the default wsdl from {0}", "classpath:wsdl/Obavestenje.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ObavestenjeService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ObavestenjeService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ObavestenjeService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public ObavestenjeService(WebServiceFeature... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ObavestenjeService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ObavestenjeService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    @WebEndpoint(name = "ObavestenjePort")
    public ObavestenjePort getObavestenjePort() {
        return super.getPort(ObavestenjePort, ObavestenjePort.class);
    }


    @WebEndpoint(name = "ObavestenjePort")
    public ObavestenjePort getObavestenjePort(WebServiceFeature... features) {
        return super.getPort(ObavestenjePort, ObavestenjePort.class, features);
    }

}

package rs.ac.uns.ftn.tim5.SOAP.zalbaCutanje;

import rs.ac.uns.ftn.tim5.SOAP.izvestaj.IzvestajPort;
import rs.ac.uns.ftn.tim5.SOAP.izvestaj.IzvestajService;
import rs.ac.uns.ftn.tim5.SOAP.obavestenje.ObavestenjeService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.URL;

@WebServiceClient(name = "ZalbaCutanjeService",
        wsdlLocation = "classpath:wsdl/ZalbaCutanje.wsdl",
        targetNamespace = "http://www.sistem.org/ws/zalba_cutanje")
public class ZalbaCutanjeService extends Service {
    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.sistem.org/ws/zalba_cutanje", "ZalbaCutanjeService");
    public final static QName ZalbaPort = new QName("http://www.sistem.org/ws/zalba_cutanje", "ZalbaCutanjePort");
    static {
        URL url = ZalbaCutanjeService.class.getClassLoader().getResource("wsdl/ZalbaCutanje.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(ZalbaCutanjeService.class.getName())
                    .log(java.util.logging.Level.INFO,
                            "Can not initialize the default wsdl from {0}", "classpath:wsdl/ZalbaCutanje.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ZalbaCutanjeService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ZalbaCutanjeService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ZalbaCutanjeService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public ZalbaCutanjeService(WebServiceFeature... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ZalbaCutanjeService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ZalbaCutanjeService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    @WebEndpoint(name = "ZalbaCutanjePort")
    public rs.ac.uns.ftn.tim5.SOAP.zalbaCutanje.ZalbaCutanjePort getZalbaCutanjePort() {
        return super.getPort(ZalbaPort, rs.ac.uns.ftn.tim5.SOAP.zalbaCutanje.ZalbaCutanjePort.class);
    }


    @WebEndpoint(name = "ZalbaCutanjePort")
    public ZalbaCutanjePort getZalbaCutanjePort(WebServiceFeature... features) {
        return super.getPort(ZalbaPort, ZalbaCutanjePort.class, features);
    }
}

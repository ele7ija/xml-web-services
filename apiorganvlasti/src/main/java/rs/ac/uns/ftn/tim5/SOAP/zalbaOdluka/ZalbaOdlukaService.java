package rs.ac.uns.ftn.tim5.SOAP.zalbaOdluka;


import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.URL;

@WebServiceClient(name = "ZalbaOdlukaService",
        wsdlLocation = "classpath:wsdl/ZalbaOdluka.wsdl",
        targetNamespace = "http://www.sistem.org/ws/zalba_odluka")
public class ZalbaOdlukaService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.sistem.org/ws/zalba_odluka", "ZalbaOdlukaService");
    public final static QName ZalbaPort = new QName("http://www.sistem.org/ws/zalba_odluka", "ZalbaOdlukaPort");
    static {
        URL url = ZalbaOdlukaService.class.getClassLoader().getResource("wsdl/ZalbaOdluka.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(ZalbaOdlukaService.class.getName())
                    .log(java.util.logging.Level.INFO,
                            "Can not initialize the default wsdl from {0}", "classpath:wsdl/ZalbaOdluka.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ZalbaOdlukaService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ZalbaOdlukaService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ZalbaOdlukaService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public ZalbaOdlukaService(WebServiceFeature... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ZalbaOdlukaService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ZalbaOdlukaService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    @WebEndpoint(name = "ZalbaOdlukaPort")
    public ZalbaOdlukaPort getZalbaCutanjePort() {
        return super.getPort(ZalbaPort, ZalbaOdlukaPort.class);
    }


    @WebEndpoint(name = "ZalbaOdlukaPort")
    public ZalbaOdlukaPort getZalbaCutanjePort(WebServiceFeature... features) {
        return super.getPort(ZalbaPort, ZalbaOdlukaPort.class, features);
    }
}

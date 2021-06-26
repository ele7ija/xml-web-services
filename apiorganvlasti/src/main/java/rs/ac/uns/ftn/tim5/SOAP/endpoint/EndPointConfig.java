package rs.ac.uns.ftn.tim5.SOAP.endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.ac.uns.ftn.tim5.SOAP.resenje.ResenjePort;
import rs.ac.uns.ftn.tim5.SOAP.resenje.ResenjePortImpl;
import rs.ac.uns.ftn.tim5.SOAP.zalbaCutanje.ZalbaCutanjePortImpl;
import rs.ac.uns.ftn.tim5.SOAP.zalbaOdluka.ZalbaOdlukaPortImpl;

import javax.xml.ws.Endpoint;

@Configuration
public class EndPointConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private ZalbaCutanjePortImpl zalbaCutanjePort;

    @Autowired
    private ZalbaOdlukaPortImpl zalbaOdlukaPort;

    @Autowired
    private ResenjePortImpl resenjePort;

    @Bean
    public Endpoint zalbaCutanjeEndPoint(){
        EndpointImpl endpoint = new EndpointImpl(bus, zalbaCutanjePort);
        endpoint.publish("/zalba_cutanje");
        return endpoint;
    }

    @Bean
    public Endpoint izvestajEndPoint(){
        EndpointImpl endpoint = new EndpointImpl(bus, resenjePort);
        endpoint.publish("/resenje");
        return endpoint;
    }

    @Bean
    public Endpoint zalbaOdlukaEndPoint(){
        EndpointImpl endpoint = new EndpointImpl(bus, zalbaOdlukaPort);
        endpoint.publish("/zalba_odluka");
        return endpoint;
    }

}

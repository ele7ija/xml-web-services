package rs.ac.uns.ftn.tim5.SOAP.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.cxf.Bus;
import rs.ac.uns.ftn.tim5.SOAP.izvestaj.IzvestajPortImpl;
import rs.ac.uns.ftn.tim5.SOAP.obavestenje.ObavestenjePort;
import javax.xml.ws.Endpoint;
import org.apache.cxf.jaxws.EndpointImpl;
import rs.ac.uns.ftn.tim5.SOAP.obavestenje.ObavestenjePortImpl;

@Configuration
public class EndpointConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private ObavestenjePortImpl obavestenjePort;

    @Autowired
    private IzvestajPortImpl izvestajPort;
    @Bean
    public Endpoint obavestenjeEndPoint(){
        EndpointImpl endpoint = new EndpointImpl(bus, obavestenjePort);
        endpoint.publish("/zalbaObavestenja");
        return endpoint;
    }

    @Bean
    public Endpoint izvestajEndPoint(){
        EndpointImpl endpoint = new EndpointImpl(bus, izvestajPort);
        endpoint.publish("/izvestaj");
        return endpoint;
    }

}

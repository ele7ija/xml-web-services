package ftn.xmlws.tim5.apiorganvlasti.jaxb;

import ftn.xmlws.tim5.apiorganvlasti.model.zahtev.Zahtev;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.stereotype.Component;

import javax.xml.transform.Result;
import java.io.StringWriter;

@Component
public class ZahtevMarshaller implements IMarshaller<Zahtev> {

    @Override
    public String marshall(Zahtev obj) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Zahtev.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(obj, sw);
        return sw.toString();
    }
}

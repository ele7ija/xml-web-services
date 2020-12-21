package ftn.xmlws.tim5.apiorganvlasti.jaxb;

import ftn.xmlws.tim5.apiorganvlasti.model.zahtev.Zahtev;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Component;

import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

@Component
public class ZahtevUnmarshaller implements IUnmarshaller<Zahtev> {

    @Override
    public Zahtev unmarshall(String xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Zahtev.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Zahtev) jaxbUnmarshaller.unmarshal(new StringReader(xml));
    }
}

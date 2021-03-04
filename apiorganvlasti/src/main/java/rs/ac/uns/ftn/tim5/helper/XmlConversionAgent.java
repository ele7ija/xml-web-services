package rs.ac.uns.ftn.tim5.helper;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;

@Component
public class XmlConversionAgent<T> {

    public T unmarshall(String xmlEntity, String contextPath) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(contextPath);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(xmlEntity);
        return (T) unmarshaller.unmarshal(reader);
    }

    public T unmarshall(Node xmlEntity, String contextPath) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(contextPath);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (T) unmarshaller.unmarshal(xmlEntity);
    }

    public String marshall(T entity, String contextPath) throws JAXBException {
        JAXBContext contextObj = JAXBContext.newInstance(contextPath);
        Marshaller marshaller = contextObj.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter sw = new StringWriter();
        marshaller.marshal(entity, sw);
        return sw.toString();
    }

    public OutputStream marshallToOutputStream(T entity, String contextPath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(contextPath);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(entity, os);
        return os;
    }
}

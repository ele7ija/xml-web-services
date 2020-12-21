package ftn.xmlws.tim5.apiorganvlasti.jaxb;

import jakarta.xml.bind.JAXBException;

public interface IMarshaller<T> {

    String marshall(T obj) throws JAXBException;
}

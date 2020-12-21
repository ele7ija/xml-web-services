package ftn.xmlws.tim5.apiorganvlasti.jaxb;

import jakarta.xml.bind.JAXBException;

public interface IUnmarshaller<T> {

    T unmarshall(String xml) throws JAXBException;
}

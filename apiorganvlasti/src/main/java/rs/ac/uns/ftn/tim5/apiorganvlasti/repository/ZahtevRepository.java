package rs.ac.uns.ftn.tim5.apiorganvlasti.repository;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.ResultIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;
import org.xmldb.api.modules.XUpdateQueryService;
import rs.ac.uns.ftn.tim5.apiorganvlasti.helper.DbConnection;
import rs.ac.uns.ftn.tim5.apiorganvlasti.model.zahtev.Zahtev;

import javax.annotation.PostConstruct;
import javax.xml.bind.*;
import javax.xml.namespace.QName;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static rs.ac.uns.ftn.tim5.apiorganvlasti.helper.XQueryExpressions.*;

@Repository
public class ZahtevRepository {

    static final String collectionId = "/db/sample/zahtevi";
    static final String jaxbContextPath = "rs.ac.uns.ftn.tim5.apiorganvlasti.model.zahtev";

    @Autowired
    DbConnection dbConnection;

    public List<Zahtev> getAllZahtevi() {
        ArrayList<Zahtev> zahtevi = new ArrayList<>();
        Collection collection = dbConnection.getCollection(collectionId);
        try {
            XQueryService xQueryService = (XQueryService) collection.getService("XQueryService", "1.0");
            CompiledExpression compiledExpression = xQueryService.compile(X_QUERY_FIND_ALL_EXPRESSION);
            ResourceSet resourceSet = xQueryService.execute(compiledExpression);
            ResourceIterator resourceIterator = resourceSet.getIterator();
            while (resourceIterator.hasMoreResources()){
                XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
                JAXBContext context = JAXBContext.newInstance(jaxbContextPath);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
                zahtevi.add(zahtev);
            }
        } catch (XMLDBException | JAXBException e) {
            e.printStackTrace();
        }
        return zahtevi;
    }

    public Zahtev getZahtev(long idZahteva) {
        Collection collection = dbConnection.getCollection(collectionId);
        try {
            XQueryService xQueryService = (XQueryService) collection.getService("XQueryService", "1.0");
            CompiledExpression compiledExpression = xQueryService.compile(String.format(X_QUERY_FIND_BY_ID_ZAHTEVA_EXPRESSION, idZahteva));
            ResourceSet resourceSet = xQueryService.execute(compiledExpression);
            ResourceIterator resourceIterator = resourceSet.getIterator();
            while (resourceIterator.hasMoreResources()){
                XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
                JAXBContext context = JAXBContext.newInstance(jaxbContextPath);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
                return zahtev;
            }
        } catch (XMLDBException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long createZahtev(Zahtev zahtev) {
        Long id = UUID.randomUUID().getLeastSignificantBits();
        zahtev.setId(id);
        Collection collection = dbConnection.getCollection(collectionId);
        OutputStream os = new ByteArrayOutputStream();
        try {
            XMLResource xmlResource = (XMLResource) collection.createResource(id.toString() + ".xml", XMLResource.RESOURCE_TYPE);
            JAXBContext context = JAXBContext.newInstance(jaxbContextPath);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // marshal the contents to an output stream
            marshaller.marshal(zahtev, os);
            xmlResource.setContent(os);

            collection.storeResource(xmlResource);
            return id;
        } catch (XMLDBException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateZahtev(Zahtev zahtev) {
        Collection collection = dbConnection.getCollection(collectionId);
        StringWriter sw = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(jaxbContextPath);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(zahtev, sw);
            String[] xmlFragments = sw.toString().split("\n");
            String[] xmlFragmentsWithoutWrapper = Arrays.copyOfRange(xmlFragments, 2, xmlFragments.length - 1);
            String xmlFragment = String.join("\n", xmlFragmentsWithoutWrapper);

            XQueryService xQueryService = (XQueryService) collection.getService("XQueryService", "1.0");
            CompiledExpression compiledExpression = xQueryService.compile(String.format(X_UPDATE_UPDATE_BY_ID_ZAHTEVA_EXPRESSION, zahtev.getId() + ".xml", xmlFragment));
            ResourceSet resourceSet  = xQueryService.execute(compiledExpression);
            ResourceIterator resourceIterator = resourceSet.getIterator();
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            Long mods = Long.parseLong((String) xmlResource.getContent());
            return mods > 0; //vraca true iako dokument nije pronadjen, do exist-db je
        } catch (XMLDBException | JAXBException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteZahtev(long idZahteva) {
        Collection collection = dbConnection.getCollection(collectionId);
        try {
            XQueryService xQueryService = (XQueryService) collection.getService("XQueryService", "1.0");
            String expression = String.format(X_UPDATE_REMOVE_BY_ID_ZAHTEVA_EXPRESSION, idZahteva + ".xml");
            System.out.println(expression);
            CompiledExpression compiledExpression = xQueryService.compile(expression);
            xQueryService.execute(compiledExpression);
            return true;
        } catch (XMLDBException e) {
            return false;
        }
    }
}

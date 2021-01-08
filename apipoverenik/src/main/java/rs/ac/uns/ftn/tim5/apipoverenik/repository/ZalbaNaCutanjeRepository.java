package rs.ac.uns.ftn.tim5.apipoverenik.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;
import rs.ac.uns.ftn.tim5.apipoverenik.helper.DbConnection;
import rs.ac.uns.ftn.tim5.apipoverenik.model.zalba_na_odluku.ZalbaNaOdluku;
import rs.ac.uns.ftn.tim5.apipoverenik.model.zalbe_cutanja.ZalbaCutanja;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static rs.ac.uns.ftn.tim5.apipoverenik.helper.XQueryExpressions.*;
import static rs.ac.uns.ftn.tim5.apipoverenik.helper.XQueryExpressions.X_UPDATE_REMOVE_ZALBA_NA_ODLUKU_BY_ID_EXPRESSION;

public class ZalbaNaCutanjeRepository {

    static final String collectionId = "/db/sample/zalbe_cutanja";
    static final String jaxbContextPath = "rs.ac.uns.ftn.tim5.apipoverenik.model.zalbe_cutanja";

    @Autowired
    DbConnection dbConnection;

    public List<ZalbaCutanja> getAllZalbeCutanja() {
        ArrayList<ZalbaCutanja> zalbeCutanja = new ArrayList<>();
        Collection collection = dbConnection.getCollection(collectionId);
        try {
            XQueryService xQueryService = (XQueryService) collection.getService("XQueryService", "1.0");
            CompiledExpression compiledExpression = xQueryService.compile(X_QUERY_FIND_ALL_ZALBE_CUTANJA_EXPRESSION);
            ResourceSet resourceSet = xQueryService.execute(compiledExpression);
            ResourceIterator resourceIterator = resourceSet.getIterator();
            while (resourceIterator.hasMoreResources()){
                XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
                JAXBContext context = JAXBContext.newInstance(jaxbContextPath);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                ZalbaCutanja zalbaCutanja = (ZalbaCutanja) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
                zalbeCutanja.add(zalbaCutanja);
            }
        } catch (XMLDBException | JAXBException e) {
            e.printStackTrace();
        }
        return zalbeCutanja;
    }

    public ZalbaCutanja getZalbaCutanja(long idZalbeCutanja) {
        Collection collection = dbConnection.getCollection(collectionId);
        try {
            XQueryService xQueryService = (XQueryService) collection.getService("XQueryService", "1.0");
            CompiledExpression compiledExpression = xQueryService.compile(String.format(X_QUERY_FIND_ZALBA_CUTANJA_BY_ID_EXPRESSION, idZalbeCutanja));
            ResourceSet resourceSet = xQueryService.execute(compiledExpression);
            ResourceIterator resourceIterator = resourceSet.getIterator();
            while (resourceIterator.hasMoreResources()){
                XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
                JAXBContext context = JAXBContext.newInstance(jaxbContextPath);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                ZalbaCutanja zalbaCutanja = (ZalbaCutanja) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
                return zalbaCutanja;
            }
        } catch (XMLDBException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long createZalbaCutanja(ZalbaCutanja zalbaCutanja) {
        Long id = UUID.randomUUID().getLeastSignificantBits() * -1;
        zalbaCutanja.setId(id);
        Collection collection = dbConnection.getCollection(collectionId);
        OutputStream os = new ByteArrayOutputStream();
        try {
            XMLResource xmlResource = (XMLResource) collection.createResource(id.toString() + ".xml", XMLResource.RESOURCE_TYPE);
            JAXBContext context = JAXBContext.newInstance(jaxbContextPath);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // marshal the contents to an output stream
            marshaller.marshal(zalbaCutanja, os);
            xmlResource.setContent(os);

            collection.storeResource(xmlResource);
            return id;
        } catch (XMLDBException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateZalbaCutanja(ZalbaCutanja zalbaCutanja) {
        Collection collection = dbConnection.getCollection(collectionId);
        StringWriter sw = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(jaxbContextPath);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(zalbaCutanja, sw);
            String[] xmlFragments = sw.toString().split("\n");
            String[] xmlFragmentsWithoutWrapper = Arrays.copyOfRange(xmlFragments, 2, xmlFragments.length - 1);
            String xmlFragment = String.join("\n", xmlFragmentsWithoutWrapper);

            XQueryService xQueryService = (XQueryService) collection.getService("XQueryService", "1.0");
            CompiledExpression compiledExpression = xQueryService.compile(String.format(X_UPDATE_UPDATE_ZALBA_CUTANJA_BY_ID_EXPRESSION, zalbaCutanja.getId() + ".xml", xmlFragment));
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

    public boolean deleteZalbaNaOdluku(long idZalbeNaOdluku) {
        Collection collection = dbConnection.getCollection(collectionId);
        try {
            XQueryService xQueryService = (XQueryService) collection.getService("XQueryService", "1.0");
            String expression = String.format(X_UPDATE_REMOVE_ZALBA_NA_ODLUKU_BY_ID_EXPRESSION, idZalbeNaOdluku + ".xml");
            System.out.println(expression);
            CompiledExpression compiledExpression = xQueryService.compile(expression);
            xQueryService.execute(compiledExpression);
            return true;
        } catch (XMLDBException e) {
            return false;
        }
    }
}

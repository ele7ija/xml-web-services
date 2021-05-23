package rs.ac.uns.ftn.tim5.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;
import rs.ac.uns.ftn.tim5.helper.DbConnection;
import rs.ac.uns.ftn.tim5.helper.XmlConversionAgent;
import rs.ac.uns.ftn.tim5.interfaces.Identifiable;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Repository
public class AbstractXmlRepository<T extends Identifiable> {

    private String collectionId;
    public String jaxbContextPath;
    private String X_QUERY_FIND_ALL_ENTITIES;
    private String X_UPDATE_REMOVE_ENTITY_BY_ID_EXPRESSION;

    @Autowired
    DbConnection dbConnection;

    @Autowired
    XmlConversionAgent<T> xmlConversionAgent;

    public List<T> getAllEntities() throws XMLDBException, JAXBException {
        ArrayList<T> entities = new ArrayList<>();
        Collection collection = this.dbConnection.getCollection(this.collectionId);
        XQueryService xQueryService = (XQueryService) collection.getService("XQueryService", "1.0");
        CompiledExpression compiledExpression = xQueryService.compile(this.X_QUERY_FIND_ALL_ENTITIES);
        ResourceSet resourceSet = xQueryService.execute(compiledExpression);
        ResourceIterator resourceIterator = resourceSet.getIterator();
        while (resourceIterator.hasMoreResources()) {
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            entities.add(this.xmlConversionAgent.unmarshall(xmlResource.getContentAsDOM(), this.jaxbContextPath));
        }
        return entities;
    }

    public T getEntity(long entityId) throws XMLDBException, JAXBException {
        Collection collection = this.dbConnection.getCollection(this.collectionId);
        Long id = new Long(entityId);
        XMLResource xmlResource = (XMLResource) collection.getResource(id.toString() + ".xml");
        if (xmlResource == null) {
            return null;
        }
        return this.xmlConversionAgent.unmarshall(xmlResource.getContentAsDOM(), this.jaxbContextPath);
    }

    public T findEntity(String query, String param) throws XMLDBException, JAXBException {
        Collection collection = this.dbConnection.getCollection(this.collectionId);
        XQueryService xQueryService = (XQueryService) collection.getService("XQueryService", "1.0");
        CompiledExpression compiledExpression = xQueryService.compile(String.format(query, param));
        ResourceSet resourceSet = xQueryService.execute(compiledExpression);
        ResourceIterator resourceIterator = resourceSet.getIterator();
        while (resourceIterator.hasMoreResources()) {
            XMLResource xmlResource = (XMLResource) resourceIterator.nextResource();
            return (T) this.xmlConversionAgent.unmarshall(xmlResource.getContentAsDOM(), this.jaxbContextPath);
        }
        return null;
    }

    public T createEntity(T entity) throws XMLDBException, JAXBException {
        Long id = UUID.randomUUID().getLeastSignificantBits() * -1;
        entity.setId(id);
        Collection collection = this.dbConnection.getCollection(this.collectionId);
        XMLResource xmlResource = (XMLResource) collection.createResource(id.toString() + ".xml", XMLResource.RESOURCE_TYPE);
        xmlResource.setContent(this.xmlConversionAgent.marshallToOutputStream(entity, this.jaxbContextPath));
        System.out.println(this.xmlConversionAgent.marshall(entity, this.jaxbContextPath));
        collection.storeResource(xmlResource);
        return this.getEntity(id);
    }

    public T createEntityWithGivenId(T entity, Long id) throws XMLDBException, JAXBException {
        entity.setId(id);
        Collection collection = this.dbConnection.getCollection(this.collectionId);
        XMLResource xmlResource = (XMLResource) collection.createResource(id.toString() + ".xml", XMLResource.RESOURCE_TYPE);
        xmlResource.setContent(this.xmlConversionAgent.marshallToOutputStream(entity, this.jaxbContextPath));
        collection.storeResource(xmlResource);
        return this.getEntity(id);
    }

    public boolean updateEntity(T entity) throws XMLDBException, JAXBException {
        if (this.getEntity(entity.getId()) == null) {
            return false;
        }
        this.deleteEntity(entity.getId());
        this.createEntityWithGivenId(entity, entity.getId());
        return true;
    }

    public boolean deleteEntity(long entityId) throws XMLDBException {
        Collection collection = this.dbConnection.getCollection(this.collectionId);
        XQueryService xQueryService = (XQueryService) collection.getService("XQueryService", "1.0");
        String expression = String.format(this.X_UPDATE_REMOVE_ENTITY_BY_ID_EXPRESSION, entityId + ".xml");
        CompiledExpression compiledExpression = xQueryService.compile(expression);
        xQueryService.execute(compiledExpression);
        return true;

    }

    public void injectRepositoryProperties(
            String collectionId,
            String jaxbContextPath,
            String X_QUERY_FIND_ALL_ENTITIES,
            String X_UPDATE_REMOVE_ENTITY_BY_ID_EXPRESSION
    ) {

        this.collectionId = collectionId;
        this.jaxbContextPath = jaxbContextPath;
        this.X_QUERY_FIND_ALL_ENTITIES = X_QUERY_FIND_ALL_ENTITIES;
        this.X_UPDATE_REMOVE_ENTITY_BY_ID_EXPRESSION = X_UPDATE_REMOVE_ENTITY_BY_ID_EXPRESSION;
    }
}

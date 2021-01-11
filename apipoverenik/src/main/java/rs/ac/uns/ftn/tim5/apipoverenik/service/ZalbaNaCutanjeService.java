package rs.ac.uns.ftn.tim5.apipoverenik.service;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import rs.ac.uns.ftn.tim5.apipoverenik.config.RDFDBConnectionProperties;
import rs.ac.uns.ftn.tim5.apipoverenik.helper.MetadataExtractor;
import rs.ac.uns.ftn.tim5.apipoverenik.helper.XQueryExpressions;
import rs.ac.uns.ftn.tim5.apipoverenik.helper.XmlConversionAgent;
import rs.ac.uns.ftn.tim5.apipoverenik.model.exception.EntityNotFoundException;
import rs.ac.uns.ftn.tim5.apipoverenik.model.exception.InvalidXmlDatabaseException;
import rs.ac.uns.ftn.tim5.apipoverenik.model.exception.InvalidXmlException;
import rs.ac.uns.ftn.tim5.apipoverenik.model.exception.XmlDatabaseException;
import rs.ac.uns.ftn.tim5.apipoverenik.model.zalbe_cutanja.ZalbaCutanja;
import rs.ac.uns.ftn.tim5.apipoverenik.repository.AbstractXmlRepository;
import rs.ac.uns.ftn.tim5.apipoverenik.util.SparqlUtil;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.TransformerException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import static rs.ac.uns.ftn.tim5.apipoverenik.helper.XQueryExpressions.*;

@Service
public class ZalbaNaCutanjeService implements AbstractXmlService<ZalbaCutanja> {

    private final String jaxbContextPath = "rs.ac.uns.ftn.tim5.apipoverenik.model.zalbe_cutanja";

    private static final String SPARQL_NAMED_GRAPH_URI = "/zalbacutanje/sparql/metadata";

    @Autowired
    @Qualifier("zalbaCutanjaRepository")
    private AbstractXmlRepository<ZalbaCutanja> zalbaCutanjaAbstractXmlRepository;

    @Autowired
    private XmlConversionAgent<ZalbaCutanja> zalbaCutanjaXmlConversionAgent;

    @Autowired 
    private RDFService rdfService;

    @PostConstruct
    public void injectRepositoryProperties(){
        this.zalbaCutanjaAbstractXmlRepository.injectRepositoryProperties(
                "/db/sample/zalbe_cutanja",
                this.jaxbContextPath,
                X_QUERY_FIND_ALL_ZALBE_CUTANJA_EXPRESSION,
                X_QUERY_FIND_ZALBA_CUTANJA_BY_ID_EXPRESSION,
                X_UPDATE_UPDATE_ZALBA_CUTANJA_BY_ID_EXPRESSION,
                X_UPDATE_REMOVE_ZALBA_CUTANJA_BY_ID_EXPRESSION
        );
        System.out.println(this.zalbaCutanjaAbstractXmlRepository.jaxbContextPath);
    }

    @Override
    public List<ZalbaCutanja> findAll() {
        System.out.println(this.zalbaCutanjaAbstractXmlRepository.jaxbContextPath);
        try {
            return this.zalbaCutanjaAbstractXmlRepository.getAllEntities();
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(ZalbaCutanja.class, e.getMessage());
        }
    }

    @Override
    public ZalbaCutanja findById(Long entityId) {
        try {
            ZalbaCutanja zalbaCutanja = this.zalbaCutanjaAbstractXmlRepository.getEntity(entityId);
            if(zalbaCutanja == null){
                throw new EntityNotFoundException(entityId, ZalbaCutanja.class);
            }
            return zalbaCutanja;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(ZalbaCutanja.class, e.getMessage());
        }
    }

    @Override
    public ZalbaCutanja create(String xmlEntity) {

        ZalbaCutanja zalbaCutanja;
        try{
            zalbaCutanja = this.zalbaCutanjaXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
        }catch(JAXBException e){
            throw new InvalidXmlException(ZalbaCutanja.class, e.getMessage());
        }

        try {
            zalbaCutanja = zalbaCutanjaAbstractXmlRepository.createEntity(zalbaCutanja);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(ZalbaCutanja.class, e.getMessage());
        }

        // Sacuvaj u RDF
        if (!rdfService.save(xmlEntity, SPARQL_NAMED_GRAPH_URI)) {
            System.out.println("[ERROR] Neuspesno cuvanje metapodataka zalbe cutanja u RDF DB.");
        };

        return zalbaCutanja;
    }

    @Override
    public ZalbaCutanja update(String xmlEntity) {
        ZalbaCutanja zalbaCutanja;
        try{
            zalbaCutanja = this.zalbaCutanjaXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
        }catch(JAXBException e){
            throw new InvalidXmlException(ZalbaCutanja.class, e.getMessage());
        }

        try {
            if(this.zalbaCutanjaAbstractXmlRepository.updateEntity(zalbaCutanja))
                return zalbaCutanja;
            else{
                throw new EntityNotFoundException(zalbaCutanja.getId(), ZalbaCutanja.class);
            }
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(ZalbaCutanja.class, e.getMessage());
        }
    }

    @Override
    public boolean deleteById(Long entityId) {
        try {
            return this.zalbaCutanjaAbstractXmlRepository.deleteEntity(entityId);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        }
    }
}

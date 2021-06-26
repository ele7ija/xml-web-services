package rs.ac.uns.ftn.tim5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import rs.ac.uns.ftn.tim5.helper.XmlConversionAgent;
import rs.ac.uns.ftn.tim5.model.exception.EntityNotFoundException;
import rs.ac.uns.ftn.tim5.model.exception.InvalidXmlDatabaseException;
import rs.ac.uns.ftn.tim5.model.exception.InvalidXmlException;
import rs.ac.uns.ftn.tim5.model.exception.XmlDatabaseException;
import rs.ac.uns.ftn.tim5.model.zalba_cutanja.ZalbaCutanja;
import rs.ac.uns.ftn.tim5.model.zalba_na_odluku.ZalbaNaOdluku;
import rs.ac.uns.ftn.tim5.repository.AbstractXmlRepository;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.util.List;

import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.X_QUERY_FIND_ALL_ZALBE_NA_ODLUKU_EXPRESSION;
import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.X_UPDATE_REMOVE_ZALBA_NA_ODLUKU_BY_ID_EXPRESSION;



@Service
public class ZalbaNaOdlukuService implements AbstractXmlService<ZalbaNaOdluku> {

    private final String jaxbContextPath = "rs.ac.uns.ftn.tim5.model.zalba_na_odluku";

    private static final String SPARQL_NAMED_GRAPH_URI = "/zalba_na_odluku/sparql/metadata";

    @Autowired
    @Qualifier("zalbaNaOdlukuRepository")
    private AbstractXmlRepository<ZalbaNaOdluku> zalbaNaOdlukuAbstractXmlRepository;

    @Autowired
    private XmlConversionAgent<ZalbaNaOdluku> zalbaNaOdlukuXmlConversionAgent;

    @Autowired 
    private RDFService rdfService;

    @Autowired
    private UUIDHelper uuidHelper;

    @PostConstruct
    public void injectRepositoryProperties(){
        this.zalbaNaOdlukuAbstractXmlRepository.injectRepositoryProperties(
                "/db/sample/zalba_na_odluku",
                this.jaxbContextPath,
                X_QUERY_FIND_ALL_ZALBE_NA_ODLUKU_EXPRESSION,
                X_UPDATE_REMOVE_ZALBA_NA_ODLUKU_BY_ID_EXPRESSION
        );
    }

    @Override
    public List<ZalbaNaOdluku> findAll() {
        try {
            return this.zalbaNaOdlukuAbstractXmlRepository.getAllEntities();
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(ZalbaNaOdluku.class, e.getMessage());
        }
    }

    @Override
    public ZalbaNaOdluku findById(Long entityId) {
        try {
            ZalbaNaOdluku zalbaNaOdluku = this.zalbaNaOdlukuAbstractXmlRepository.getEntity(entityId);
            if(zalbaNaOdluku == null){
                throw new EntityNotFoundException(entityId, ZalbaNaOdluku.class);
            }
            return zalbaNaOdluku;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(ZalbaNaOdluku.class, e.getMessage());
        }
    }

    @Override
    public ZalbaNaOdluku create(String xmlEntity) {
        ZalbaNaOdluku zalbaNaOdluku;
        try{
            zalbaNaOdluku = this.zalbaNaOdlukuXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
        }catch(JAXBException e){
            throw new InvalidXmlException(ZalbaNaOdluku.class, e.getMessage());
        }

        try {
            zalbaNaOdluku = zalbaNaOdlukuAbstractXmlRepository.createEntity(zalbaNaOdluku);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(ZalbaNaOdluku.class, e.getMessage());
        }

        if (!rdfService.save(xmlEntity, SPARQL_NAMED_GRAPH_URI)) {
            System.out.println("[ERROR] Neuspesno cuvanje metapodataka zalbe na odluku u RDF DB.");
        }

        return zalbaNaOdluku;
    }

    public ZalbaNaOdluku create(ZalbaNaOdluku zalbaNaOdluku) {

        try {
            zalbaNaOdluku = zalbaNaOdlukuAbstractXmlRepository.createEntity(zalbaNaOdluku);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(ZalbaNaOdluku.class, e.getMessage());
        }

        try {
            String xmlEntity = this.zalbaNaOdlukuXmlConversionAgent.marshall(zalbaNaOdluku, this.jaxbContextPath);
            if (!rdfService.save(xmlEntity, SPARQL_NAMED_GRAPH_URI)) {
                System.out.println("[ERROR] Neuspesno cuvanje metapodataka obavestenja u RDF DB.");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return zalbaNaOdluku;
    }

    @Override
    public ZalbaNaOdluku update(String xmlEntity) {
        ZalbaNaOdluku zalbaNaOdluku;
        try{
            zalbaNaOdluku = this.zalbaNaOdlukuXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
        }catch(JAXBException e){
            throw new InvalidXmlException(ZalbaNaOdluku.class, e.getMessage());
        }

        try {
            if (!this.zalbaNaOdlukuAbstractXmlRepository.updateEntity(zalbaNaOdluku)) {
                throw new EntityNotFoundException(zalbaNaOdluku.getId(), ZalbaCutanja.class);
            }
            return zalbaNaOdluku;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(ZalbaNaOdluku.class, e.getMessage());
        }
    }

    @Override
    public boolean deleteById(Long entityId) {
        try {
            return this.zalbaNaOdlukuAbstractXmlRepository.deleteEntity(entityId);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        }
    }
}

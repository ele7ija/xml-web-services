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
import rs.ac.uns.ftn.tim5.model.obavestenje.Obavestenje;
import rs.ac.uns.ftn.tim5.repository.AbstractXmlRepository;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.util.List;

import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.X_QUERY_FIND_ALL_OBAVESTENJA_EXPRESSION;
import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.X_UPDATE_REMOVE_OBAVESTENJE_BY_ID_EXPRESSION;

@Service
public class ObavestenjeService implements AbstractXmlService<Obavestenje> {

    private final String jaxbContextPath = "rs.ac.uns.ftn.tim5.model.obavestenje";

    private static final String SPARQL_NAMED_GRAPH_URI = "/obavestenje/sparql/metadata";

    @Autowired
    @Qualifier("obavestenjeRepository")
    private AbstractXmlRepository<Obavestenje> obavestenjeAbstractXmlRepository;

    @Autowired
    private XmlConversionAgent<Obavestenje> obavestenjeXmlConversionAgent;

    @Autowired
    private RDFService rdfService;

    @PostConstruct
    public void injectRepositoryProperties() {
        this.obavestenjeAbstractXmlRepository.injectRepositoryProperties(
                "/db/sample/obavestenje",
                jaxbContextPath,
                X_QUERY_FIND_ALL_OBAVESTENJA_EXPRESSION,
                X_UPDATE_REMOVE_OBAVESTENJE_BY_ID_EXPRESSION
        );
    }

    @Override
    public List<Obavestenje> findAll() {
        try {
            return this.obavestenjeAbstractXmlRepository.getAllEntities();
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Obavestenje.class, e.getMessage());
        }
    }

    @Override
    public Obavestenje findById(Long entityId) {
        try {
            Obavestenje obavestenje = this.obavestenjeAbstractXmlRepository.getEntity(entityId);
            if (obavestenje == null)
                throw new EntityNotFoundException(entityId, Obavestenje.class);
            return obavestenje;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Obavestenje.class, e.getMessage());
        }
    }

    @Override
    public Obavestenje create(String xmlEntity) {

        Obavestenje obavestenje;
        try {
            obavestenje = this.obavestenjeXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
        } catch (JAXBException e) {
            throw new InvalidXmlException(Obavestenje.class, e.getMessage());
        }

        try {
            obavestenje = obavestenjeAbstractXmlRepository.createEntity(obavestenje);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Obavestenje.class, e.getMessage());
        }

        // Sacuvaj u RDF
        if (!rdfService.save(xmlEntity, SPARQL_NAMED_GRAPH_URI)) {
            System.out.println("[ERROR] Neuspesno cuvanje metapodataka obavestenja u RDF DB.");
        }

        return obavestenje;
    }

    @Override
    public Obavestenje update(String xmlEntity) {
        Obavestenje obavestenje;
        try {
            obavestenje = this.obavestenjeXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
        } catch (JAXBException e) {
            throw new InvalidXmlException(Obavestenje.class, e.getMessage());
        }

        try {
            if (!this.obavestenjeAbstractXmlRepository.updateEntity(obavestenje)) {
                throw new EntityNotFoundException(obavestenje.getId(), Obavestenje.class);
            }
            return obavestenje;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Obavestenje.class, e.getMessage());
        }

    }

    @Override
    public boolean deleteById(Long entityId) {
        try {
            return this.obavestenjeAbstractXmlRepository.deleteEntity(entityId);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        }
    }

}

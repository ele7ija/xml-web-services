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
import rs.ac.uns.ftn.tim5.model.poverenik.Poverenik;
import rs.ac.uns.ftn.tim5.repository.AbstractXmlRepository;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.util.List;

import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.*;

@Service
public class PoverenikService implements AbstractXmlService<Poverenik> {

    private final String jaxbContextPath = "rs.ac.uns.ftn.tim5.model.poverenik";

    private static final String SPARQL_NAMED_GRAPH_URI = "/poverenik/sparql/metadata";

    @Autowired
    @Qualifier("poverenikRepository")
    private AbstractXmlRepository<Poverenik> poverenikAbstractXmlRepository;

    @Autowired
    private XmlConversionAgent<Poverenik> poverenikXmlConversionAgent;

    @Autowired
    private RDFService rdfService;

    @Autowired
    private UUIDHelper uuidHelper;

    @PostConstruct
    public void injectRepositoryProperties() {
        this.poverenikAbstractXmlRepository.injectRepositoryProperties(
                "/db/sample/poverenik",
                jaxbContextPath,
                X_QUERY_FIND_ALL_POVERENICI_EXPRESSION,
                X_UPDATE_REMOVE_POVERENIK_BY_ID_EXPRESSION
        );
    }

    @Override
    public List<Poverenik> findAll() {
        try {
            return this.poverenikAbstractXmlRepository.getAllEntities();
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Poverenik.class, e.getMessage());
        }
    }

    @Override
    public Poverenik findById(Long entityId) {
        try {
            Poverenik poverenik = this.poverenikAbstractXmlRepository.getEntity(entityId);
            if (poverenik == null)
                throw new EntityNotFoundException(entityId, Poverenik.class);
            return poverenik;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Poverenik.class, e.getMessage());
        }
    }

    @Override
    public Poverenik create(String xmlEntity) {

        Poverenik poverenik;
        try {
            poverenik = this.poverenikXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
            poverenik.setId(this.uuidHelper.getUUID());
        } catch (JAXBException e) {
            throw new InvalidXmlException(Poverenik.class, e.getMessage());
        }

        try {
            poverenik = poverenikAbstractXmlRepository.createEntity(poverenik);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Poverenik.class, e.getMessage());
        }

        // Sacuvaj u RDF
        if (!rdfService.save(xmlEntity, SPARQL_NAMED_GRAPH_URI)) {
            System.out.println("[ERROR] Neuspesno cuvanje metapodataka poverenika u RDF DB.");
        }

        return poverenik;
    }

    @Override
    public Poverenik update(String xmlEntity) {
        Poverenik poverenik;
        try {
            poverenik = this.poverenikXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
        } catch (JAXBException e) {
            throw new InvalidXmlException(Poverenik.class, e.getMessage());
        }

        try {
            if (!this.poverenikAbstractXmlRepository.updateEntity(poverenik)) {
                throw new EntityNotFoundException(poverenik.getId(), Poverenik.class);
            }
            return poverenik;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Poverenik.class, e.getMessage());
        }

    }

    @Override
    public boolean deleteById(Long entityId) {
        try {
            return this.poverenikAbstractXmlRepository.deleteEntity(entityId);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        }
    }

    public Poverenik findByUsername(String username) {
        try {
            return this.poverenikAbstractXmlRepository.findEntity(X_QUERY_FIND_POVERENIK_BY_KORISNICKO_IME, username);
        } catch (XMLDBException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}

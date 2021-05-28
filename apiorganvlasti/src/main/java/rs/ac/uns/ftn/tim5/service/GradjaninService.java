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
import rs.ac.uns.ftn.tim5.model.gradjanin.Gradjanin;
import rs.ac.uns.ftn.tim5.repository.AbstractXmlRepository;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.util.List;

import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.X_QUERY_FIND_ALL_GRADJANI_EXPRESSION;
import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.X_QUERY_FIND_GRADJANIN_BY_KORISNICKO_IME;
import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.X_UPDATE_REMOVE_GRADJANIN_BY_ID_EXPRESSION;


@Service
public class GradjaninService implements AbstractXmlService<Gradjanin> {

    private final String jaxbContextPath = "rs.ac.uns.ftn.tim5.model.gradjanin";

    private static final String SPARQL_NAMED_GRAPH_URI = "/gradjanin/sparql/metadata";

    @Autowired
    @Qualifier("gradjaninRepository")
    private AbstractXmlRepository<Gradjanin> gradjaninAbstractXmlRepository;

    @Autowired
    private XmlConversionAgent<Gradjanin> gradjaninXmlConversionAgent;

    @Autowired
    private RDFService rdfService;

    @Autowired
    private UUIDHelper uuidHelper;

    @PostConstruct
    public void injectRepositoryProperties() {
        this.gradjaninAbstractXmlRepository.injectRepositoryProperties(
                "/db/sample/gradjanin",
                jaxbContextPath,
                X_QUERY_FIND_ALL_GRADJANI_EXPRESSION,
                X_UPDATE_REMOVE_GRADJANIN_BY_ID_EXPRESSION
        );
    }

    @Override
    public List<Gradjanin> findAll() {
        try {
            return this.gradjaninAbstractXmlRepository.getAllEntities();
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Gradjanin.class, e.getMessage());
        }
    }

    @Override
    public Gradjanin findById(Long entityId) {
        try {
            Gradjanin gradjanin = this.gradjaninAbstractXmlRepository.getEntity(entityId);
            if (gradjanin == null)
                throw new EntityNotFoundException(entityId, Gradjanin.class);
            return gradjanin;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Gradjanin.class, e.getMessage());
        }
    }

    @Override
    public Gradjanin create(String xmlEntity) {
        Gradjanin gradjanin;
        try {
            gradjanin = this.gradjaninXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
            gradjanin.setId(this.uuidHelper.getUUID());
        } catch (JAXBException e) {
            throw new InvalidXmlException(Gradjanin.class, e.getMessage());
        }

        try {
            gradjanin = gradjaninAbstractXmlRepository.createEntity(gradjanin);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Gradjanin.class, e.getMessage());
        }

        // Sacuvaj u RDF
        if (!rdfService.save(xmlEntity, SPARQL_NAMED_GRAPH_URI)) {
            System.out.println("[ERROR] Neuspesno cuvanje metapodataka gradjanina u RDF DB.");
        }

        return gradjanin;
    }

    @Override
    public Gradjanin update(String xmlEntity) {
        Gradjanin gradjanin;
        try {
            gradjanin = this.gradjaninXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
        } catch (JAXBException e) {
            throw new InvalidXmlException(Gradjanin.class, e.getMessage());
        }

        try {
            if (!this.gradjaninAbstractXmlRepository.updateEntity(gradjanin)) {
                throw new EntityNotFoundException(gradjanin.getId(), Gradjanin.class);
            }
            return gradjanin;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Gradjanin.class, e.getMessage());
        }

    }

    @Override
    public boolean deleteById(Long entityId) {
        try {
            return this.gradjaninAbstractXmlRepository.deleteEntity(entityId);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        }
    }

    public Gradjanin findByUsername(String username) {
        try {
            return this.gradjaninAbstractXmlRepository.findEntity(X_QUERY_FIND_GRADJANIN_BY_KORISNICKO_IME, username);
        } catch (XMLDBException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}

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
import rs.ac.uns.ftn.tim5.model.resenje.Resenje;
import rs.ac.uns.ftn.tim5.repository.AbstractXmlRepository;
import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.util.List;
import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.X_QUERY_FIND_ALL_RESENJA_EXPRESSION;
import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.X_UPDATE_REMOVE_RESENJE_BY_ID_EXPRESSION;

@Service
public class ResenjeService implements AbstractXmlService<Resenje> {

    private final String jaxbContextPath = "rs.ac.uns.ftn.tim5.model.resenje";

    private static final String SPARQL_NAMED_GRAPH_URI = "/resenje/sparql/metadata";

    @Autowired
    @Qualifier("resenjeRepository")
    private AbstractXmlRepository<Resenje> resenjeAbstractXmlRepository;

    @Autowired
    private XmlConversionAgent<Resenje> resenjeXmlConversionAgent;

    @Autowired
    private RDFService rdfService;

    @Autowired
    private UUIDHelper uuidHelper;

    @PostConstruct
    public void injectRepositoryProperties() {
        this.resenjeAbstractXmlRepository.injectRepositoryProperties(
                "/db/sample/resenja",
                jaxbContextPath,
                X_QUERY_FIND_ALL_RESENJA_EXPRESSION,
                X_UPDATE_REMOVE_RESENJE_BY_ID_EXPRESSION
        );
    }

    @Override
    public List<Resenje> findAll() {
        try {
            return this.resenjeAbstractXmlRepository.getAllEntities();
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Resenje.class, e.getMessage());
        }
    }

    @Override
    public Resenje findById(Long entityId) {
        try {
            Resenje resenje = this.resenjeAbstractXmlRepository.getEntity(entityId);
            if (resenje == null)
                throw new EntityNotFoundException(entityId, Resenje.class);
            return resenje;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Resenje.class, e.getMessage());
        }
    }

    @Override
    public Resenje create(String xmlEntity) {

        Resenje resenje;
        try {
            resenje = this.resenjeXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
            resenje.setId(this.uuidHelper.getUUID());
            this.handleMetadata(resenje);
        } catch (JAXBException e) {
            throw new InvalidXmlException(Resenje.class, e.getMessage());
        }

        try {
            resenje = resenjeAbstractXmlRepository.createEntity(resenje);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Resenje.class, e.getMessage());
        }

        // Sacuvaj u RDF
        if (!rdfService.save(xmlEntity, SPARQL_NAMED_GRAPH_URI)) {
            System.out.println("[ERROR] Neuspesno cuvanje metapodataka resenja u RDF DB.");
        }

        return resenje;
    }


    public Resenje create(Resenje resenje) {

        try {
            resenje = resenjeAbstractXmlRepository.createEntity(resenje);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Resenje.class, e.getMessage());
        }

        // Sacuvaj u RDF
        try {
            String xmlEntity = this.resenjeXmlConversionAgent.marshall(resenje, this.jaxbContextPath);
            if (!rdfService.save(xmlEntity, SPARQL_NAMED_GRAPH_URI)) {
                System.out.println("[ERROR] Neuspesno cuvanje metapodataka obavestenja u RDF DB.");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return resenje;
    }

    @Override
    public Resenje update(String xmlEntity) {
        Resenje resenje;
        try {
            resenje = this.resenjeXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
        } catch (JAXBException e) {
            throw new InvalidXmlException(Resenje.class, e.getMessage());
        }

        try {
            if (!this.resenjeAbstractXmlRepository.updateEntity(resenje)) {
                throw new EntityNotFoundException(resenje.getId(), Resenje.class);
            }
            return resenje;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Resenje.class, e.getMessage());
        }

    }

    @Override
    public boolean deleteById(Long entityId) {
        try {
            return this.resenjeAbstractXmlRepository.deleteEntity(entityId);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        }
    }

    private void handleMetadata(Resenje resenje) {
        resenje.setAbout(
                String.format(
                        "%s%s%s",
                        System.getenv("FRONTEND_URL"),
                        "/",
                        resenje.getId()
                )
        );
    }

}

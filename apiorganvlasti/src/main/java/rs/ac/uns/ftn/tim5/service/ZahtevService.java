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
import rs.ac.uns.ftn.tim5.model.zahtev.Zahtev;
import rs.ac.uns.ftn.tim5.repository.AbstractXmlRepository;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.util.List;

import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.X_QUERY_FIND_ALL_ZAHTEVI_EXPRESSION;
import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.X_UPDATE_REMOVE_ZAHTEV_BY_ID_EXPRESSION;

@Service
public class ZahtevService implements AbstractXmlService<Zahtev> {

    private final String jaxbContextPath = "rs.ac.uns.ftn.tim5.model.zahtev";

    private static final String SPARQL_NAMED_GRAPH_URI = "/zahtev/sparql/metadata";

    @Autowired
    @Qualifier("zahtevRepository")
    private AbstractXmlRepository<Zahtev> zahtevAbstractXmlRepository;

    @Autowired
    private XmlConversionAgent<Zahtev> zahtevXmlConversionAgent;

    @Autowired
    private RDFService rdfService;

    @PostConstruct
    public void injectRepositoryProperties() {
        this.zahtevAbstractXmlRepository.injectRepositoryProperties(
                "/db/sample/zahtev",
                jaxbContextPath,
                X_QUERY_FIND_ALL_ZAHTEVI_EXPRESSION,
                X_UPDATE_REMOVE_ZAHTEV_BY_ID_EXPRESSION
        );
    }

    @Override
    public List<Zahtev> findAll() {
        try {
            return this.zahtevAbstractXmlRepository.getAllEntities();
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Zahtev.class, e.getMessage());
        }
    }

    @Override
    public Zahtev findById(Long entityId) {
        try {
            Zahtev zahtev = this.zahtevAbstractXmlRepository.getEntity(entityId);
            if (zahtev == null)
                throw new EntityNotFoundException(entityId, Zahtev.class);
            return zahtev;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Zahtev.class, e.getMessage());
        }
    }

    @Override
    public Zahtev create(String xmlEntity) {

        Zahtev zahtev;
        try {
            zahtev = this.zahtevXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
        } catch (JAXBException e) {
            throw new InvalidXmlException(Zahtev.class, e.getMessage());
        }

        try {
            zahtev = zahtevAbstractXmlRepository.createEntity(zahtev);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Zahtev.class, e.getMessage());
        }

        // Sacuvaj u RDF
        if (!rdfService.save(xmlEntity, SPARQL_NAMED_GRAPH_URI)) {
            System.out.println("[ERROR] Neuspesno cuvanje metapodataka zahteva u RDF DB.");
        }

        return zahtev;
    }

    @Override
    public Zahtev update(String xmlEntity) {
        Zahtev zahtev;
        try {
            zahtev = this.zahtevXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
        } catch (JAXBException e) {
            throw new InvalidXmlException(Zahtev.class, e.getMessage());
        }

        try {
            if (!this.zahtevAbstractXmlRepository.updateEntity(zahtev)) {
                throw new EntityNotFoundException(zahtev.getId(), Zahtev.class);
            }
            return zahtev;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Zahtev.class, e.getMessage());
        }

    }

    @Override
    public boolean deleteById(Long entityId) {
        try {
            return this.zahtevAbstractXmlRepository.deleteEntity(entityId);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        }
    }

}
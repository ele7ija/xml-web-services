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
import rs.ac.uns.ftn.tim5.model.sluzbenik.Sluzbenik;
import rs.ac.uns.ftn.tim5.repository.AbstractXmlRepository;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.util.List;

import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.X_QUERY_FIND_ALL_ZAHTEVI_EXPRESSION;
import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.X_UPDATE_REMOVE_ZAHTEV_BY_ID_EXPRESSION;

@Service
public class SluzbenikService implements AbstractXmlService<Sluzbenik> {

    private final String jaxbContextPath = "rs.ac.uns.ftn.tim5.model.sluzbenik";

    private static final String SPARQL_NAMED_GRAPH_URI = "/sluzbenik/sparql/metadata";

    @Autowired
    @Qualifier("sluzbenikRepository")
    private AbstractXmlRepository<Sluzbenik> sluzbenikAbstractXmlRepository;

    @Autowired
    private XmlConversionAgent<Sluzbenik> sluzbenikXmlConversionAgent;

    @Autowired
    private RDFService rdfService;

    @PostConstruct
    public void injectRepositoryProperties() {
        this.sluzbenikAbstractXmlRepository.injectRepositoryProperties(
                "/db/sample/sluzbenik",
                jaxbContextPath,
                X_QUERY_FIND_ALL_ZAHTEVI_EXPRESSION,
                X_UPDATE_REMOVE_ZAHTEV_BY_ID_EXPRESSION
        );
    }

    @Override
    public List<Sluzbenik> findAll() {
        try {
            return this.sluzbenikAbstractXmlRepository.getAllEntities();
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Sluzbenik.class, e.getMessage());
        }
    }

    @Override
    public Sluzbenik findById(Long entityId) {
        try {
            Sluzbenik sluzbenik = this.sluzbenikAbstractXmlRepository.getEntity(entityId);
            if (sluzbenik == null)
                throw new EntityNotFoundException(entityId, Sluzbenik.class);
            return sluzbenik;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Sluzbenik.class, e.getMessage());
        }
    }

    @Override
    public Sluzbenik create(String xmlEntity) {

        Sluzbenik sluzbenik;
        try {
            sluzbenik = this.sluzbenikXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
        } catch (JAXBException e) {
            throw new InvalidXmlException(Sluzbenik.class, e.getMessage());
        }

        try {
            sluzbenik = sluzbenikAbstractXmlRepository.createEntity(sluzbenik);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Sluzbenik.class, e.getMessage());
        }

        // Sacuvaj u RDF
        if (!rdfService.save(xmlEntity, SPARQL_NAMED_GRAPH_URI)) {
            System.out.println("[ERROR] Neuspesno cuvanje metapodataka sluzbenika u RDF DB.");
        }

        return sluzbenik;
    }

    @Override
    public Sluzbenik update(String xmlEntity) {
        Sluzbenik sluzbenik;
        try {
            sluzbenik = this.sluzbenikXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
        } catch (JAXBException e) {
            throw new InvalidXmlException(Sluzbenik.class, e.getMessage());
        }

        try {
            if (!this.sluzbenikAbstractXmlRepository.updateEntity(sluzbenik)) {
                throw new EntityNotFoundException(sluzbenik.getId(), Sluzbenik.class);
            }
            return sluzbenik;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Sluzbenik.class, e.getMessage());
        }

    }

    @Override
    public boolean deleteById(Long entityId) {
        try {
            return this.sluzbenikAbstractXmlRepository.deleteEntity(entityId);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        }
    }

}

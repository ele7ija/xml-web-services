package rs.ac.uns.ftn.tim5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import rs.ac.uns.ftn.tim5.helper.PretrageHelper;
import rs.ac.uns.ftn.tim5.helper.XQueryExpressions;
import rs.ac.uns.ftn.tim5.helper.XmlConversionAgent;
import rs.ac.uns.ftn.tim5.model.exception.EntityNotFoundException;
import rs.ac.uns.ftn.tim5.model.exception.InvalidXmlDatabaseException;
import rs.ac.uns.ftn.tim5.model.exception.InvalidXmlException;
import rs.ac.uns.ftn.tim5.model.exception.XmlDatabaseException;
import rs.ac.uns.ftn.tim5.model.izvestaj.Izvestaj;
import rs.ac.uns.ftn.tim5.model.resenje.Resenje;
import rs.ac.uns.ftn.tim5.model.zahtev.Zahtev;
import rs.ac.uns.ftn.tim5.repository.AbstractXmlRepository;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.util.*;

import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.X_QUERY_FIND_ALL_IZVESTAJI_EXPRESSION;
import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.X_UPDATE_REMOVE_IZVESTAJ_BY_ID_EXPRESSION;

@Service
public class IzvestajService implements AbstractXmlService<Izvestaj> {

    private final String jaxbContextPath = "rs.ac.uns.ftn.tim5.model.izvestaj";

    private static final String SPARQL_NAMED_GRAPH_URI = "/izvestaj/sparql/metadata";

    @Autowired
    @Qualifier("izvestajRepository")
    private AbstractXmlRepository<Izvestaj> izvestajAbstractXmlRepository;

    @Autowired
    private XmlConversionAgent<Izvestaj> izvestajXmlConversionAgent;

    @Autowired
    private RDFService rdfService;

    @Autowired
    private PretrageHelper pretrageHelper;

    @PostConstruct
    public void injectRepositoryProperties() {
        this.izvestajAbstractXmlRepository.injectRepositoryProperties(
                "/db/sample/izvestaj",
                jaxbContextPath,
                X_QUERY_FIND_ALL_IZVESTAJI_EXPRESSION,
                X_UPDATE_REMOVE_IZVESTAJ_BY_ID_EXPRESSION
        );
    }

    @Override
    public List<Izvestaj> findAll() {
        try {
            return this.izvestajAbstractXmlRepository.getAllEntities();
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Izvestaj.class, e.getMessage());
        }
    }

    @Override
    public Izvestaj findById(Long entityId) {
        try {
            Izvestaj izvestaj = this.izvestajAbstractXmlRepository.getEntity(entityId);
            if (izvestaj == null)
                throw new EntityNotFoundException(entityId, Izvestaj.class);
            return izvestaj;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Izvestaj.class, e.getMessage());
        }
    }

    @Override
    public Izvestaj create(String xmlEntity) {

        Izvestaj izvestaj;
        try {
            izvestaj = this.izvestajXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
        } catch (JAXBException e) {
            throw new InvalidXmlException(Izvestaj.class, e.getMessage());
        }

        try {
            izvestaj = izvestajAbstractXmlRepository.createEntity(izvestaj);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Izvestaj.class, e.getMessage());
        }

        // Sacuvaj u RDF
        if (!rdfService.save(xmlEntity, SPARQL_NAMED_GRAPH_URI)) {
            System.out.println("[ERROR] Neuspesno cuvanje metapodataka izvestaja u RDF DB.");
        }

        return izvestaj;
    }

    @Override
    public Izvestaj update(String xmlEntity) {
        Izvestaj izvestaj;
        try {
            izvestaj = this.izvestajXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
        } catch (JAXBException e) {
            throw new InvalidXmlException(Izvestaj.class, e.getMessage());
        }

        try {
            if (!this.izvestajAbstractXmlRepository.updateEntity(izvestaj)) {
                throw new EntityNotFoundException(izvestaj.getId(), Izvestaj.class);
            }
            return izvestaj;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Izvestaj.class, e.getMessage());
        }

    }

    @Override
    public boolean deleteById(Long entityId) {
        try {
            return this.izvestajAbstractXmlRepository.deleteEntity(entityId);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        }
    }

    public List<Izvestaj> pronadjiTerm(String term) {
        try {
            return this.izvestajAbstractXmlRepository.findEntities(String.format(XQueryExpressions.SEARCH_IZVESTAJI, term));
        } catch (XMLDBException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Izvestaj> pronadjiMetadata(String metadata) {
        String[] sts = metadata.split("\\*OR\\*");
        Set<Izvestaj> retval = new HashSet<>();
        for (String andsubstr : sts) {
            String[] andsubstrtokens = andsubstr.split("\\*AND\\*");
            System.out.println("Search by metadata AND substring: " + Arrays.toString(andsubstrtokens));
            Set<String> ids = pretrageHelper.searchMetadata(andsubstrtokens, SPARQL_NAMED_GRAPH_URI);

            ids.forEach((String s) -> {
                try {
                    retval.add(izvestajAbstractXmlRepository.getEntity(Long.parseLong(s)));
                } catch (XMLDBException | JAXBException e) {
                    e.printStackTrace();
                }
            });
        }

        return new ArrayList<>(retval);
    }
}

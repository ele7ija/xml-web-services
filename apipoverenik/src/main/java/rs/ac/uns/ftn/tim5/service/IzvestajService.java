package rs.ac.uns.ftn.tim5.service;

import org.apache.commons.io.FileUtils;
import org.apache.jena.sparql.resultset.ResultsFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import rs.ac.uns.ftn.tim5.helper.XmlConversionAgent;
import rs.ac.uns.ftn.tim5.model.exception.EntityNotFoundException;
import rs.ac.uns.ftn.tim5.model.exception.InvalidXmlDatabaseException;
import rs.ac.uns.ftn.tim5.model.exception.InvalidXmlException;
import rs.ac.uns.ftn.tim5.model.exception.XmlDatabaseException;
import rs.ac.uns.ftn.tim5.model.izvestaj.Izvestaj;
import rs.ac.uns.ftn.tim5.model.obavestenje.Obavestenje;
import rs.ac.uns.ftn.tim5.repository.AbstractXmlRepository;
import rs.ac.uns.ftn.tim5.transformation.XSLFOTransformer;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.X_QUERY_FIND_ALL_IZVESTAJI_EXPRESSION;
import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.X_UPDATE_REMOVE_IZVESTAJ_BY_ID_EXPRESSION;

@Service
public class IzvestajService implements AbstractXmlService<Izvestaj> {

    private final String jaxbContextPath = "rs.ac.uns.ftn.tim5.model.izvestaj";

    private static final String SPARQL_NAMED_GRAPH_URI = "/izvestaj/sparql/metadata";

    public static final String OUTPUT_FOLDER_XML = "output_xml";
    public static final String OUTPUT_FOLDER_PDF = "output_pdf";
    public static final String OUTPUT_FOLDER_HTML = "output_html";
    public static final String OUTPUT_FOLDER_METADATA = "output_metadata";

    @Autowired
    @Qualifier("izvestajRepository")
    private AbstractXmlRepository<Izvestaj> izvestajAbstractXmlRepository;

    @Autowired
    private XmlConversionAgent<Izvestaj> izvestajXmlConversionAgent;

    @Autowired
    private RDFService rdfService;

    @Autowired
    private UUIDHelper uuidHelper;

    @Autowired
    private DateHelper dateHelper;

    @Autowired
    private SparqlUtil sparqlUtil;

    private XSLFOTransformer XSLFOTransformer;

    @PostConstruct
    public void injectRepositoryProperties() {
        this.izvestajAbstractXmlRepository.injectRepositoryProperties(
                "/db/sample/izvestaj",
                jaxbContextPath,
                X_QUERY_FIND_ALL_IZVESTAJI_EXPRESSION,
                X_UPDATE_REMOVE_IZVESTAJ_BY_ID_EXPRESSION
        );

        this.XSLFOTransformer = new XSLFOTransformer();
        try {
            this.XSLFOTransformer.injectTransformerProperties(
                    "classpath:transformations/xsl/izvestaj.xsl",
                    "classpath:transformations/xsl_fo/izvestaj_fo.xsl",
                    "output_pdf/izvestaj.pdf",
                    "output_html/izvestaj.html"
            );
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
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

    public Izvestaj create(Izvestaj izvestaj) {
        izvestaj.setId(this.uuidHelper.getUUID());
        this.handleMetadata(izvestaj);

        try {
            izvestaj = izvestajAbstractXmlRepository.createEntity(izvestaj);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Obavestenje.class, e.getMessage());
        }

        // Sacuvaj u RDF
        try {
            String xmlEntity = this.izvestajXmlConversionAgent.marshall(izvestaj, this.jaxbContextPath);
            if (!rdfService.save(xmlEntity, SPARQL_NAMED_GRAPH_URI)) {
                System.out.println("[ERROR] Neuspesno cuvanje metapodataka obavestenja u RDF DB.");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return izvestaj;
    }

    private void handleMetadata(Izvestaj izvestaj) {
        izvestaj.setAbout(
                String.format(
                        "%s%s%s",
                        System.getenv("FRONTEND_URL"),
                        "/izvestaj/",
                        izvestaj.getId()
                )
        );
        izvestaj.getDatumPodnosenja().setContent(this.dateHelper.toDate(izvestaj.getDatumPodnosenja()));
        izvestaj.setId(izvestaj.getId());

        izvestaj.setVocab("http://ftn.uns.ac.rs.tim5/model/predicate");
        izvestaj.getDatumPodnosenja().setProperty("pred:datum");
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

    public ByteArrayInputStream generatePdf(Long id) {
        Izvestaj izvestaj = this.findById(id);
        try {
            this.izvestajXmlConversionAgent.marshallToFile(izvestaj, this.jaxbContextPath,this.getXmlFilePath());
            this.XSLFOTransformer.generatePDF(this.getXmlFilePath());

        } catch (JAXBException | SAXException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error converting document to pdf.");
        }

        try {
            return new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(this.getPdfFilePath())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ByteArrayInputStream generateHtml(Long id) {
        Izvestaj izvestaj = this.findById(id);
        try {
            this.izvestajXmlConversionAgent.marshallToFile(izvestaj, this.jaxbContextPath,this.getXmlFilePath());
            this.XSLFOTransformer.generateHTML(this.getXmlFilePath());

        } catch (JAXBException | SAXException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error converting document to pdf.");
        }

        try {
            return new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(this.getHtmlFilePath())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getXmlFilePath() {
        String sep = System.getProperty("file.separator");
        return String.format(".%s%s%sizvestaj.xml", sep, OUTPUT_FOLDER_XML, sep);
    }

    public String getPdfFilePath() {
        String sep = System.getProperty("file.separator");
        return String.format(".%s%s%sizvestaj.pdf", sep, OUTPUT_FOLDER_PDF, sep);
    }

    private String getHtmlFilePath() {
        String sep = System.getProperty("file.separator");
        return String.format(".%s%s%sizvestaj.html", sep, OUTPUT_FOLDER_HTML, sep);
    }

    public ByteArrayInputStream exportMetadataAsJson(Long zahtevId) {
        String sparqlQuery = this.sparqlUtil.selectPredicateAndObject(
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format("<%s/izvestaj/%d>  ?p  ?o", System.getenv("FRONTEND_URL"), zahtevId));
        String sep = System.getProperty("file.separator");
        String filePath = String.format(".%s%s%sizvestaj_metadata.json", sep, OUTPUT_FOLDER_METADATA, sep);
        this.rdfService.runAndExportInGivenFormat(
                sparqlQuery,
                filePath,
                ResultsFormat.FMT_RS_JSON
        );
        try {
            return new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ByteArrayInputStream exportMetadataAsXml(Long zahtevId) {
        String sparqlQuery = this.sparqlUtil.selectPredicateAndObject(
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format("<%s/izvestaj/%d>  ?p  ?o", System.getenv("FRONTEND_URL"), zahtevId));
        String sep = System.getProperty("file.separator");
        String filePath = String.format(".%s%s%sizvestaj_metadata.xml", sep, OUTPUT_FOLDER_METADATA, sep);
        this.rdfService.runAndExportInGivenFormat(
                sparqlQuery,
                filePath,
                ResultsFormat.FMT_RS_XML
        );
        try {
            return new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ByteArrayInputStream exportMetadataAsRdf(Long zahtevId) {
        String sparqlQuery = this.sparqlUtil.describe(
                String.format("%s/izvestaj/%d", System.getenv("FRONTEND_URL"), zahtevId),
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format("<%s/izvestaj/%d>  ?p  ?o", System.getenv("FRONTEND_URL"), zahtevId));
        String sep = System.getProperty("file.separator");
        String filePath = String.format(".%s%s%sizvestaj_metadata.ttl", sep, OUTPUT_FOLDER_METADATA, sep);
        this.rdfService.runAndExportInNativeFormat(
                sparqlQuery,
                filePath
        );
        try {
            return new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}


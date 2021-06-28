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
import rs.ac.uns.ftn.tim5.model.resenje.Resenje;
import rs.ac.uns.ftn.tim5.repository.AbstractXmlRepository;
import rs.ac.uns.ftn.tim5.transofrmation.XSLFOTransformer;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.*;

@Service
public class ResenjeService implements AbstractXmlService<Resenje> {

    private final String jaxbContextPath = "rs.ac.uns.ftn.tim5.model.resenje";

    private static final String SPARQL_NAMED_GRAPH_URI = "/resenje/sparql/metadata";

    public static final String OUTPUT_FOLDER_XML = "output_xml";
    public static final String OUTPUT_FOLDER_PDF = "output_pdf";
    public static final String OUTPUT_FOLDER_HTML = "output_html";

    public static final String OUTPUT_FOLDER_METADATA = "output_metadata";

    @Autowired
    private SparqlUtil sparqlUtil;

    @Autowired
    private UUIDHelper uuidHelper;

    @Autowired
    private DateHelper dateHelper;

    private XSLFOTransformer xslfoTransformer;

    @Autowired
    @Qualifier("resenjeRepository")
    private AbstractXmlRepository<Resenje> resenjeAbstractXmlRepository;

    @Autowired
    private XmlConversionAgent<Resenje> resenjeXmlConversionAgent;

    @Autowired
    private RDFService rdfService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ZalbaNaOdlukuService zalbaNaOdlukuService;

    @Autowired
    private ZalbaNaCutanjeService zalbaNaCutanjeService;

    @PostConstruct
    public void injectRepositoryProperties() {
        this.resenjeAbstractXmlRepository.injectRepositoryProperties(
                "/db/sample/resenja",
                jaxbContextPath,
                X_QUERY_FIND_ALL_RESENJA_EXPRESSION,
                X_UPDATE_REMOVE_RESENJE_BY_ID_EXPRESSION
        );

        this.xslfoTransformer = new XSLFOTransformer();
        try {
            this.xslfoTransformer.injectTransformerProperties(
                    "classpath:transformations/xsl/resenje.xsl",
                    "classpath:transformations/xsl_fo/resenje_fo.xsl",
                    "output_pdf/resenje.pdf",
                    "output_html/resenje.html"
            );
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
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
        try {
            xmlEntity = this.resenjeXmlConversionAgent.marshall(resenje, this.jaxbContextPath);
            if (!rdfService.save(xmlEntity, SPARQL_NAMED_GRAPH_URI)) {
                System.out.println("[ERROR] Neuspesno cuvanje metapodataka resenja u RDF DB.");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
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
                System.out.println("[ERROR] Neuspesno cuvanje metapodataka resenja u RDF DB.");
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
                        "/resenje/",
                        resenje.getId()
                )
        );
        resenje.setContent(
                String.format(
                        "%s%s%s%s%s",
                        System.getenv("FRONTEND_URL"),
                        "/",
                        resenje.getZalba().getTipZalbe(),
                        "/",
                        resenje.getIdZalbe()
                )
        );
        resenje.getDatumResenja().setContent(this.dateHelper.toDate(resenje.getDatumResenja()));
        resenje.getOdluka().setContent(resenje.getOdluka().isPrihvaceno() ? "prihvaceno" : (resenje.getOdluka().isOdbijenaZalba() ? "odbijena_zalba" : "odbijena_zahtev"));
        resenje.getPoverenik().setContent(resenje.getPoverenik().getIme() + " " + resenje.getPoverenik().getPrezime());

        resenje.setVocab("http://ftn.uns.ac.rs.tim5/model/predicate");
        resenje.setProperty("pred:zalba_url");
        resenje.getDatumResenja().setProperty("pred:datum_resenja");
        resenje.getOdluka().setProperty("pred:odluka_poverenika");
        resenje.getPoverenik().setProperty("pred:poverenik");
    }

    public byte[] generatePdf(Long id) {
        Resenje resenje = this.findById(id);
        try {
            this.resenjeXmlConversionAgent.marshallToFile(resenje, this.jaxbContextPath, this.getXmlFilePath());
            this.xslfoTransformer.generatePDF(this.getXmlFilePath());

        } catch (JAXBException | SAXException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error converting document to pdf.");
        }

        try {
            return FileUtils.readFileToByteArray(new File(this.getPdfFilePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public byte[] generateHtml(Long id) {
        Resenje resenje = this.findById(id);
        try {
            this.resenjeXmlConversionAgent.marshallToFile(resenje, this.jaxbContextPath,this.getXmlFilePath());
            this.xslfoTransformer.generateHTML(this.getXmlFilePath());

        } catch (JAXBException | SAXException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error converting document to pdf.");
        }

        try {
            return FileUtils.readFileToByteArray(new File(this.getHtmlFilePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ByteArrayInputStream exportMetadataAsJson(Long resenjeId) {
        String sparqlQuery = this.sparqlUtil.selectPredicateAndObject(
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format("<%s/resenje/%d>  ?p  ?o", System.getenv("FRONTEND_URL"), resenjeId));
        String sep = System.getProperty("file.separator");
        String filePath = String.format(".%s%s%sresenje_metadata.json", sep, OUTPUT_FOLDER_METADATA, sep);
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

    public ByteArrayInputStream exportMetadataAsXml(Long resenjeId) {
        String sparqlQuery = this.sparqlUtil.selectPredicateAndObject(
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format("<%s/resenje/%d>  ?p  ?o", System.getenv("FRONTEND_URL"), resenjeId));
        String sep = System.getProperty("file.separator");
        String filePath = String.format(".%s%s%sresenje_metadata.xml", sep, OUTPUT_FOLDER_METADATA, sep);
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

    public ByteArrayInputStream exportMetadataAsRdf(Long resenjeId) {
        String sparqlQuery = this.sparqlUtil.describe(
                String.format("%s/resenje/%d", System.getenv("FRONTEND_URL"), resenjeId),
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format("<%s/resenje/%d>  ?p  ?o", System.getenv("FRONTEND_URL"), resenjeId));
        String sep = System.getProperty("file.separator");
        String filePath = String.format(".%s%s%resenje_metadata.ttl", sep, OUTPUT_FOLDER_METADATA, sep);
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

    public String getXmlFilePath() {
        String sep = System.getProperty("file.separator");
        return String.format(".%s%s%sresenje.xml", sep, OUTPUT_FOLDER_XML, sep);
    }

    public String getPdfFilePath() {
        String sep = System.getProperty("file.separator");
        return String.format(".%s%s%sresenje.pdf", sep, OUTPUT_FOLDER_PDF, sep);
    }

    private String getHtmlFilePath() {
        String sep = System.getProperty("file.separator");
        return String.format(".%s%s%sresenje.html", sep, OUTPUT_FOLDER_HTML, sep);
    }

    public Resenje findByIdZalbe(Long id) {
        try {
            return this.resenjeAbstractXmlRepository.findEntity(X_QUERY_FIND_RESENJE_BY_ID_ZALBE_EXPRESSION, id);
        } catch (XMLDBException | JAXBException e) {
            return null;
        }
    }

}

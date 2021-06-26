package rs.ac.uns.ftn.tim5.service;

import org.apache.commons.io.FileUtils;
import org.apache.jena.sparql.resultset.ResultsFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import rs.ac.uns.ftn.tim5.helper.SparqlQueryResult;
import rs.ac.uns.ftn.tim5.helper.XmlConversionAgent;
import rs.ac.uns.ftn.tim5.model.exception.EntityNotFoundException;
import rs.ac.uns.ftn.tim5.model.exception.InvalidXmlDatabaseException;
import rs.ac.uns.ftn.tim5.model.exception.InvalidXmlException;
import rs.ac.uns.ftn.tim5.model.exception.XmlDatabaseException;
import rs.ac.uns.ftn.tim5.model.obavestenje.Obavestenje;
import rs.ac.uns.ftn.tim5.model.zalba_cutanja.ZalbaCutanja;
import rs.ac.uns.ftn.tim5.model.zalba_na_odluku.ZalbaNaOdluku;
import rs.ac.uns.ftn.tim5.repository.AbstractXmlRepository;
import rs.ac.uns.ftn.tim5.transformation.XSLFOTransformer;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.*;


@Service
public class ZalbaNaOdlukuService implements AbstractXmlService<ZalbaNaOdluku> {

    private final String jaxbContextPath = "rs.ac.uns.ftn.tim5.model.zalba_na_odluku";

    private static final String SPARQL_NAMED_GRAPH_URI = "/zalba_na_odluku/sparql/metadata";

    public static final String OUTPUT_FOLDER_XML = "output_xml";
    public static final String OUTPUT_FOLDER_PDF = "output_pdf";
    public static final String OUTPUT_FOLDER_HTML = "output_html";

    public static final String OUTPUT_FOLDER_METADATA = "output_metadata";

    @Autowired
    @Qualifier("zalbaNaOdlukuRepository")
    private AbstractXmlRepository<ZalbaNaOdluku> zalbaNaOdlukuAbstractXmlRepository;

    @Autowired
    private XmlConversionAgent<ZalbaNaOdluku> zalbaNaOdlukuXmlConversionAgent;

    @Autowired 
    private RDFService rdfService;

    @Autowired
    private SparqlUtil sparqlUtil;

    @Autowired
    private UUIDHelper uuidHelper;

    @Autowired
    private DateHelper dateHelper;

    private XSLFOTransformer xslfoTransformer;

    @PostConstruct
    public void injectRepositoryProperties(){
        this.zalbaNaOdlukuAbstractXmlRepository.injectRepositoryProperties(
                "/db/sample/zalba_na_odluku",
                this.jaxbContextPath,
                X_QUERY_FIND_ALL_ZALBE_NA_ODLUKU_EXPRESSION,
                X_UPDATE_REMOVE_ZALBA_NA_ODLUKU_BY_ID_EXPRESSION
        );

        this.xslfoTransformer = new XSLFOTransformer();
        try {
            this.xslfoTransformer.injectTransformerProperties(
                    "classpath:transformations/xsl/zalba_na_odluku.xsl",
                    "classpath:transformations/xsl_fo/zalba_na_odluku_fo.xsl",
                    "output_pdf/zalba_na_odluku.pdf",
                    "output_html/zalba_na_odluku.html"
            );
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
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
            zalbaNaOdluku.setId(this.uuidHelper.getUUID());
            this.handleMetadata(zalbaNaOdluku);
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

        try {
            xmlEntity = this.zalbaNaOdlukuXmlConversionAgent.marshall(zalbaNaOdluku, this.jaxbContextPath);
            if (!rdfService.save(xmlEntity, SPARQL_NAMED_GRAPH_URI)) {
                System.out.println("[ERROR] Neuspesno cuvanje metapodataka zalbe na odluku u RDF DB.");
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

    private void handleMetadata(ZalbaNaOdluku zalbaNaOdluku) {
        zalbaNaOdluku.setAbout(
                String.format(
                        "%s%s%s",
                        System.getenv("FRONTEND_URL"),
                        "/zalba_na_odluku/",
                        zalbaNaOdluku.getId()
                )
        );
        zalbaNaOdluku.setContent(
                String.format(
                        "%s%s%s",
                        System.getenv("FRONTEND_URL"),
                        "/obavestenje/",
                        zalbaNaOdluku.getIdObavestenja()
                )
        );
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        zalbaNaOdluku.getZalilac().setContent(email);
        zalbaNaOdluku.getOrganVlasti().setContent(zalbaNaOdluku.getOrganVlasti().getNaziv());
        zalbaNaOdluku.getOdluka().getDatumOdluke().setContent(this.dateHelper.toDate(zalbaNaOdluku.getOdluka().getDatumOdluke()));
        zalbaNaOdluku.getMestoZalbe().setContent(zalbaNaOdluku.getMestoZalbe().getValue());
        zalbaNaOdluku.getDatumZalbe().setContent(this.dateHelper.toDate(zalbaNaOdluku.getDatumZalbe()));

        zalbaNaOdluku.setVocab("http://ftn.uns.ac.rs.tim5/model/predicate");
        zalbaNaOdluku.setProperty("pred:obavestenje_url");
        zalbaNaOdluku.getZalilac().setProperty("pred:email_trazioca");
        zalbaNaOdluku.getOrganVlasti().setProperty("pred:naziv_organa_vlasti");
        zalbaNaOdluku.getOdluka().getDatumOdluke().setProperty("pred:datum_odluke");
        zalbaNaOdluku.getMestoZalbe().setProperty("pred:mesto");
        zalbaNaOdluku.getDatumZalbe().setProperty("pred:datum_zalbe");
    }

    public ZalbaNaOdluku findByIdZahteva(Long idZahteva) {
        try {
            return this.zalbaNaOdlukuAbstractXmlRepository.findEntity(
                    X_QUERY_FIND_ZALBA_NA_ODLUKU_BY_ID_ZAHTEVA_EXPRESSION,
                    idZahteva
            );
        } catch (XMLDBException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] generatePdf(Long id) {
        ZalbaNaOdluku zalbaNaOdluku = this.findById(id);
        try {
            this.zalbaNaOdlukuXmlConversionAgent.marshallToFile(zalbaNaOdluku, this.jaxbContextPath, this.getXmlFilePath());
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
        ZalbaNaOdluku zalbaNaOdluku = this.findById(id);
        try {
            this.zalbaNaOdlukuXmlConversionAgent.marshallToFile(zalbaNaOdluku, this.jaxbContextPath,this.getXmlFilePath());
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

    public ByteArrayInputStream exportMetadataAsJson(Long zalbaNaOdlukuId) {
        String sparqlQuery = this.sparqlUtil.selectPredicateAndObject(
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format("<%s/zalba_na_odluku/%d>  ?p  ?o", System.getenv("FRONTEND_URL"), zalbaNaOdlukuId));
        String sep = System.getProperty("file.separator");
        String filePath = String.format(".%s%s%szalba_na_odluku_metadata.json", sep, OUTPUT_FOLDER_METADATA, sep);
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

    public ByteArrayInputStream exportMetadataAsXml(Long zalbaNaOdlukuId) {
        String sparqlQuery = this.sparqlUtil.selectPredicateAndObject(
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format("<%s/zalba_na_odluku/%d>  ?p  ?o", System.getenv("FRONTEND_URL"), zalbaNaOdlukuId));
        String sep = System.getProperty("file.separator");
        String filePath = String.format(".%s%s%szalba_na_odluku_metadata.xml", sep, OUTPUT_FOLDER_METADATA, sep);
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

    public ByteArrayInputStream exportMetadataAsRdf(Long zalbaNaOdlukuId) {
        String sparqlQuery = this.sparqlUtil.describe(
                String.format("%s/zalba_na_odluku/%d", System.getenv("FRONTEND_URL"), zalbaNaOdlukuId),
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format("<%s/zalba_na_odluku/%d>  ?p  ?o", System.getenv("FRONTEND_URL"), zalbaNaOdlukuId));
        String sep = System.getProperty("file.separator");
        String filePath = String.format(".%s%s%szalba_na_odluku_metadata.ttl", sep, OUTPUT_FOLDER_METADATA, sep);
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
        return String.format(".%s%s%szalba_na_odluku.xml", sep, OUTPUT_FOLDER_XML, sep);
    }

    public String getPdfFilePath() {
        String sep = System.getProperty("file.separator");
        return String.format(".%s%s%szalba_na_odluku.pdf", sep, OUTPUT_FOLDER_PDF, sep);
    }

    private String getHtmlFilePath() {
        String sep = System.getProperty("file.separator");
        return String.format(".%s%s%szalba_na_odluku.html", sep, OUTPUT_FOLDER_HTML, sep);
    }

    private List<ZalbaNaOdluku> sparqlQueryToZalbaNaOdlukuList(String query) {
        try {
            List<SparqlQueryResult> sparqlQueryResults = this.rdfService.run(query);
            List<String> zalbaIds = sparqlQueryResults.stream().map(zahtev -> {
                String[] data = zahtev.getVarValue().toString().split("/");
                return data[data.length - 1];
            }).collect(Collectors.toList());
            return zalbaIds.stream().map(
                    zalbaId -> {
                        try {
                            return this.zalbaNaOdlukuAbstractXmlRepository.getEntity(Long.parseLong(zalbaId));
                        } catch (XMLDBException | JAXBException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
            ).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ZalbaNaOdluku> findAllByGradjaninEmail(String email) {
        String query = this.sparqlUtil.selectData(
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format(
                        "?s <http://ftn.uns.ac.rs/tim5/model/predicate/email_trazioca> \"%s\"^^<http://www.w3.org/2000/01/rdf-schema#Literal>",
                        email)
        );
        return this.sparqlQueryToZalbaNaOdlukuList(query);
    }
}

package rs.ac.uns.ftn.tim5.service;

import org.apache.commons.io.FileUtils;
import org.apache.jena.sparql.resultset.ResultsFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import rs.ac.uns.ftn.tim5.SOAP.client.ZalbaCutanjeClient;
import rs.ac.uns.ftn.tim5.helper.SparqlQueryResult;
import rs.ac.uns.ftn.tim5.helper.XmlConversionAgent;
import rs.ac.uns.ftn.tim5.model.exception.EntityNotFoundException;
import rs.ac.uns.ftn.tim5.model.exception.InvalidXmlDatabaseException;
import rs.ac.uns.ftn.tim5.model.exception.InvalidXmlException;
import rs.ac.uns.ftn.tim5.model.exception.XmlDatabaseException;
import rs.ac.uns.ftn.tim5.model.zalba_cutanja.ZalbaCutanja;
import rs.ac.uns.ftn.tim5.repository.AbstractXmlRepository;
import rs.ac.uns.ftn.tim5.transofrmation.XSLFOTransformer;
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
public class ZalbaNaCutanjeService implements AbstractXmlService<ZalbaCutanja> {

    private final String jaxbContextPath = "rs.ac.uns.ftn.tim5.model.zalba_cutanja";

    private static final String SPARQL_NAMED_GRAPH_URI = "/zalba_cutanja/sparql/metadata";

    public static final String OUTPUT_FOLDER_XML = "output_xml";
    public static final String OUTPUT_FOLDER_PDF = "output_pdf";
    public static final String OUTPUT_FOLDER_HTML = "output_html";

    public static final String OUTPUT_FOLDER_METADATA = "output_metadata";

    @Autowired
    @Qualifier("zalbaCutanjaRepository")
    private AbstractXmlRepository<ZalbaCutanja> zalbaCutanjaAbstractXmlRepository;

    @Autowired
    private XmlConversionAgent<ZalbaCutanja> zalbaCutanjaXmlConversionAgent;

    @Autowired
    private RDFService rdfService;

    @Autowired
    private SparqlUtil sparqlUtil;

    @Autowired
    private UUIDHelper uuidHelper;

    @Autowired
    private DateHelper dateHelper;

    @Autowired
    private ZalbaCutanjeClient zalbaCutanjeClient;

    private XSLFOTransformer xslfoTransformer;

    @Autowired
    @Lazy
    private ResenjeService resenjeService;


    @PostConstruct
    public void injectRepositoryProperties(){
        this.zalbaCutanjaAbstractXmlRepository.injectRepositoryProperties(
                "/db/sample/zalba_cutanja",
                this.jaxbContextPath,
                X_QUERY_FIND_ALL_ZALBE_CUTANJA_EXPRESSION,
                X_UPDATE_REMOVE_ZALBA_CUTANJA_BY_ID_EXPRESSION
        );
        this.xslfoTransformer = new XSLFOTransformer();
        try {
            this.xslfoTransformer.injectTransformerProperties(
                    "classpath:transformations/xsl/zalba_cutanja.xsl",
                    "classpath:transformations/xsl_fo/zalba_cutanja_fo.xsl",
                    "output_pdf/zalba_cutanja.pdf",
                    "output_html/zalba_cutanja.html"
            );
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ZalbaCutanja> findAll() {
        System.out.println(this.zalbaCutanjaAbstractXmlRepository.jaxbContextPath);
        try {
            return this.zalbaCutanjaAbstractXmlRepository.getAllEntities();
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(ZalbaCutanja.class, e.getMessage());
        }
    }

    @Override
    public ZalbaCutanja findById(Long entityId) {
        try {
            ZalbaCutanja zalbaCutanja = this.zalbaCutanjaAbstractXmlRepository.getEntity(entityId);
            if(zalbaCutanja == null){
                throw new EntityNotFoundException(entityId, ZalbaCutanja.class);
            }
            return zalbaCutanja;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(ZalbaCutanja.class, e.getMessage());
        }
    }

    @Override
    public ZalbaCutanja create(String xmlEntity) {
        ZalbaCutanja zalbaCutanja;
        try{
            zalbaCutanja = this.zalbaCutanjaXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
            zalbaCutanja.setId(this.uuidHelper.getUUID());
            this.handleMetadata(zalbaCutanja);
        }catch(JAXBException e){
            throw new InvalidXmlException(ZalbaCutanja.class, e.getMessage());
        }

        try {
            zalbaCutanja = zalbaCutanjaAbstractXmlRepository.createEntity(zalbaCutanja);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(ZalbaCutanja.class, e.getMessage());
        }

        // Sacuvaj u RDF
        try {
            xmlEntity = this.zalbaCutanjaXmlConversionAgent.marshall(zalbaCutanja, this.jaxbContextPath);
            if (!rdfService.save(xmlEntity, SPARQL_NAMED_GRAPH_URI)) {
                System.out.println("[ERROR] Neuspesno cuvanje metapodataka zalbe cutanja u RDF DB.");
            };
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        //this.zalbaCutanjeClient.sendZalba(zalbaCutanja);

        return zalbaCutanja;
    }

    public ZalbaCutanja create(ZalbaCutanja zalbaCutanja) {
        this.handleMetadata(zalbaCutanja);
        try {
            zalbaCutanja = zalbaCutanjaAbstractXmlRepository.createEntity(zalbaCutanja);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(ZalbaCutanja.class, e.getMessage());
        }

        // Sacuvaj u RDF
        try {
            String xmlEntity = this.zalbaCutanjaXmlConversionAgent.marshall(zalbaCutanja, this.jaxbContextPath);
            if (!rdfService.save(xmlEntity, SPARQL_NAMED_GRAPH_URI)) {
                System.out.println("[ERROR] Neuspesno cuvanje metapodataka zalbe cutanja u RDF DB.");
            };
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        //this.zalbaCutanjeClient.sendZalba(zalbaCutanja);

        return zalbaCutanja;
    }

    @Override
    public ZalbaCutanja update(String xmlEntity) {
        ZalbaCutanja zalbaCutanja;
        try{
            zalbaCutanja = this.zalbaCutanjaXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
        }catch(JAXBException e){
            throw new InvalidXmlException(ZalbaCutanja.class, e.getMessage());
        }

        try {
            if (!this.zalbaCutanjaAbstractXmlRepository.updateEntity(zalbaCutanja)) {
                throw new EntityNotFoundException(zalbaCutanja.getId(), ZalbaCutanja.class);
            }
            return zalbaCutanja;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(ZalbaCutanja.class, e.getMessage());
        }
    }

    public ZalbaCutanja accept(Long id){
        ZalbaCutanja zalbaCutanja = this.findById(id);
        zalbaCutanja.getOdgovorOrganaVlasti().getPrihvatio().setValue("da");

        zalbaCutanja = update(zalbaCutanja);
        zalbaCutanjeClient.sendZalba(zalbaCutanja);
        return zalbaCutanja;
    }

    public ZalbaCutanja decline(Long id){
        ZalbaCutanja zalbaCutanja = this.findById(id);
        zalbaCutanja.getOdgovorOrganaVlasti().getOdbio().setValue("da");

        zalbaCutanja = update(zalbaCutanja);
        zalbaCutanjeClient.sendZalba(zalbaCutanja);
        return zalbaCutanja;
    }

    public ZalbaCutanja update(ZalbaCutanja zalbaCutanja) {

        try {
            if (!this.zalbaCutanjaAbstractXmlRepository.updateEntity(zalbaCutanja)) {
                throw new EntityNotFoundException(zalbaCutanja.getId(), ZalbaCutanja.class);
            }
            return zalbaCutanja;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(ZalbaCutanja.class, e.getMessage());
        }
    }

    @Override
    public boolean deleteById(Long entityId) {
        try {
            return this.zalbaCutanjaAbstractXmlRepository.deleteEntity(entityId);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        }
    }

    public ZalbaCutanja findByIdZahteva(Long idZahteva) {
        try {
            return this.zalbaCutanjaAbstractXmlRepository.findEntity(
                    X_QUERY_FIND_ZALBA_CUTANJA_BY_ID_ZAHTEVA_EXPRESSION,
                    idZahteva
            );
        } catch (XMLDBException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void handleMetadata(ZalbaCutanja zalbaCutanja) {
        /**
         * Izmeni ako da se slaze sa portom frontenda organa vlasti (neophodno za rdf pretrage)
         */
        zalbaCutanja.setAbout(
                String.format(
                        "%s%s%s",
                        System.getenv("FRONTEND_URL"),
                        "/zalba_cutanja/",
                        zalbaCutanja.getId()
                )
        );
    }

    public byte[] generatePdf(Long id) {
        ZalbaCutanja zalbaCutanja = this.findById(id);
        try {
            this.zalbaCutanjaXmlConversionAgent.marshallToFile(zalbaCutanja, this.jaxbContextPath, this.getXmlFilePath());
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
        ZalbaCutanja zalbaCutanja = this.findById(id);
        try {
            this.zalbaCutanjaXmlConversionAgent.marshallToFile(zalbaCutanja, this.jaxbContextPath,this.getXmlFilePath());
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

    public ByteArrayInputStream exportMetadataAsJson(Long zalbaCutanjaId) {
        String sparqlQuery = this.sparqlUtil.selectPredicateAndObject(
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format("<%s/zalba_cutanja/%d>  ?p  ?o", System.getenv("FRONTEND_URL"), zalbaCutanjaId));
        String sep = System.getProperty("file.separator");
        String filePath = String.format(".%s%s%szalba_cutanja_metadata.json", sep, OUTPUT_FOLDER_METADATA, sep);
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

    public ByteArrayInputStream exportMetadataAsXml(Long zalbaCutanjaId) {
        String sparqlQuery = this.sparqlUtil.selectPredicateAndObject(
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format("<%s/zalba_cutanja/%d>  ?p  ?o", System.getenv("FRONTEND_URL"), zalbaCutanjaId));
        String sep = System.getProperty("file.separator");
        String filePath = String.format(".%s%s%szalba_cutanja_metadata.xml", sep, OUTPUT_FOLDER_METADATA, sep);
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

    public ByteArrayInputStream exportMetadataAsRdf(Long zalbaCutanjaId) {
        String sparqlQuery = this.sparqlUtil.describe(
                String.format("%s/zalba_cutanja/%d", System.getenv("FRONTEND_URL"), zalbaCutanjaId),
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format("<%s/zalba_cutanja/%d>  ?p  ?o", System.getenv("FRONTEND_URL"), zalbaCutanjaId));
        String sep = System.getProperty("file.separator");
        String filePath = String.format(".%s%s%szalba_cutanja_metadata.ttl", sep, OUTPUT_FOLDER_METADATA, sep);
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
        return String.format(".%s%s%szalba_cutanja.xml", sep, OUTPUT_FOLDER_XML, sep);
    }

    public String getPdfFilePath() {
        String sep = System.getProperty("file.separator");
        return String.format(".%s%s%szalba_cutanja.pdf", sep, OUTPUT_FOLDER_PDF, sep);
    }

    private String getHtmlFilePath() {
        String sep = System.getProperty("file.separator");
        return String.format(".%s%s%szalba_cutanja.html", sep, OUTPUT_FOLDER_HTML, sep);
    }

    private List<ZalbaCutanja> sparqlQueryToZalbaNaOdlukuList(String query) {
        try {
            List<SparqlQueryResult> sparqlQueryResults = this.rdfService.run(query);
            List<String> zalbaIds = sparqlQueryResults.stream().map(zahtev -> {
                String[] data = zahtev.getVarValue().toString().split("/");
                return data[data.length - 1];
            }).collect(Collectors.toList());
            return zalbaIds.stream().map(
                    zalbaId -> {
                        try {
                            return this.zalbaCutanjaAbstractXmlRepository.getEntity(Long.parseLong(zalbaId));
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

    public List<ZalbaCutanja> findAllByGradjaninEmail(String email) {
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

    public List<ZalbaCutanja> findAllNeobradjene() {
        List<ZalbaCutanja> zalbeCutanja = this.findAll();
        //TODO: Zalbe koje se filtriraju ispod dodatno isfiltrirati po nepostojanju resenja za zalbu sa datim ID-jem
        return zalbeCutanja.stream().filter(
                x -> x.getOdgovorOrganaVlasti().getPrihvatio().getValue().equals("ne") &&
                     x.getOdgovorOrganaVlasti().getOdbio().getValue().equals("ne") && this.resenjeService.findByIdZalbe(x.getId()) == null
        ).collect(Collectors.toList());
    }

    public List<String> getRefers(String about) {
        return rdfService.search(SPARQL_NAMED_GRAPH_URI, about);
    }
}

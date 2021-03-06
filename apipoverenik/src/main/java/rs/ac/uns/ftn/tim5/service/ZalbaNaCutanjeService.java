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
import rs.ac.uns.ftn.tim5.helper.PretrageHelper;
import rs.ac.uns.ftn.tim5.helper.SparqlQueryResult;
import rs.ac.uns.ftn.tim5.helper.XmlConversionAgent;
import rs.ac.uns.ftn.tim5.model.exception.EntityNotFoundException;
import rs.ac.uns.ftn.tim5.model.exception.InvalidXmlDatabaseException;
import rs.ac.uns.ftn.tim5.model.exception.InvalidXmlException;
import rs.ac.uns.ftn.tim5.model.exception.XmlDatabaseException;
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
import java.util.*;
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

    @Autowired
    private PretrageHelper pretrageHelper;

    @Autowired
    @Lazy
    private ResenjeService resenjeService;

    private XSLFOTransformer xslfoTransformer;

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

        this.zalbaCutanjeClient.sendZalba(zalbaCutanja);

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
        zalbaCutanja.setAbout(
                String.format(
                        "%s%s%s",
                        System.getenv("FRONTEND_URL"),
                        "/zalba_cutanja/",
                        zalbaCutanja.getId()
                )
        );
        zalbaCutanja.setContent(
                String.format(
                        "%s%s%s",
                        "http://localhost:8086",
                        "/zahtev/",
                        zalbaCutanja.getIdZahteva()
                )
        );
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        zalbaCutanja.getZalilac().setContent(email);
        zalbaCutanja.getOrganVlasti().setContent(zalbaCutanja.getOrganVlasti().getNaziv());
        zalbaCutanja.getMestoZalbe().setContent(zalbaCutanja.getMestoZalbe().getValue());
        zalbaCutanja.getDatumZalbe().setContent(this.dateHelper.toDate(zalbaCutanja.getDatumZalbe()));

        zalbaCutanja.setVocab("http://ftn.uns.ac.rs.tim5/model/predicate");
        zalbaCutanja.setProperty("pred:zahtev_url");
        zalbaCutanja.getZalilac().setProperty("pred:email_trazioca");
        zalbaCutanja.getOrganVlasti().setProperty("pred:naziv_organa_vlasti");
        zalbaCutanja.getMestoZalbe().setProperty("pred:mesto");
        zalbaCutanja.getDatumZalbe().setProperty("pred:datum_zalbe");
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
        return zalbeCutanja.stream().filter(
                x -> x.getOdgovorOrganaVlasti().getPrihvatio().getValue().equals("ne") && this.resenjeService.findByIdZalbe(x.getId()) == null
        ).collect(Collectors.toList());
    }

    public String findEmailGradjaninaByIdZalbe(Long idZalbe) {
        String zalbaUrl = String.format(
                "<%s%s%s>",
                System.getenv("FRONTEND_URL"),
                "/zalba_cutanja/",
                idZalbe
        );
        String query = this.sparqlUtil.selectObjectOnly(
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format(
                        "%s <http://ftn.uns.ac.rs/tim5/model/predicate/email_trazioca> ?o",
                        zalbaUrl)
        );
        try {
            List<SparqlQueryResult> sparqlQueryResults = this.rdfService.run(query);
            System.out.println(sparqlQueryResults.get(0).getVarValue().toString());
            return sparqlQueryResults.get(0).getVarValue().toString().split("\\^")[0];
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ZalbaCutanja> pronadjiTerm(String term) {
        try {
            return this.zalbaCutanjaAbstractXmlRepository.findEntities(String.format(SEARCH_ZALBA_CUTANJA, term));
        } catch (XMLDBException | JAXBException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<ZalbaCutanja> pronadjiMetadata(String metadata) {
        String[] sts = metadata.split("\\*OR\\*");
        Set<ZalbaCutanja> retval = new HashSet<>();
        for (String andsubstr : sts) {
            String[] andsubstrtokens = andsubstr.split("\\*AND\\*");
            System.out.println("Search by metadata AND substring: " + Arrays.toString(andsubstrtokens));
            Set<String> ids = pretrageHelper.searchMetadata(andsubstrtokens, SPARQL_NAMED_GRAPH_URI);

            ids.forEach((String s) -> {
                try {
                    retval.add(zalbaCutanjaAbstractXmlRepository.getEntity(Long.parseLong(s)));
                } catch (XMLDBException | JAXBException e) {
                    e.printStackTrace();
                }
            });
        }

        return new ArrayList<>(retval);
    }

    public List<String> getRefers(String about) {

        return rdfService.search(SPARQL_NAMED_GRAPH_URI, about);
    }
}

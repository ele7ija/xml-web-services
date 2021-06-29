package rs.ac.uns.ftn.tim5.service;

import org.apache.commons.io.FileUtils;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.sparql.resultset.ResultsFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import rs.ac.uns.ftn.tim5.helper.PretrageHelper;
import rs.ac.uns.ftn.tim5.helper.SparqlQueryResult;
import rs.ac.uns.ftn.tim5.helper.XQueryExpressions;
import rs.ac.uns.ftn.tim5.helper.XmlConversionAgent;
import rs.ac.uns.ftn.tim5.model.exception.EntityNotFoundException;
import rs.ac.uns.ftn.tim5.model.exception.InvalidXmlDatabaseException;
import rs.ac.uns.ftn.tim5.model.exception.InvalidXmlException;
import rs.ac.uns.ftn.tim5.model.exception.XmlDatabaseException;
import rs.ac.uns.ftn.tim5.model.sluzbenik.Sluzbenik;
import rs.ac.uns.ftn.tim5.model.zahtev.Zahtev;
import rs.ac.uns.ftn.tim5.repository.AbstractXmlRepository;
import rs.ac.uns.ftn.tim5.transofrmation.XSLFOTransformer;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.X_QUERY_FIND_ALL_ZAHTEVI_EXPRESSION;
import static rs.ac.uns.ftn.tim5.helper.XQueryExpressions.X_UPDATE_REMOVE_ZAHTEV_BY_ID_EXPRESSION;

@Service
public class ZahtevService implements AbstractXmlService<Zahtev> {

    private final String jaxbContextPath = "rs.ac.uns.ftn.tim5.model.zahtev";

    private static final String SPARQL_NAMED_GRAPH_URI = "/zahtev/sparql/metadata";

    public static final String OUTPUT_FOLDER_XML = "output_xml";
    public static final String OUTPUT_FOLDER_PDF = "output_pdf";
    public static final String OUTPUT_FOLDER_HTML = "output_html";
    public static final String OUTPUT_FOLDER_METADATA = "output_metadata";

    @Autowired
    @Qualifier("zahtevRepository")
    private AbstractXmlRepository<Zahtev> zahtevAbstractXmlRepository;

    @Autowired
    private XmlConversionAgent<Zahtev> zahtevXmlConversionAgent;

    @Autowired
    private RDFService rdfService;

    @Autowired
    private UUIDHelper uuidHelper;

    @Autowired
    private AuthHelper authHelper;

    @Autowired
    private DateHelper dateHelper;

    @Autowired
    private SparqlUtil sparqlUtil;

    @Autowired
    private PretrageHelper pretrageHelper;

    @Autowired
    private SluzbenikService sluzbenikService;

    private XSLFOTransformer XSLFOTransformer;

    @PostConstruct
    public void injectRepositoryProperties() {
        this.zahtevAbstractXmlRepository.injectRepositoryProperties(
                "/db/sample/zahtev",
                jaxbContextPath,
                X_QUERY_FIND_ALL_ZAHTEVI_EXPRESSION,
                X_UPDATE_REMOVE_ZAHTEV_BY_ID_EXPRESSION
        );


        this.XSLFOTransformer = new XSLFOTransformer();
        try {
            this.XSLFOTransformer.injectTransformerProperties(
                    "classpath:transformations/xsl/zahtev.xsl",
                    "classpath:transformations/xsl_fo/zahtev_fo.xsl",
                    "output_pdf/zahtev.pdf",
                    "output_html/zahtev.html"
            );
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        /*
            Unmarshall to java POJO
            Create UUID
            Set RDFa
         */
        Zahtev zahtev;
        try {
            zahtev = this.zahtevXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
            zahtev.setId(this.uuidHelper.getUUID());
            this.handleMetadata(zahtev);
        } catch (JAXBException e) {
            throw new InvalidXmlException(Zahtev.class, e.getMessage());
        }

        /*
            Store in XML DB
         */
        try {
            zahtev = zahtevAbstractXmlRepository.createEntity(zahtev);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Zahtev.class, e.getMessage());
        }

        /*
            Save to RDF DB
            We marshall because we need RDFa (which was set by handleMetadata)
         */
        try {
            xmlEntity = this.zahtevXmlConversionAgent.marshall(zahtev, this.jaxbContextPath);
            System.out.println(xmlEntity);
            if (!rdfService.save(xmlEntity, SPARQL_NAMED_GRAPH_URI)) {
                System.out.println("[ERROR] Neuspesno cuvanje metapodataka zahteva u RDF DB.");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
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


    public ByteArrayInputStream generatePdf(Long id) {
        Zahtev zahtev = this.findById(id);
        try {
            this.zahtevXmlConversionAgent.marshallToFile(zahtev, this.jaxbContextPath,this.getXmlFilePath());
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
        Zahtev zahtev = this.findById(id);
        try {
            this.zahtevXmlConversionAgent.marshallToFile(zahtev, this.jaxbContextPath,this.getXmlFilePath());
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
        return String.format(".%s%s%szahtev.xml", sep, OUTPUT_FOLDER_XML, sep);
    }

    public String getPdfFilePath() {
        String sep = System.getProperty("file.separator");
        return String.format(".%s%s%szahtev.pdf", sep, OUTPUT_FOLDER_PDF, sep);
    }

    private String getHtmlFilePath() {
        String sep = System.getProperty("file.separator");
        return String.format(".%s%s%szahtev.html", sep, OUTPUT_FOLDER_HTML, sep);
    }

    private void handleMetadata(Zahtev zahtev) {
        zahtev.setAbout(
                String.format(
                        "%s%s%s",
                        System.getenv("FRONTEND_URL"),
                        "/zahtev/",
                        zahtev.getId()
                )
        );
        zahtev.getTrazilac().setContent(this.authHelper.getAuthenticatedEmail());
        zahtev.getOrgan().setContent(zahtev.getOrgan().getNaziv());
        zahtev.getMesto().setContent(zahtev.getMesto().getValue());
        zahtev.getDatum().setContent(this.dateHelper.toDate(zahtev.getDatum()));


        zahtev.setVocab("http://ftn.uns.ac.rs.tim5/model/predicate");
        zahtev.getTrazilac().setProperty("pred:email_trazioca");
        zahtev.getOrgan().setProperty("pred:naziv_organa_vlasti");
        zahtev.getMesto().setProperty("pred:mesto");
        zahtev.getDatum().setProperty("pred:datum");
    }

    public List<Zahtev> findAllByGradjaninEmail(String email) {
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
        return this.sparqlQueryToZahtevList(query);
    }

    public List<Zahtev> findAllByNazivOrganaVlasti(String email) {
        Sluzbenik sluzbenik = this.sluzbenikService.findByUsername(email);
        String nazivOrganaVlasti = sluzbenik.getPreduzece().getNaziv();
        String query = this.sparqlUtil.selectData(
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format(
                        "?s <http://ftn.uns.ac.rs/tim5/model/predicate/naziv_organa_vlasti> \"%s\"^^<http://www.w3.org/2000/01/rdf-schema#Literal>",
                        nazivOrganaVlasti)
        );
        return this.sparqlQueryToZahtevList(query);
    }

    /**
     * Helper metoda koja konvertuje listu SparqlQueryResult objekata u listu Zahtev objekata
     */
    private List<Zahtev> sparqlQueryToZahtevList(String query) {
        try {
            List<SparqlQueryResult> sparqlQueryResults = this.rdfService.run(query);
            List<String> zahtevIds = sparqlQueryResults.stream().map(zahtev -> {
                String[] data = zahtev.getVarValue().toString().split("/");
                return data[data.length - 1];
            }).collect(Collectors.toList());
            return zahtevIds.stream().map(
                    zahtevId -> {
                        try {
                            return this.zahtevAbstractXmlRepository.getEntity(Long.parseLong(zahtevId));
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

    public String findGradjaninEmailFromZahtevId(Long zahtevId) {
        String query = this.sparqlUtil.selectObjectOnly(
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format(
                        "?s ?p ?o\nFILTER (?s=<%s/zahtev/%d> && ?p=<http://ftn.uns.ac.rs/tim5/model/predicate/email_trazioca>)",
                        System.getenv("FRONTEND_URL"),
                        zahtevId
                )
        );
        try {
            List<SparqlQueryResult> sparqlQueryResults = this.rdfService.run(query);
            String temp = sparqlQueryResults.get(0).getVarValue().toString();
            return temp.substring(0, temp.indexOf('^')); //remove ^^ characters
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ByteArrayInputStream exportMetadataAsJson(Long zahtevId) {
        String sparqlQuery = this.sparqlUtil.selectPredicateAndObject(
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format("<%s/zahtev/%d>  ?p  ?o", System.getenv("FRONTEND_URL"), zahtevId));
        String sep = System.getProperty("file.separator");
        String filePath = String.format(".%s%s%szahtev_metadata.json", sep, OUTPUT_FOLDER_METADATA, sep);
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
                String.format("<%s/zahtev/%d>  ?p  ?o", System.getenv("FRONTEND_URL"), zahtevId));
        String sep = System.getProperty("file.separator");
        String filePath = String.format(".%s%s%szahtev_metadata.xml", sep, OUTPUT_FOLDER_METADATA, sep);
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
                String.format("%s/zahtev/%d", System.getenv("FRONTEND_URL"), zahtevId),
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format("<%s/zahtev/%d>  ?p  ?o", System.getenv("FRONTEND_URL"), zahtevId));
        String sep = System.getProperty("file.separator");
        String filePath = String.format(".%s%s%szahtev_metadata.ttl", sep, OUTPUT_FOLDER_METADATA, sep);
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

    public List<Zahtev> pronadjiTerm(String term) {

        try {

            return this.zahtevAbstractXmlRepository.findEntities(String.format(XQueryExpressions.SEARCH_ZAHTEVI, term));
        } catch (XMLDBException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Zahtev> pronadjiMetadata(String metadata) {

        String[] sts = metadata.split("\\*OR\\*");
        Set<Zahtev> retval = new HashSet<>();
        for (String andsubstr : sts) {
            String[] andsubstrtokens = andsubstr.split("\\*AND\\*");
            System.out.println("Search by metadata AND substring: " + Arrays.toString(andsubstrtokens));
            Set<String> ids = pretrageHelper.searchMetadata(andsubstrtokens, SPARQL_NAMED_GRAPH_URI);

            ids.forEach((String s) -> {
                try {
                    retval.add(zahtevAbstractXmlRepository.getEntity(Long.parseLong(s)));
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

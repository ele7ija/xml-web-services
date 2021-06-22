package rs.ac.uns.ftn.tim5.service;

import org.apache.commons.io.FileUtils;
import org.apache.jena.sparql.resultset.ResultsFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import rs.ac.uns.ftn.tim5.repository.AbstractXmlRepository;


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
public class ObavestenjeService implements AbstractXmlService<Obavestenje> {

    private final String jaxbContextPath = "rs.ac.uns.ftn.tim5.model.obavestenje";

    private static final String SPARQL_NAMED_GRAPH_URI = "/obavestenje/sparql/metadata";

    public static final String OUTPUT_FOLDER_XML = "output_xml";
    public static final String OUTPUT_FOLDER_PDF = "output_pdf";
    public static final String OUTPUT_FOLDER_HTML = "output_html";

    public static final String OUTPUT_FOLDER_METADATA = "output_metadata";

    @Autowired
    @Qualifier("obavestenjeRepository")
    private AbstractXmlRepository<Obavestenje> obavestenjeAbstractXmlRepository;

    @Autowired
    private XmlConversionAgent<Obavestenje> obavestenjeXmlConversionAgent;

    @Autowired
    private RDFService rdfService;

    @Autowired
    private UUIDHelper uuidHelper;

    @Autowired
    private DateHelper dateHelper;

    /*@Autowired
    private ZahtevService zahtevService;

    @Autowired
    private EmailService emailService;*/

    @Autowired
    private SparqlUtil sparqlUtil;

    //private XSLFOTransformer XSLFOTransformer;

    @PostConstruct
    public void injectRepositoryProperties() {
        this.obavestenjeAbstractXmlRepository.injectRepositoryProperties(
                "/db/sample/obavestenje",
                jaxbContextPath,
                X_QUERY_FIND_ALL_OBAVESTENJA_EXPRESSION,
                X_UPDATE_REMOVE_OBAVESTENJE_BY_ID_EXPRESSION
        );

        /*this.XSLFOTransformer = new XSLFOTransformer();
        try {
            this.XSLFOTransformer.injectTransformerProperties(
                    "classpath:transformations/xsl/obavestenje.xsl",
                    "classpath:transformations/xsl_fo/obavestenje_fo.xsl",
                    "output_pdf/obavestenje.pdf",
                    "output_html/obavestenje.html"
            );
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public List<Obavestenje> findAll() {
        try {
            return this.obavestenjeAbstractXmlRepository.getAllEntities();
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Obavestenje.class, e.getMessage());
        }
    }

    @Override
    public Obavestenje findById(Long entityId) {
        try {
            Obavestenje obavestenje = this.obavestenjeAbstractXmlRepository.getEntity(entityId);
            if (obavestenje == null)
                throw new EntityNotFoundException(entityId, Obavestenje.class);
            return obavestenje;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Obavestenje.class, e.getMessage());
        }
    }

    @Override
    public Obavestenje create(String xmlEntity) {
        Obavestenje obavestenje;
        try {
            obavestenje = this.obavestenjeXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
            obavestenje.setId(this.uuidHelper.getUUID());
            //this.handleMetadata(obavestenje);
        } catch (JAXBException e) {
            throw new InvalidXmlException(Obavestenje.class, e.getMessage());
        }

        try {
            obavestenje = obavestenjeAbstractXmlRepository.createEntity(obavestenje);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Obavestenje.class, e.getMessage());
        }

        // Sacuvaj u RDF
        try {
            xmlEntity = this.obavestenjeXmlConversionAgent.marshall(obavestenje, this.jaxbContextPath);
            if (!rdfService.save(xmlEntity, SPARQL_NAMED_GRAPH_URI)) {
                System.out.println("[ERROR] Neuspesno cuvanje metapodataka obavestenja u RDF DB.");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        //posalji email
        /*Zahtev zahtev = this.zahtevService.findById(obavestenje.getIdZahteva());
        if (obavestenje.getOdbijen().isValue()) {
            this.emailService.odbijZahtev(zahtev);
        } else if(obavestenje.getIstekao().isValue()) {
            this.emailService.istekaoZahtev(zahtev);
        } else {
           this.emailService.prihvatiZahtev(
                   zahtev,
                   this.generatePdf(obavestenje.getId()),
                   this.generateHtml(obavestenje.getId())
           );
        }*/
        return obavestenje;
    }


    public Obavestenje create(Obavestenje obavestenje) {
        obavestenje.setId(this.uuidHelper.getUUID());
        //this.handleMetadata(obavestenje);

        try {
            obavestenje = obavestenjeAbstractXmlRepository.createEntity(obavestenje);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Obavestenje.class, e.getMessage());
        }

        // Sacuvaj u RDF
        try {
            String xmlEntity = this.obavestenjeXmlConversionAgent.marshall(obavestenje, this.jaxbContextPath);
            if (!rdfService.save(xmlEntity, SPARQL_NAMED_GRAPH_URI)) {
                System.out.println("[ERROR] Neuspesno cuvanje metapodataka obavestenja u RDF DB.");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        //posalji email
        /*Zahtev zahtev = this.zahtevService.findById(obavestenje.getIdZahteva());
        if (obavestenje.getOdbijen().isValue()) {
            this.emailService.odbijZahtev(zahtev);
        } else if(obavestenje.getIstekao().isValue()) {
            this.emailService.istekaoZahtev(zahtev);
        } else {
            this.emailService.prihvatiZahtev(
                    zahtev,
                    this.generatePdf(obavestenje.getId()),
                    this.generateHtml(obavestenje.getId())
            );
        }*/
        return obavestenje;
    }

    @Override
    public Obavestenje update(String xmlEntity) {
        Obavestenje obavestenje;
        try {
            obavestenje = this.obavestenjeXmlConversionAgent.unmarshall(xmlEntity, this.jaxbContextPath);
        } catch (JAXBException e) {
            throw new InvalidXmlException(Obavestenje.class, e.getMessage());
        }

        try {
            if (!this.obavestenjeAbstractXmlRepository.updateEntity(obavestenje)) {
                throw new EntityNotFoundException(obavestenje.getId(), Obavestenje.class);
            }
            return obavestenje;
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        } catch (JAXBException e) {
            throw new InvalidXmlDatabaseException(Obavestenje.class, e.getMessage());
        }

    }

    @Override
    public boolean deleteById(Long entityId) {
        try {
            return this.obavestenjeAbstractXmlRepository.deleteEntity(entityId);
        } catch (XMLDBException e) {
            throw new XmlDatabaseException(e.getMessage());
        }
    }

    /*private void handleMetadata(Obavestenje obavestenje) {
        obavestenje.setAbout(
                String.format(
                        "%s%s%s",
                        System.getenv("FRONTEND_URL"),
                        "/obavestenje/",
                        obavestenje.getId()
                )
        );
        obavestenje.setContent(
                String.format(
                        "%s%s%s",
                        System.getenv("FRONTEND_URL"),
                        "/zahtev/",
                        obavestenje.getIdZahteva()
                )
        );

           Dobavi email trazioca
            Iz RDF baze procitaj subjekat koji u svom identifikatoru sadrzi id zahteva na koje se obavestenje odnosi
            Taj subjekat ima za objekat email podnosioca
            U

        obavestenje.getTrazilac().setContent(this.zahtevService.findGradjaninEmailFromZahtevId(obavestenje.getIdZahteva()));
        obavestenje.getOrgan().setContent(obavestenje.getOrgan().getNaziv());
        obavestenje.getPredmet().getDatum().setContent(this.dateHelper.toDate(obavestenje.getPredmet().getDatum()));
        obavestenje.getOdbijen().setContent(obavestenje.getOdbijen().isValue() ? "da" : "ne");
        obavestenje.getIstekao().setContent(obavestenje.getIstekao().isValue() ? "da" : "ne");

        obavestenje.setVocab("http://ftn.uns.ac.rs.tim5/model/predicate");
        obavestenje.setProperty("pred:zahtev_url");
        obavestenje.getTrazilac().setProperty("pred:email_trazioca");
        obavestenje.getOrgan().setProperty("pred:naziv_organa_vlasti");
        obavestenje.getPredmet().getDatum().setProperty("pred:datum");
        obavestenje.getOdbijen().setProperty("pred:odbijen");
        obavestenje.getIstekao().setProperty("pred:istekao");
    }*/


    public String getXmlFilePath() {
        String sep = System.getProperty("file.separator");
        return String.format(".%s%s%sobavestenje.xml", sep, OUTPUT_FOLDER_XML, sep);
    }

    public String getPdfFilePath() {
        String sep = System.getProperty("file.separator");
        return String.format(".%s%s%sobavestenje.pdf", sep, OUTPUT_FOLDER_PDF, sep);
    }

    private String getHtmlFilePath() {
        String sep = System.getProperty("file.separator");
        return String.format(".%s%s%sobavestenje.html", sep, OUTPUT_FOLDER_HTML, sep);
    }

    public Obavestenje findByZahtevId(Long id) {
        try {
            return this.obavestenjeAbstractXmlRepository.findEntity(X_QUERY_FIND_OBAVESTENJE_BY_ID_ZAHTEVA_EXPRESSION, id);
        } catch (XMLDBException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public ByteArrayInputStream exportMetadataAsJson(Long zahtevId) {
        String sparqlQuery = this.sparqlUtil.selectPredicateAndObject(
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format("<%s/obavestenje/%d>  ?p  ?o", System.getenv("FRONTEND_URL"), zahtevId));
        String sep = System.getProperty("file.separator");
        String filePath = String.format(".%s%s%sobavestenje_metadata.json", sep, OUTPUT_FOLDER_METADATA, sep);
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
    }*/

    /*public ByteArrayInputStream exportMetadataAsXml(Long zahtevId) {
        String sparqlQuery = this.sparqlUtil.selectPredicateAndObject(
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format("<%s/obavestenje/%d>  ?p  ?o", System.getenv("FRONTEND_URL"), zahtevId));
        String sep = System.getProperty("file.separator");
        String filePath = String.format(".%s%s%sobavestenje_metadata.xml", sep, OUTPUT_FOLDER_METADATA, sep);
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
    }*/

    /*public ByteArrayInputStream exportMetadataAsRdf(Long zahtevId) {
        String sparqlQuery = this.sparqlUtil.describe(
                String.format("%s/obavestenje/%d", System.getenv("FRONTEND_URL"), zahtevId),
                String.format(
                        "%s%s",
                        this.rdfService.getRdfdbConnectionProperties().getDataEndpoint(),
                        SPARQL_NAMED_GRAPH_URI
                ),
                String.format("<%s/obavestenje/%d>  ?p  ?o", System.getenv("FRONTEND_URL"), zahtevId));
        String sep = System.getProperty("file.separator");
        String filePath = String.format(".%s%s%sobavestenje_metadata.ttl", sep, OUTPUT_FOLDER_METADATA, sep);
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
    }*/


    //TODO kad dodam logovanje onda ovo ima smila
    /*public List<Obavestenje> findByUlogovaniTrazilac(String email) {
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
        return this.sparqlQueryToObavestenjeList(query);
    }*/

    /**
     * Helper metoda koja konvertuje listu SparqlQueryResult objekata u listu Obavestenje objekata
     */
    private List<Obavestenje> sparqlQueryToObavestenjeList(String query) {
        try {
            List<SparqlQueryResult> sparqlQueryResults = this.rdfService.run(query);
            List<String> obavestenjaIds = sparqlQueryResults.stream().map(obavestenje -> {
                String[] data = obavestenje.getVarValue().toString().split("/");
                return data[data.length - 1];
            }).collect(Collectors.toList());
            return obavestenjaIds.stream().map(
                    obavestenjeId -> {
                        try {
                            return this.obavestenjeAbstractXmlRepository.getEntity(Long.parseLong(obavestenjeId));
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
}

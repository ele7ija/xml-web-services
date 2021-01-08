package rs.ac.uns.ftn.tim5.apipoverenik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import rs.ac.uns.ftn.tim5.apipoverenik.model.zalba_na_odluku.ZalbaNaOdluku;
import rs.ac.uns.ftn.tim5.apipoverenik.repository.AbstractXmlRepository;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.util.List;

import static rs.ac.uns.ftn.tim5.apipoverenik.helper.XQueryExpressions.*;

@Service
public class ZalbaNaOdlukuService implements AbstractXmlService<ZalbaNaOdluku> {

    @Autowired
    private AbstractXmlRepository<ZalbaNaOdluku> zalbaNaOdlukuAbstractXmlRepository;

    @PostConstruct
    public void injectRepositoryProperties(){
        this.zalbaNaOdlukuAbstractXmlRepository.injectRepositoryProperties(
                "/db/sample/zalbe_na_odluku",
                "rs.ac.uns.ftn.tim5.apipoverenik.model.zalba_na_dodluku",
                X_QUERY_FIND_ALL_ZALBE_NA_ODLUKU_EXPRESSION,
                X_QUERY_FIND_ZALBA_NA_ODLUKU_BY_ID_EXPRESSION,
                X_UPDATE_UPDATE_ZALBA_NA_ODLUKU_BY_ID_EXPRESSION,
                X_UPDATE_REMOVE_ZALBA_NA_ODLUKU_BY_ID_EXPRESSION
        );
    }

    @Override
    public List<ZalbaNaOdluku> findAll() {
        try {
            return this.zalbaNaOdlukuAbstractXmlRepository.getAllEntities();
        } catch (XMLDBException e) {
            //baci izuzetak kako nesto nije uredu sa xml bazom
            e.printStackTrace();
        } catch (JAXBException e) {
            //baci izuzetak kako nesto nije uredu sa ispravnoscu dokumenta procitanog iz baze (po pianju xml seme)
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ZalbaNaOdluku findById(Long entityId) {
        try {
            return this.zalbaNaOdlukuAbstractXmlRepository.getEntity(entityId);
        } catch (XMLDBException e) {
            //baci izuzetak kako nesto nije uredu sa xml bazom
            e.printStackTrace();
        } catch (JAXBException e) {
            //baci izuzetak kako nesto nije uredu sa ispravnoscu dokumenta procitanog iz baze (po pianju xml seme)
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long create(String xmlEntity) {
        /*
        Bojane, predpostavljam da bi ti u servisnim metodama koristio ovaj xmlEntity String kako bi odradio neke stvari sa RDF-om
         */

        ZalbaNaOdluku zalbaNaOdluku = null;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ZalbaNaOdluku.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlEntity);
            zalbaNaOdluku = (ZalbaNaOdluku) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            //baci custom izuzetak kako nesto nije u redu sa poslatim dokumentom i uhvati ga u rest controller advice-u
            e.printStackTrace();
        }

        try {
            return zalbaNaOdlukuAbstractXmlRepository.createEntity(zalbaNaOdluku);
        } catch (XMLDBException e) {
            //baci izuzetak kako nesto nije uredu sa xml bazom
            e.printStackTrace();
        } catch (JAXBException e) {
            //baci izuzetak kako nesto nije uredu sa ispravnoscu dokumenta procitanog iz baze (po pianju xml seme)
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(String xmlEntity) {
        ZalbaNaOdluku zalbaNaOdluku = null;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ZalbaNaOdluku.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlEntity);
            zalbaNaOdluku = (ZalbaNaOdluku) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            //baci custom izuzetak kako nesto nije u redu sa poslatim dokumentom i uhvati ga u rest controller advice-u
            e.printStackTrace();
        }

        try {
            return this.zalbaNaOdlukuAbstractXmlRepository.updateEntity(zalbaNaOdluku);
        } catch (XMLDBException e) {
            //baci izuzetak kako nesto nije uredu sa xml bazom
            e.printStackTrace();
        } catch (JAXBException e) {
            //baci izuzetak kako nesto nije uredu sa ispravnoscu dokumenta procitanog iz baze (po pianju xml seme)
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(Long entityId) {
        try {
            return this.zalbaNaOdlukuAbstractXmlRepository.deleteEntity(entityId);
        } catch (XMLDBException e) {
            //baci izuzetak kako nesto nije uredu sa xml bazom
            e.printStackTrace();
        }
        return false;
    }
}

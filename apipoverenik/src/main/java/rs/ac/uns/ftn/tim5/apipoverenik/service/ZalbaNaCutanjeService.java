package rs.ac.uns.ftn.tim5.apipoverenik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.xmldb.api.base.XMLDBException;
import rs.ac.uns.ftn.tim5.apipoverenik.model.zalbe_cutanja.ZalbaCutanja;
import rs.ac.uns.ftn.tim5.apipoverenik.repository.AbstractXmlRepository;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;

import static rs.ac.uns.ftn.tim5.apipoverenik.helper.XQueryExpressions.*;
import static rs.ac.uns.ftn.tim5.apipoverenik.helper.XQueryExpressions.X_UPDATE_REMOVE_ZALBA_NA_ODLUKU_BY_ID_EXPRESSION;

public class ZalbaNaCutanjeService implements AbstractXmlService<ZalbaCutanja> {

    @Autowired
    private AbstractXmlRepository<ZalbaCutanja> zalbaCutanjaAbstractXmlRepository;

    @PostConstruct
    public void injectRepositoryProperties(){
        this.zalbaCutanjaAbstractXmlRepository.injectRepositoryProperties(
                "/db/sample/zalbe_cutanja",
                "rs.ac.uns.ftn.tim5.apipoverenik.model.zalbe_cutanja",
                X_QUERY_FIND_ALL_ZALBE_CUTANJA_EXPRESSION,
                X_QUERY_FIND_ZALBA_CUTANJA_BY_ID_EXPRESSION,
                X_UPDATE_UPDATE_ZALBA_CUTANJA_BY_ID_EXPRESSION,
                X_UPDATE_REMOVE_ZALBA_CUTANJA_BY_ID_EXPRESSION
        );
    }

    @Override
    public List<ZalbaCutanja> findAll() {
        try {
            return this.zalbaCutanjaAbstractXmlRepository.getAllEntities();
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
    public ZalbaCutanja findById(Long entityId) {
        try {
            return this.zalbaCutanjaAbstractXmlRepository.getEntity(entityId);
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

        ZalbaCutanja zalbaCutanja = null;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ZalbaCutanja.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlEntity);
            zalbaCutanja = (ZalbaCutanja) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            //baci custom izuzetak kako nesto nije u redu sa poslatim dokumentom i uhvati ga u rest controller advice-u
            e.printStackTrace();
        }

        try {
            return zalbaCutanjaAbstractXmlRepository.createEntity(zalbaCutanja);
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
        ZalbaCutanja zalbaCutanja = null;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ZalbaCutanja.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlEntity);
            zalbaCutanja = (ZalbaCutanja) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            //baci custom izuzetak kako nesto nije u redu sa poslatim dokumentom i uhvati ga u rest controller advice-u
            e.printStackTrace();
        }

        try {
            return this.zalbaCutanjaAbstractXmlRepository.updateEntity(zalbaCutanja);
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
            return this.zalbaCutanjaAbstractXmlRepository.deleteEntity(entityId);
        } catch (XMLDBException e) {
            //baci izuzetak kako nesto nije uredu sa xml bazom
            e.printStackTrace();
        }
        return false;
    }

}

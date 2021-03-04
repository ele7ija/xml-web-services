package rs.ac.uns.ftn.tim5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.ac.uns.ftn.tim5.model.gradjanin.Gradjanin;
import rs.ac.uns.ftn.tim5.model.izvestaj.Izvestaj;
import rs.ac.uns.ftn.tim5.model.obavestenje.Obavestenje;
import rs.ac.uns.ftn.tim5.model.resenje.Resenje;
import rs.ac.uns.ftn.tim5.model.sluzbenik.Sluzbenik;
import rs.ac.uns.ftn.tim5.model.zahtev.Zahtev;
import rs.ac.uns.ftn.tim5.repository.AbstractXmlRepository;

@Configuration
public class AutowireConfig {

    @Bean
    public AbstractXmlRepository<Resenje> resenjeRepository() {
        return new AbstractXmlRepository<Resenje>();
    }

    @Bean
    public AbstractXmlRepository<Zahtev> zahtevRepository() {
        return new AbstractXmlRepository<Zahtev>();
    }

    @Bean
    public AbstractXmlRepository<Obavestenje> obavestenjeRepository() {
        return new AbstractXmlRepository<Obavestenje>();
    }

    @Bean
    public AbstractXmlRepository<Izvestaj> izvestajRepository() {
        return new AbstractXmlRepository<Izvestaj>();
    }

    @Bean
    public AbstractXmlRepository<Sluzbenik> sluzbenikRepository() {
        return new AbstractXmlRepository<Sluzbenik>();
    }

    @Bean
    public AbstractXmlRepository<Gradjanin> gradjaninRepository() {
        return new AbstractXmlRepository<Gradjanin>();
    }
}

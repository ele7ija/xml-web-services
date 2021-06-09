package rs.ac.uns.ftn.tim5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.ac.uns.ftn.tim5.model.gradjanin.Gradjanin;
import rs.ac.uns.ftn.tim5.model.izvestaj.Izvestaj;
import rs.ac.uns.ftn.tim5.model.poverenik.Poverenik;
import rs.ac.uns.ftn.tim5.model.resenje.Resenje;
import rs.ac.uns.ftn.tim5.model.zalba_na_odluku.ZalbaNaOdluku;
import rs.ac.uns.ftn.tim5.model.zalba_cutanja.ZalbaCutanja;
import rs.ac.uns.ftn.tim5.repository.AbstractXmlRepository;

@Configuration
public class AutowireConfig {

    @Bean
    public AbstractXmlRepository<Resenje> resenjeRepository() {
        return new AbstractXmlRepository<Resenje>();
    }

    @Bean
    public AbstractXmlRepository<ZalbaNaOdluku> zalbaNaOdlukuRepository() {
        return new AbstractXmlRepository<ZalbaNaOdluku>();
    }

    @Bean
    public AbstractXmlRepository<ZalbaCutanja> zalbaCutanjaRepository() {
        return new AbstractXmlRepository<ZalbaCutanja>();
    }

    @Bean
    public AbstractXmlRepository<Izvestaj> izvestajRepository() {
        return new AbstractXmlRepository<Izvestaj>();
    }

    @Bean
    public AbstractXmlRepository<Poverenik> poverenikRepository() {
        return new AbstractXmlRepository<Poverenik>();
    }

    @Bean
    public AbstractXmlRepository<Gradjanin> gradjaninRepository() {
        return new AbstractXmlRepository<Gradjanin>();
    }
}

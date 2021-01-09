package rs.ac.uns.ftn.tim5.apipoverenik.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.ac.uns.ftn.tim5.apipoverenik.model.resenje.Resenje;
import rs.ac.uns.ftn.tim5.apipoverenik.model.zalba_na_odluku.ZalbaNaOdluku;
import rs.ac.uns.ftn.tim5.apipoverenik.model.zalbe_cutanja.ZalbaCutanja;
import rs.ac.uns.ftn.tim5.apipoverenik.repository.AbstractXmlRepository;

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
}

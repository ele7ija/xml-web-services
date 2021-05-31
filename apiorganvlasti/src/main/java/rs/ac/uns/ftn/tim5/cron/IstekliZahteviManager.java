package rs.ac.uns.ftn.tim5.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.tim5.model.obavestenje.Obavestenje;
import rs.ac.uns.ftn.tim5.model.util.Datum;
import rs.ac.uns.ftn.tim5.model.zahtev.Zahtev;
import rs.ac.uns.ftn.tim5.service.ObavestenjeService;
import rs.ac.uns.ftn.tim5.service.ZahtevService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Component
public class IstekliZahteviManager {

    @Autowired
    private ZahtevService zahtevService;

    @Autowired
    private ObavestenjeService obavestenjeService;

    //broj dana pre nego sto zahtev istekne
    private final int X = 0;

    /**
     * Proverava svaki minut da li je odredjeni zahtev istekao
     * Zahtev je istekao ako je proslo vise od X dana od njegovog podnosenja, a da on nije obradjen
     */
    @Scheduled(cron = "0 * * * * ?")
    public void scheduleTaskWithFixedRate() throws DatatypeConfigurationException {
        System.out.println("Usao");
        List<Zahtev> zahtevi = this.zahtevService.findAll();
        for(Zahtev zahtev : zahtevi) {
            Obavestenje o = this.obavestenjeService.findByZahtevId(zahtev.getId());
            if (o == null && this.isExpired(zahtev.getDatum())) {
                Obavestenje obavestenje1 = new Obavestenje();

                obavestenje1.setIdZahteva(zahtev.getId());

                Obavestenje.Istekao istekao = new Obavestenje.Istekao();
                istekao.setValue(true);
                obavestenje1.setIstekao(istekao);

                Obavestenje.Odbijen odbijen = new Obavestenje.Odbijen();
                odbijen.setValue(false);
                obavestenje1.setOdbijen(odbijen);

                Obavestenje.Trazilac t1 = new Obavestenje.Trazilac();
                t1.setIme(zahtev.getTrazilac().getIme());
                t1.setPrezime(zahtev.getTrazilac().getPrezime());
                t1.setAdresa(zahtev.getTrazilac().getAdresa());
                obavestenje1.setTrazilac(t1);

                Obavestenje.Organ o1 = new Obavestenje.Organ();
                o1.setNaziv(zahtev.getOrgan().getNaziv());
                o1.setAdresa(zahtev.getOrgan().getAdresa());
                obavestenje1.setOrgan(o1);

                Obavestenje.Predmet p1 = new Obavestenje.Predmet();
                p1.setBrojPredmeta(-1);

                Obavestenje.Predmet.Datum datum = new Obavestenje.Predmet.Datum();
                Date date = Date.from(Instant.now());
                GregorianCalendar c = new GregorianCalendar();
                c.setTime(date);
                XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
                date2.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
                datum.setDan(date2);
                datum.setMesec(date2);
                datum.setGodina(date2);
                p1.setDatum(datum);

                obavestenje1.setPredmet(p1);

                this.obavestenjeService.create(obavestenje1);
            }
        }
    }

    public boolean isExpired(Datum datum) {
        Calendar date = new GregorianCalendar();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.set(Calendar.DAY_OF_MONTH, datum.getDan().getDay());
        date.set(Calendar.MONTH, datum.getMesec().getMonth());
        date.set(Calendar.YEAR, datum.getGodina().getYear());
        Date d = Date.from(date.toInstant());

        Date xDaysAgo = Date.from(Instant.now().minus(Duration.ofDays( this.X)));
        return d.after(xDaysAgo);
    }
}

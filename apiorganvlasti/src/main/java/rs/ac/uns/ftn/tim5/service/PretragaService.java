package rs.ac.uns.ftn.tim5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.tim5.dto.PretragaDTO;
import rs.ac.uns.ftn.tim5.dto.RezultatPretrageDTO;
import rs.ac.uns.ftn.tim5.helper.PretrageHelper;
import rs.ac.uns.ftn.tim5.model.izvestaj.Izvestaj;
import rs.ac.uns.ftn.tim5.model.izvestaj.KolekcijaIzvestaja;
import rs.ac.uns.ftn.tim5.model.osnovnapretraga.OsnovnaPretraga;
import rs.ac.uns.ftn.tim5.model.osnovnapretraga.RezultatOsnovnePretrage;
import rs.ac.uns.ftn.tim5.model.resenje.KolekcijaResenja;
import rs.ac.uns.ftn.tim5.model.resenje.Resenje;
import rs.ac.uns.ftn.tim5.model.zahtev.KolekcijaZahteva;
import rs.ac.uns.ftn.tim5.model.zahtev.Zahtev;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PretragaService {

    @Autowired
    private ZahtevService zahtevService;

    @Autowired
    private ResenjeService resenjeService;

    @Autowired
    private IzvestajService izvestajService;

    // TODO IZVESTAJE UKLJUCITI
    public RezultatPretrageDTO pronadji(PretragaDTO pretraga) {

        System.out.println("pronadji: termin=" + pretraga.getTerm() + ", metadata=" + pretraga.getMetadata());

        List<Zahtev> zahteviTerm = new ArrayList<>();
        List<Resenje> resenjaTerm = new ArrayList<>();
        List<Izvestaj> izvestajiTerm = new ArrayList<>();
        if (!pretraga.getTerm().equals("")) {
            // pretraga po termu
            zahteviTerm = zahtevService.pronadjiTerm(pretraga.getTerm());
            resenjaTerm = resenjeService.pronadjiTerm(pretraga.getTerm());
            izvestajiTerm = izvestajService.pronadjiTerm(pretraga.getTerm());
        }

        List<Zahtev> zahteviMetadata = new ArrayList<>();
        List<Resenje> resenjaMetadata = new ArrayList<>();
        List<Izvestaj> izvestajiMetadata = new ArrayList<>();
        if (!pretraga.getMetadata().equals("")) {
            // pretraga po metapodacima
            zahteviMetadata = zahtevService.pronadjiMetadata(pretraga.getMetadata());
            resenjaMetadata = resenjeService.pronadjiMetadata(pretraga.getMetadata());
            izvestajiMetadata = izvestajService.pronadjiMetadata(pretraga.getMetadata());
        }

        Set<Zahtev> zahtevi = new HashSet<>(zahteviTerm);
        Set<Resenje> resenja = new HashSet<>(resenjaTerm);
        Set<Izvestaj> izvestaji = new HashSet<>(izvestajiTerm);
        zahtevi.addAll(zahteviMetadata);
        resenja.addAll(resenjaMetadata);
        izvestaji.addAll(izvestajiMetadata);

        KolekcijaZahteva kz = new KolekcijaZahteva();
        kz.setZahtev(new ArrayList<>(zahtevi));
        KolekcijaResenja kr = new KolekcijaResenja();
        kr.setResenje(new ArrayList<>(resenja));
        KolekcijaIzvestaja ki = new KolekcijaIzvestaja();
        ki.setIzvestaj(new ArrayList<>(izvestaji));
        return new RezultatPretrageDTO(kz, kr, ki);

    }
}

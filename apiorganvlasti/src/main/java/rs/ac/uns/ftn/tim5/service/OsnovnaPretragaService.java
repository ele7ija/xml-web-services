package rs.ac.uns.ftn.tim5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.tim5.helper.PretrageHelper;
import rs.ac.uns.ftn.tim5.model.izvestaj.Izvestaj;
import rs.ac.uns.ftn.tim5.model.osnovnapretraga.OsnovnaPretraga;
import rs.ac.uns.ftn.tim5.model.osnovnapretraga.RezultatOsnovnePretrage;
import rs.ac.uns.ftn.tim5.model.resenje.Resenje;
import rs.ac.uns.ftn.tim5.model.zahtev.KolekcijaZahteva;
import rs.ac.uns.ftn.tim5.model.zahtev.Zahtev;

import java.util.ArrayList;
import java.util.List;

@Service
public class OsnovnaPretragaService {

    @Autowired
    private ZahtevService zahtevService;

    @Autowired
    private ResenjeService resenjeService;

    @Autowired
    private IzvestajService izvestajService;

    public RezultatOsnovnePretrage pronadji(OsnovnaPretraga pretraga) {
        RezultatOsnovnePretrage rop = new RezultatOsnovnePretrage();

        // Pronadji sve zahteve koji zadovoljavaju
        List<Zahtev> zahtevi = zahtevService.findAll();
        List<Zahtev> zahtevRet = new ArrayList<>();
        for (Zahtev z: zahtevi) {
            if (PretrageHelper.sadrziRec(z.getOpisZahteva(), pretraga.getTermin())) {
                zahtevRet.add(z);
                continue;
            }
            if (PretrageHelper.sadrziRec(z.getMesto().getValue(), pretraga.getTermin())) {
                zahtevRet.add(z);
                // continue;
            }
        }
        KolekcijaZahteva kz = new KolekcijaZahteva();
        kz.setZahtev(zahtevRet);
        rop.setKolekcijaZahteva(kz);

        // Pronadji sva resenja
        List<Resenje> resenja = resenjeService.findAll();
        // Pronadji sve izvestaje
        List<Izvestaj> izvestaji = izvestajService.findAll();
        return rop;
    }
}

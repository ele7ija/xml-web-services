package rs.ac.uns.ftn.tim5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.tim5.dto.PretragaDTO;
import rs.ac.uns.ftn.tim5.dto.ReferencedByDTO;
import rs.ac.uns.ftn.tim5.dto.RezultatPretrageDTO;
import rs.ac.uns.ftn.tim5.model.izvestaj.Izvestaj;
import rs.ac.uns.ftn.tim5.model.izvestaj.KolekcijaIzvestaja;
import rs.ac.uns.ftn.tim5.model.resenje.KolekcijaResenja;
import rs.ac.uns.ftn.tim5.model.resenje.Resenje;
import rs.ac.uns.ftn.tim5.model.zalba_cutanja.KolekcijaZalbiCutanja;
import rs.ac.uns.ftn.tim5.model.zalba_cutanja.ZalbaCutanja;
import rs.ac.uns.ftn.tim5.model.zalba_na_odluku.KolekcijaZalbiNaOdluku;
import rs.ac.uns.ftn.tim5.model.zalba_na_odluku.ZalbaNaOdluku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PretragaService {

    @Autowired
    private ResenjeService resenjeService;

    @Autowired
    private IzvestajService izvestajService;

    @Autowired
    private ZalbaNaCutanjeService zalbaNaCutanjeService;

    @Autowired
    private ZalbaNaOdlukuService zalbaNaOdlukuService;

    @Autowired
    private ObavestenjeService obavestenjeService;

    public RezultatPretrageDTO pronadji(PretragaDTO pretraga) {

        System.out.println("pronadji: termin=" + pretraga.getTerm() + ", metadata=" + pretraga.getMetadata());

        List<ZalbaNaOdluku> zalbeOdlukaTerm = new ArrayList<>();
        List<ZalbaCutanja> zalbeCutanjeTerm = new ArrayList<>();
        List<Resenje> resenjaTerm = new ArrayList<>();
        List<Izvestaj> izvestajiTerm = new ArrayList<>();
        if (!pretraga.getTerm().equals("")) {
            // pretraga po termu
            zalbeOdlukaTerm = zalbaNaOdlukuService.pronadjiTerm(pretraga.getTerm());
            zalbeCutanjeTerm = zalbaNaCutanjeService.pronadjiTerm(pretraga.getTerm());
            resenjaTerm = resenjeService.pronadjiTerm(pretraga.getTerm());
            izvestajiTerm = izvestajService.pronadjiTerm(pretraga.getTerm());
        }

        List<ZalbaNaOdluku> zalbeOdlukaMetadata = new ArrayList<>();
        List<ZalbaCutanja> zalbeCutanjeMetadata = new ArrayList<>();
        List<Resenje> resenjaMetadata = new ArrayList<>();
        List<Izvestaj> izvestajiMetadata = new ArrayList<>();
        if (!pretraga.getMetadata().equals("")) {
            // pretraga po metapodacima
            zalbeOdlukaMetadata = zalbaNaOdlukuService.pronadjiMetadata(pretraga.getMetadata());
            zalbeCutanjeMetadata = zalbaNaCutanjeService.pronadjiMetadata(pretraga.getMetadata());
            resenjaMetadata = resenjeService.pronadjiMetadata(pretraga.getMetadata());
            izvestajiMetadata = izvestajService.pronadjiMetadata(pretraga.getMetadata());
        }

        Set<ZalbaNaOdluku> zalbeo = new HashSet<>(zalbeOdlukaTerm);
        Set<ZalbaCutanja> zalbec = new HashSet<>(zalbeCutanjeTerm);
        Set<Resenje> resenja = new HashSet<>(resenjaTerm);
        Set<Izvestaj> izvestaji = new HashSet<>(izvestajiTerm);
        zalbeo.addAll(zalbeOdlukaMetadata);
        zalbec.addAll(zalbeCutanjeMetadata);
        resenja.addAll(resenjaMetadata);
        izvestaji.addAll(izvestajiMetadata);

        KolekcijaZalbiNaOdluku kz = new KolekcijaZalbiNaOdluku();
        kz.setZalbaNaOdluku(new ArrayList<>(zalbeo));
        KolekcijaZalbiCutanja ko = new KolekcijaZalbiCutanja();
        ko.setZalbaCutanja(new ArrayList<>(zalbec));
        KolekcijaResenja kr = new KolekcijaResenja();
        kr.setResenje(new ArrayList<>(resenja));
        KolekcijaIzvestaja ki = new KolekcijaIzvestaja();
        ki.setIzvestaj(new ArrayList<>(izvestaji));
        return new RezultatPretrageDTO(kz, ko, kr, ki);

    }

    public ReferencedByDTO referencedBy(String about) {

        about = "\"" + about + "\"";
        ReferencedByDTO retval = new ReferencedByDTO(about, new ArrayList<>());

        List<String> resenja = resenjeService.getRefers(about);
        List<String> izvestaji = izvestajService.getRefers(about);
        List<String> zalbeodluka = zalbaNaOdlukuService.getRefers(about);
        List<String> zalbecutanje = zalbaNaCutanjeService.getRefers(about);
        List<String> obavestenja = obavestenjeService.getRefers(about);

        retval.getDokument().addAll(resenja);
        retval.getDokument().addAll(izvestaji);
        retval.getDokument().addAll(zalbeodluka);
        retval.getDokument().addAll(zalbecutanje);
        retval.getDokument().addAll(obavestenja);

        return retval;
    }
}

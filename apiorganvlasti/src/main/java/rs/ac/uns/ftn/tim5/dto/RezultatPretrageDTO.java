package rs.ac.uns.ftn.tim5.dto;

import rs.ac.uns.ftn.tim5.model.izvestaj.KolekcijaIzvestaja;
import rs.ac.uns.ftn.tim5.model.resenje.KolekcijaResenja;
import rs.ac.uns.ftn.tim5.model.zahtev.KolekcijaZahteva;
import rs.ac.uns.ftn.tim5.model.zahtev.Zahtev;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "zahtevi", "resenja", "izvestaji" })
@XmlRootElement(name = "rezultat_pretrage")
public class RezultatPretrageDTO {

    @XmlElement(required = true, type = KolekcijaZahteva.class)
    private KolekcijaZahteva zahtevi;

    @XmlElement(required = true, type = KolekcijaResenja.class)
    private KolekcijaResenja resenja;

    @XmlElement(required = true, type = KolekcijaIzvestaja.class)
    private KolekcijaIzvestaja izvestaji;

    public RezultatPretrageDTO(KolekcijaZahteva zahtevi, KolekcijaResenja resenja, KolekcijaIzvestaja izvestaji) {
        this.zahtevi = zahtevi;
        this.resenja = resenja;
        this.izvestaji = izvestaji;
    }

    public RezultatPretrageDTO() {
        this.zahtevi = new KolekcijaZahteva();
        this.resenja = new KolekcijaResenja();
        this.izvestaji = new KolekcijaIzvestaja();
    }

    public KolekcijaZahteva getZahtevi() {
        return zahtevi;
    }

    public void setZahtevi(KolekcijaZahteva zahtevi) {
        this.zahtevi = zahtevi;
    }

    public KolekcijaResenja getResenja() {
        return resenja;
    }

    public void setResenja(KolekcijaResenja resenja) {
        this.resenja = resenja;
    }

    public KolekcijaIzvestaja getIzvestaji() {
        return izvestaji;
    }

    public void setIzvestaji(KolekcijaIzvestaja izvestaji) {
        this.izvestaji = izvestaji;
    }
}

package rs.ac.uns.ftn.tim5.dto;

import rs.ac.uns.ftn.tim5.model.izvestaj.KolekcijaIzvestaja;
import rs.ac.uns.ftn.tim5.model.resenje.KolekcijaResenja;
import rs.ac.uns.ftn.tim5.model.zalba_cutanja.KolekcijaZalbiCutanja;
import rs.ac.uns.ftn.tim5.model.zalba_na_odluku.KolekcijaZalbiNaOdluku;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "zalbe_odluka", "zalbe_cutanje", "resenja", "izvestaji" })
@XmlRootElement(name = "rezultat_pretrage")
public class RezultatPretrageDTO {

    @XmlElement(required = true, type = KolekcijaZalbiNaOdluku.class)
    private KolekcijaZalbiNaOdluku zalbe_odluka;

    @XmlElement(required = true, type = KolekcijaZalbiCutanja.class)
    private KolekcijaZalbiCutanja zalbe_cutanje;

    @XmlElement(required = true, type = KolekcijaResenja.class)
    private KolekcijaResenja resenja;

    @XmlElement(required = true, type = KolekcijaIzvestaja.class)
    private KolekcijaIzvestaja izvestaji;

    public RezultatPretrageDTO() {
    }

    public KolekcijaZalbiNaOdluku getZalbe_odluka() {
        return zalbe_odluka;
    }

    public void setZalbe_odluka(KolekcijaZalbiNaOdluku zalbe_odluka) {
        this.zalbe_odluka = zalbe_odluka;
    }

    public KolekcijaZalbiCutanja getZalbe_cutanje() {
        return zalbe_cutanje;
    }

    public void setZalbe_cutanje(KolekcijaZalbiCutanja zalbe_cutanje) {
        this.zalbe_cutanje = zalbe_cutanje;
    }

    public RezultatPretrageDTO(KolekcijaZalbiNaOdluku zalbe_odluka, KolekcijaZalbiCutanja zalbe_cutanje, KolekcijaResenja resenja, KolekcijaIzvestaja izvestaji) {
        this.zalbe_odluka = zalbe_odluka;
        this.zalbe_cutanje = zalbe_cutanje;
        this.resenja = resenja;
        this.izvestaji = izvestaji;
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

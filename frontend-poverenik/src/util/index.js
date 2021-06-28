/*eslint no-undef: "warn"*/
export const constructGradjanin = xml => {
  xml = Xonomy.xml2js(xml);
  console.log(xml);
  return {
    korisnicko_ime: xml.getChildElements('gr:korisnicko_ime')[0].getText(),
    lozinka: xml.getChildElements('gr:lozinka')[0].getText(),
    podaci: {
      adresa: {
        mesto: xml.getChildElements('gr:podaci')[0].getChildElements('util:Adresa')[0].getChildElements('util:Mesto')[0].getText(),
        ulica: xml.getChildElements('gr:podaci')[0].getChildElements('util:Adresa')[0].getChildElements('util:Ulica')[0].getText(),
        broj: xml.getChildElements('gr:podaci')[0].getChildElements('util:Adresa')[0].getChildElements('util:Broj')[0].getText(), 
      },
      ime: xml.getChildElements('gr:podaci')[0].getChildElements('util:Ime')[0].getText(),
      prezime: xml.getChildElements('gr:podaci')[0].getChildElements('util:Prezime')[0].getText(),
    }
  };
};

export const constructDatum = xml => {
  return `${xml.getChildElements("util:dan")[0].getText().substr(3)}.${xml.getChildElements("util:mesec")[0].getText().substr(2)}.${xml.getChildElements("util:godina")[0].getText()}`
};

export const constructKolekcijaObavestenja = xml => {
  xml = Xonomy.xml2js(xml);
  return xml.getChildElements("ob:Obavestenje").map(x => constructObavestenje(x));
};

export const constructObavestenje = xml => {
  return {
    id: xml.getAttribute("id").value,
    about: xml.getAttribute("about").value,
    id_zahteva: xml.getAttribute("id_zahteva").value,
    zahtev_url: xml.getAttribute("content").value,
    odbijen: xml.getChildElements("ob:odbijen")[0].getText() == 'true' ? 'da' : 'ne',
    istekao: xml.getChildElements("ob:istekao")[0].getText() == 'true' ? 'da' : 'ne',
    organ: {
      naziv: xml.getChildElements("ob:Organ")[0].getChildElements("util:Naziv")[0].getText(),
      adresa: {
        mesto: `${xml.getChildElements("ob:Organ")[0].getChildElements("util:Adresa")[0].getChildElements("util:Mesto")[0].getText()}`,
        ulica: `${xml.getChildElements("ob:Organ")[0].getChildElements("util:Adresa")[0].getChildElements("util:Ulica")[0].getText()}`,
        broj: `${xml.getChildElements("ob:Organ")[0].getChildElements("util:Adresa")[0].getChildElements("util:Broj")[0].getText()}`
      },
    },
    trazilac: {
      imePrezime: `${xml.getChildElements("ob:Trazilac")[0].getChildElements("util:Ime")[0].getText()} ${xml.getChildElements("ob:Trazilac")[0].getChildElements("util:Prezime")[0].getText()}`,
      ime: `${xml.getChildElements("ob:Trazilac")[0].getChildElements("util:Ime")[0].getText()}`,
      prezime: `${xml.getChildElements("ob:Trazilac")[0].getChildElements("util:Prezime")[0].getText()}`,
      adresa: {
        mesto: `${xml.getChildElements("ob:Trazilac")[0].getChildElements("util:Adresa")[0].getChildElements("util:Mesto")[0].getText()}`,
        ulica: `${xml.getChildElements("ob:Trazilac")[0].getChildElements("util:Adresa")[0].getChildElements("util:Ulica")[0].getText()}`,
        broj: `${xml.getChildElements("ob:Trazilac")[0].getChildElements("util:Adresa")[0].getChildElements("util:Broj")[0].getText()}`
      },
    },
    predmet: {
      brojPredmeta: xml.getChildElements("ob:Predmet")[0].getChildElements("ob:Broj_Predmeta")[0].getText(), 
      datum: constructDatum(xml.getChildElements("ob:Predmet")[0].getChildElements("ob:Datum")[0]),
      opisZahteva: xml.getChildElements("ob:Predmet")[0].getChildElements("ob:Opis_Trazene_Informacije").length == 0 ?
        "" :
        xml.getChildElements("ob:Predmet")[0].getChildElements("ob:Opis_Trazene_Informacije")[0].getText()
    }
  };
};

export const constructKolekcijaZalbi = xml => {
  xml = Xonomy.xml2js(xml);
  return xml.getChildElements("zo:Zalba_na_odluku").map(x => constructZalbaNaOdluku(x));
};

export const constructZalbaNaOdluku = xml => {
  return {
    id: xml.getAttribute("id").value,
    about: xml.getAttribute("about").value,
    zahtev_url: "http://localhost:8086/zahtev/" + xml.getAttribute("id_zahteva").value,
    status: 
      xml.getChildElements("zo:odgovor_organa_vlasti")[0].getChildElements("zo:prihvatio")[0].getText() == 'da' ? 
        'prihvaceno' :
        xml.getChildElements("zo:odgovor_organa_vlasti")[0].getChildElements("zo:prihvatio")[0].getText() == 'da' ?
          'odbijeno' :
          'na cekanju...',
    organVlasti: {
      naziv: xml.getChildElements("zo:organ_vlasti")[0].getChildElements("util:Naziv")[0].getText(),
    },
    datum: constructDatum(xml.getChildElements("zo:datum_zalbe")[0]),
  };
};

export const constructKolekcijaZalbiNaCutanje = xml => {
  xml = Xonomy.xml2js(xml);
  return xml.getChildElements("zc:Zalba_cutanja").map(x => constructZalbaNaCutanje(x));
};

export const constructZalbaNaCutanje = xml => {
  return {
    id: xml.getAttribute("id").value,
    about: xml.getAttribute("about").value,
    zahtev_url: xml.getAttribute("content").value,
    status: 
      xml.getChildElements("zc:odgovor_organa_vlasti")[0].getChildElements("zc:prihvatio")[0].getText() == 'da' ? 
        'prihvaceno' :
        xml.getChildElements("zc:odgovor_organa_vlasti")[0].getChildElements("zc:prihvatio")[0].getText() == 'da' ?
          'odbijeno' :
          'na cekanju...',
    organVlasti: {
      naziv: xml.getChildElements("zc:organ_vlasti")[0].getChildElements("util:Naziv")[0].getText(),
    },
    datum: constructDatum(xml.getChildElements("zc:datum_zalbe")[0]),
  };
};

export const constructZahtev = xml => {
  return {
    id: xml.getAttribute("id").value,
    about: xml.getAttribute("about").value,
    datum: constructDatum(xml.getChildElements("za:datum")[0])
  };
};

export const constructKolekcijaIzvestaja = xml => {
  xml = Xonomy.xml2js(xml);
  let mapa = xml.getChildElements("iz:Izvestaj").map(x => constructIzvestaj(x))
  return mapa;
}

export const constructIzvestaj = xml => {
  return {
    id: xml.getAttribute("id").value,
    about: xml.getAttribute("about").value,
    organ_vlasti: {
      naziv: xml.getChildElements("iz:organ_vlasti")[0].getChildElements("util:Naziv")[0].getText(),
      adresa: {
        mesto: `${xml.getChildElements("iz:organ_vlasti")[0].getChildElements("util:Adresa")[0].getChildElements("util:Mesto")[0].getText()}`,
        ulica: `${xml.getChildElements("iz:organ_vlasti")[0].getChildElements("util:Adresa")[0].getChildElements("util:Ulica")[0].getText()}`,
        broj: `${xml.getChildElements("iz:organ_vlasti")[0].getChildElements("util:Adresa")[0].getChildElements("util:Broj")[0].getText()}`
      },
    },
    datum_podnosenja: constructDatum(xml.getChildElements("iz:datum_podnosenja")[0]),
  };
}
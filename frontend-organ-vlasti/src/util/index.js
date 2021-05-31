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

export const constructKolekcijaZahteva = xml => {
  xml = Xonomy.xml2js(xml);
  return xml.getChildElements("za:Zahtev").map(x => constructZahtev(x));
}

export const constructZahtev = xml => {
  console.log(xml);
  return {
    id: xml.getAttribute("id").value,
    about: xml.getAttribute("about").value,
    datum: constructDatum(xml.getChildElements("za:datum")[0]),
    organ: {
      naziv: xml.getChildElements("za:organ")[0].getChildElements("util:Naziv")[0].getText(),
      adresa: {
        mesto: `${xml.getChildElements("za:organ")[0].getChildElements("util:Adresa")[0].getChildElements("util:Mesto")[0].getText()}`,
        ulica: `${xml.getChildElements("za:organ")[0].getChildElements("util:Adresa")[0].getChildElements("util:Ulica")[0].getText()}`,
        broj: `${xml.getChildElements("za:organ")[0].getChildElements("util:Adresa")[0].getChildElements("util:Broj")[0].getText()}`
      },
    },
    trazilac: {
      imePrezime: `${xml.getChildElements("za:trazilac")[0].getChildElements("util:Ime")[0].getText()} ${xml.getChildElements("za:trazilac")[0].getChildElements("util:Prezime")[0].getText()}`,
      ime: `${xml.getChildElements("za:trazilac")[0].getChildElements("util:Ime")[0].getText()}`,
      prezime: `${xml.getChildElements("za:trazilac")[0].getChildElements("util:Prezime")[0].getText()}`,
      adresa: {
        mesto: `${xml.getChildElements("za:trazilac")[0].getChildElements("util:Adresa")[0].getChildElements("util:Mesto")[0].getText()}`,
        ulica: `${xml.getChildElements("za:trazilac")[0].getChildElements("util:Adresa")[0].getChildElements("util:Ulica")[0].getText()}`,
        broj: `${xml.getChildElements("za:trazilac")[0].getChildElements("util:Adresa")[0].getChildElements("util:Broj")[0].getText()}`
      },
    },
    opisZahteva: xml.getChildElements("za:opis_zahteva")[0].getText(),
    elementiZahteva: xml.getChildElements("za:elementi_zahteva")[0].getChildElements("za:Element_Zahteva").map(x => constructElementZahteva(x))
  };
}

export const constructDatum = xml => {
  return `${xml.getChildElements("util:dan")[0].getText().substr(3)}.${xml.getChildElements("util:mesec")[0].getText().substr(2)}.${xml.getChildElements("util:godina")[0].getText()}`
}

const constructElementZahteva = elementZahteva => {
  return {
      tekst: elementZahteva.getChildElements("util:Tekst")[0].getText(),
      metodDostave: elementZahteva.hasChildElement("util:Metod_Dostave") ? elementZahteva.getChildElements("util:Metod_Dostave")[0].getText() : null
  }
};

export const constructObavestenje = xml => {
  xml = Xonomy.xml2js(xml);
  return {
    id: xml.getAttribute("id").value,
    about: xml.getAttribute("about").value,
    id_zahteva: xml.getAttribute("id_zahteva").value,
    zahtev_url: xml.getAttribute("content").value,
    odbijen: xml.getChildElements("ob:odbijen")[0].getText() == 'true' ? true : false,
    istekao: xml.getChildElements("ob:istekao")[0].getText() == 'true' ? true : false,
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
      datum: constructDatum(xml.getChildElements("ob:Predmet")[0].getChildElements("ob:Datum")[0]),
      opisZahteva: xml.getChildElements("ob:Predmet")[0].getChildElements("ob:Opis_Trazene_Informacije")[0]
    }
  };
};

export const odbijObavestenjeXml = zahtev => `<?xml version="1.0" encoding="UTF-8"?>
<ob:Obavestenje
    xmlns:ob="http://ftn.uns.ac.rs/tim5/model/obavestenje"
    xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"
    xmlns:xs="http://www.w3.org/2001/XMLSchema-instance"
    id_zahteva="${zahtev.id}">
    <ob:odbijen>true</ob:odbijen>
    <ob:istekao>false</ob:istekao>
    <ob:Trazilac>
        <util:Adresa>
            <util:Mesto>${zahtev.trazilac.adresa.mesto}</util:Mesto>
            <util:Ulica>${zahtev.trazilac.adresa.ulica}</util:Ulica>
            <util:Broj>${zahtev.trazilac.adresa.broj}</util:Broj>
        </util:Adresa>
        <util:Ime>${zahtev.trazilac.ime}</util:Ime>
        <util:Prezime>${zahtev.trazilac.prezime}</util:Prezime>
    </ob:Trazilac>
    <ob:Organ>
        <util:Adresa>
            <util:Mesto>${zahtev.organ.adresa.mesto}</util:Mesto>
            <util:Ulica>${zahtev.organ.adresa.ulica}</util:Ulica>
            <util:Broj>${zahtev.organ.adresa.broj}</util:Broj>
        </util:Adresa>
        <util:Naziv>${zahtev.organ.naziv}</util:Naziv>
    </ob:Organ>
    <ob:Predmet>
        <ob:Broj_Predmeta></ob:Broj_Predmeta>
        <ob:Datum>
            <util:dan>${"---"+new Date().getDate()}</util:dan>
            <util:mesec>${"--"+getCurrentMonth()}</util:mesec>
            <util:godina>${new Date().getFullYear()}</util:godina>
        </ob:Datum>
        <ob:Opis_Trazene_Informacije>${zahtev.opisZahteva}</ob:Opis_Trazene_Informacije>
        <ob:Pravni_Osnov>
            <util:Naziv>Закона о слободном приступу информацијама од јавног значаја</util:Naziv>
            <util:Clan>16</util:Clan>
            <util:Strana>1</util:Strana>
            <util:Dopune>
                <util:Dopuna>
                    <util:Broj_dopune>16</util:Broj_dopune>
                    <util:Godina>2020</util:Godina>
                </util:Dopuna>
            </util:Dopune>
        </ob:Pravni_Osnov>
        ${
          zahtev.elementiZahteva.map((x,i) => {
            return `
              <ob:Odgovor>
                <util:Odobren>true</util:Odobren>
                <util:Element_Zahteva>
                    <util:Tekst>${x.tekst}</util:Tekst>
                    ${x.metodDostave ? `<util:Metod_Dostave>${x.metodDostave}</util:Metod_Dostave>` : ""}
                </util:Element_Zahteva>
                ${
                  i == 0 ?
                  `<util:Uvid>
                    <util:Datum_Uvida>
                        <util:dan>${"---"+new Date().getDate()}</util:dan>
                        <util:mesec>${"--"+getCurrentMonth()}</util:mesec>
                        <util:godina>${new Date().getFullYear()}</util:godina>
                    </util:Datum_Uvida>
                    <util:Vreme_Uvida>
                        <util:Sat>15</util:Sat>
                        <util:Minut>30</util:Minut>
                    </util:Vreme_Uvida>
                    </util:Uvid>`
                    :
                    ''
                }
               </ob:Odgovor>
            `;
          })
        }
        <ob:Uplata>
            <util:Racun>
                <util:Broj_Racuna>840-742328843-30</util:Broj_Racuna>
            </util:Racun>
            <util:Iznos>0</util:Iznos>
            <util:Poziv_Na_Broj>97</util:Poziv_Na_Broj>
            <util:Pravni_Osnov>
                <util:Naziv>Правилника о условима и начину вођења рачуна</util:Naziv>
                <util:Dopune>
                    <util:Dopuna>
                        <util:Broj_dopune>20</util:Broj_dopune>
                        <util:Godina>2007</util:Godina>
                    </util:Dopuna>
                    <util:Dopuna>
                        <util:Broj_dopune>40</util:Broj_dopune>
                        <util:Godina>2010</util:Godina>
                    </util:Dopuna>
                </util:Dopune>
            </util:Pravni_Osnov>
        </ob:Uplata>
    </ob:Predmet>
</ob:Obavestenje>`

const getCurrentMonth = () => {
  const month = new Date().getMonth() + 1;
  if(month < 10) {
    return '0' + month;
  } else {
    return month;
  }
}
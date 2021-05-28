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

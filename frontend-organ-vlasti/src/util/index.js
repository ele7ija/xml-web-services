/*eslint no-undef: "warn"*/
export const constructGradjanin = xml => {
  xml = Xonomy.xml2js(xml);
  return {
    korisnicko_ime: xml.getChildElements('korisnicko_ime')[0].getText(),
    lozinka: xml.getChildElements('lozinka')[0].getText(),
    podaci: {
      adresa: {
        mesto: xml.getChildElements('podaci')[0].getChildElements('ns2:Adresa')[0].getChildElements('ns2:Mesto')[0].getText(),
        ulica: xml.getChildElements('podaci')[0].getChildElements('ns2:Adresa')[0].getChildElements('ns2:Ulica')[0].getText(),
        broj: xml.getChildElements('podaci')[0].getChildElements('ns2:Adresa')[0].getChildElements('ns2:Broj')[0].getText(), 
      },
      ime: xml.getChildElements('podaci')[0].getChildElements('ns2:Ime')[0].getText(),
      prezime: xml.getChildElements('podaci')[0].getChildElements('ns2:Prezime')[0].getText(),
    }
  };
};

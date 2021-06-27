<template>
  <div>
    Prihvacenih: {{getStatistikaZahteva().brojPrihvacenih}}
    Odbijenih: {{getStatistikaZahteva().brojOdbijenih}}
    br zalbi na odluku: {{getBrojZalbiNaOdluku()}}
    br nije postupio: {{getStatistikaZalbiNaCutanje().brNijePostupio}}
    br nije postupio u celosti: {{getStatistikaZalbiNaCutanje().brNijePostupioUCelosti}}
  </div>
</template>

<script>
/*eslint no-undef: "warn"*/
/*eslint no-unused-vars: "warn"*/
const { Octicon, check, x, clippy, code } = require('octicons-vue');
import zahtevApi from '../../../api/zahtev';
import obavestenjeApi from '../../../api/obavestenje';
import zalbaNaCutanjeApi from '../../../api/zalba_cutanje';
import zalbaNaOdlukuApi from '../../../api/zalba_odluka';
import { constructKolekcijaZahteva, odbijObavestenjeXml, constructObavestenje, constructKolekcijaZalbiNaOdluku, constructKolekcijaZalbiNaCutanje } from '../../../util';
export default {
  name: 'Izvestaj',
  data: () => {
    return {
      check,
      x,
      clippy,
      code,
      zahtevi: [],
      loading: false,

      idZahtevaSelected: null,
      odbijLoading: false,
      pdfLoading: false,
      htmlLoading: false,

      obradjeniZahtevi: [],
      obavestenja: [],
      idObavestenjaSelected: null,

      zalbe_cutanje: [],
      zalbe_odluka: []
    };
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      this.loading = true;
      const tempZahtevi = constructKolekcijaZahteva((await zahtevApi.getByNazivOrganaVlasti()).data);
      const results = await Promise.all(
        tempZahtevi.map(zahtev => obavestenjeApi.getObavestenjeByIdZahteva(zahtev.id))
      );
      for(const index in results) {
        if (!results[index].data) {
          this.zahtevi.push(tempZahtevi[index]);
        } else {
          this.obradjeniZahtevi.push(tempZahtevi[index]);
        }
      }
      this.obavestenja = results.filter(x => x.data).map(x => constructObavestenje(Xonomy.xml2js(x.data)));
      this.loading = false;

      // get zalbe
      const zalbe_odluka = (await zalbaNaOdlukuApi.getAll()).data;
      console.log('zalbe_odluka: ' + zalbe_odluka);
      this.zalbe_odluka = constructKolekcijaZalbiNaOdluku(zalbe_odluka);
      console.log('zalbe_odluka (formatirano): ' + JSON.stringify(this.zalbe_odluka));

      const zalbe_cutanje = (await zalbaNaCutanjeApi.getAll()).data;
      console.log('zalbe_cutanje: ' + zalbe_cutanje);
      this.zalbe_cutanje = constructKolekcijaZalbiNaCutanje(zalbe_cutanje);
      console.log('zalbe_cutanje (formatirano): ' + JSON.stringify(this.zalbe_cutanje));
    }
  },
  methods: {
    getStatistikaZahteva() {
      let statistika = {
        brojPrihvacenih: 0,
        brojOdbijenih: 0,
        brojIsteklih: 0
      }
      for (let obavestenje of this.obavestenja) {
        if (obavestenje.odbijen) {
          statistika.brojOdbijenih += 1
        }
        else if (obavestenje.istekao) {
          statistika.brojIsteklih += 1
        }
        else {
          statistika.brojPrihvacenih += 1
        }
      }
      return statistika
    },
    getBrojZalbiNaOdluku() {
      return this.zalbe_odluka.length
    },
    getStatistikaZalbiNaCutanje() {
      let brNijePostupio = 0;
      let brNijePostupioUCelosti = 0;
      for (let zalba_cutanje of this.zalbe_cutanje) {
        if (zalba_cutanje.razlog_zalbe == 'није поступио') {
          brNijePostupio++;
        }
        else {
          brNijePostupioUCelosti++;
        }
      }
      return {
        brNijePostupio,
        brNijePostupioUCelosti
      }
    }
  }
}
</script>

<style>

</style>
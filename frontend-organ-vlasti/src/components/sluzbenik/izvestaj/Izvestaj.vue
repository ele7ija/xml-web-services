<template>
  <div>
    Prihvacenih: {{getStatistikaZahteva().brojPrihvacenih}}
    Odbijenih: {{getStatistikaZahteva().brojOdbijenih}}
  </div>
</template>

<script>
/*eslint no-undef: "warn"*/
/*eslint no-unused-vars: "warn"*/
const { Octicon, check, x, clippy, code } = require('octicons-vue');
import zahtevApi from '../../../api/zahtev';
import obavestenjeApi from '../../../api/obavestenje';
import { constructKolekcijaZahteva, odbijObavestenjeXml, constructObavestenje } from '../../../util';
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
      idObavestenjaSelected: null
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
    }
  }
}
</script>

<style>

</style>
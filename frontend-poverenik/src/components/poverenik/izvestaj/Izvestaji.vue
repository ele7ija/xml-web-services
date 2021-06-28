<template>
  <div class="container-fluid my-4">
    <div class="row mx-5">
      <div class="col-12">
        <h3 class="text-center my-3">Izvestaji</h3>
        <table class="table table-sm table-bordered mt-3">
          <thead>
            <tr>
              <th class="text-center">ID</th>
              <th class="text-center">Datum podnosenja</th>
              <th class="text-center">Organ Vlasti</th>
              <th class="text-center" :style="{width: '14%'}">PDF</th>
              <th class="text-center" :style="{width: '15%'}">HTML</th>
            </tr>
          </thead>
          <tbody v-if="!izvestajiLoading">
            <tr
              v-for="(izvestaj, index) in izvestaji"
              :key="index"
            >
              <td class="text-center"><router-link :to="{path:`/izvestaj/${izvestaj.id}`}">{{izvestaj.about}}</router-link></td>
              <td class="text-center">{{izvestaj.datum_podnosenja}}</td>
              <td class="text-center">{{izvestaj.organ_vlasti.naziv}}</td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-1"
                  @click="getPdf(izvestaj.id)"
                >
                  <div v-if="izvestajPdfLoadingId==izvestaj.id" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="clippy"/>
                  <span>Preuzmi PDF</span>
                </button>
              </td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-1"
                  @click="getHtml(izvestaj.id)"
                >
                  <div v-if="izvestajHtmlLoadingId==izvestaj.id" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="code"/>
                  <span>Preuzmi HTML</span>
                </button>
              </td>
            </tr>
          </tbody>
          <tbody v-else>
            <tr>
              <td colspan="5">
                <div class="spinner-border" role="status" :style="{width: '2rem', height: '2rem', 'font-size': '10px', left: '50%', position: 'relative'}">
                  <span class="sr-only">Loading...</span>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

    </div>
  </div>
</template>

<script>
/*eslint no-undef: "warn"*/
/*eslint no-unused-vars: "warn"*/
const { Octicon, check, x, clippy, code } = require('octicons-vue');
import izvestajApi from '../../../api/izvestaj';
import { constructKolekcijaIzvestaja } from '../../../util';
export default {
  name: 'Izvestaji',
  components: {
    Octicon
  },
  data: () => {
    return {
      check,
      x,
      clippy,
      code,


      izvestaji: [],
      izvestajiLoading: false,
      izvestajPdfLoadingId: null,
      izvestajHtmlLoadingId: null,
      loading: false,
    };
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      this.izvestajiLoading = true;
      this.izvestaji = constructKolekcijaIzvestaja((await izvestajApi.getAll()).data);
      this.izvestajiLoading = false;
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
    },
    async getPdf(idZahteva) {
      this.izvestajPdfLoadingId = idZahteva;
      const response = await izvestajApi.getPdf(idZahteva);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `izvestaj-${idZahteva}.pdf`);
      document.body.appendChild(link);
      link.click();
      this.izvestajPdfLoadingId = null;
    },
    async getHtml(idZahteva) {
      this.izvestajHtmlLoadingId = idZahteva;
      const response = await izvestajApi.getHtml(idZahteva);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `izvestaj-${idZahteva}.html`);
      document.body.appendChild(link);
      link.click();
      this.zahtevHtmlLoadingId = null;
    }
  }
}
</script>

<style>

</style>
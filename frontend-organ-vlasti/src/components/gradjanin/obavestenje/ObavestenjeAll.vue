<template>
  <div class="container-fluid mt-4">
    <div class="row m-5">
      <div class="col-12">
        <h3 class="text-center">Obavestenja organa vlasti</h3>
        <table class="table table-sm table-bordered my-4">
          <thead>
            <tr>
              <th class="text-center">Obavestenje</th>
              <th class="text-center">Datum</th>
              <th class="text-center">Organ Vlasti</th>
              <th class="text-center">Trazilac</th>
              <th class="text-center" :style="{width: '8%'}">PDF obavestenja</th>
              <th class="text-center" :style="{width: '9%'}">HTML obavestenja</th>
            </tr>
          </thead>
          <tbody v-if="!obavestenjaLoading">
            <tr
              v-for="(obavestenje, index) in obavestenja"
              :key="index"
            >
              <td class="text-center"><a :href="obavestenje.about">{{obavestenje.about}}</a></td>
              <td class="text-center">{{obavestenje.predmet.datum}}</td>
              <td class="text-center">{{obavestenje.organ.naziv}}</td>
              <td class="text-center">{{obavestenje.trazilac.imePrezime}}</td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-2"
                  @click="getPdf(obavestenje.id)"
                >
                  <div v-if="idObavestenjaSelected==obavestenje.id && pdfLoading" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="clippy"/>
                  <span>Preuzmi PDF</span>
                </button>
              </td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-2"
                  @click="getHtml(obavestenje.id)"
                >
                  <div v-if="idObavestenjaSelected==obavestenje.id && htmlLoading" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="code" class="pt-1"/>
                  <span>Preuzmi HTML</span>
                </button>
              </td>
            </tr>
          </tbody>
          <tbody v-else>
            <tr>
              <td colspan="9">
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
const { Octicon, clippy, code } = require('octicons-vue');
import obavestenjeApi from '../../../api/obavestenje';
import { constructKolekcijaObavestenja } from '../../../util';
export default {
  name: 'ObavestenjeAll',
  components: {
    Octicon
  },
  data: () => {
    return {
      clippy,
      code,
      obavestenja: [],
      obavestenjaLoading: false,
      idObavestenjaSelected: null,
      pdfLoading: false,
      htmlLoading: false,
    };
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      this.zahteviLoading = true;
      this.obavestenja = constructKolekcijaObavestenja((await obavestenjeApi.getByUlogovaniTrazilac()).data);
      this.obavestenja = this.obavestenja.filter(x => !x.odbijen && !x.istekao);
      this.zahteviLoading = false;
    }
  },
  methods: {
    async getPdf(idObavestenja) {
      this.idObavestenjaSelected = idObavestenja;
      this.pdfLoading = true;
      const response = await obavestenjeApi.getPdf(idObavestenja);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `obavestenje-${idObavestenja}.pdf`);
      document.body.appendChild(link);
      link.click();
      this.pdfLoading = false;
      this.idObavestenjaSelected = null;
    },
    async getHtml(idObavestenja) {
      this.idObavestenjaSelected = idObavestenja;
      this.htmlLoading = true;
      const response = await obavestenjeApi.getHtml(idObavestenja);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `obavestenje-${idObavestenja}.html`);
      document.body.appendChild(link);
      link.click();
      this.htmlLoading = false;
      this.idObavestenjaSelected = null;
    },
  }
}
</script>

<style>

</style>
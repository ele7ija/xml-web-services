<template>
  <div class="container">
    <div class="row">
      <div class="col-12">
        <h3 class="text-center mt-5 mb-4">Neobradjene zalbe na odluku</h3>
        <table class="table table-sm table-bordered">
          <thead>
            <th class="text-center">Zalba</th>
            <th class="text-center">Status</th>
            <th class="text-center">Organ Vlasti</th>
            <th class="text-center">Datum</th>
            <th class="text-center" :style="{width: '12%'}">PDF</th>
            <th class="text-center" :style="{width: '14%'}">HTML</th>
            <th class="text-center" :style="{width: '10%'}">Obradi</th>
          </thead>
          <tbody v-if="!zalbeLoading">
            <tr
              v-for="(zalba, index) in zalbe"
              :key="index"
            >
              <td class="text-center"><a :href="zalba.about">{{zalba.about}}</a></td>
              <td class="text-center">{{zalba.status}}</td>
              <td class="text-center">{{zalba.organVlasti.naziv}}</td>
              <td class="text-center">{{zalba.datum}}</td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-1"
                  @click="getPdf(zalba.id)"
                >
                  <div v-if="pdfZalbeLoading==zalba.id" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="clippy"/>
                  <span class="pl-2">Preuzmi PDF</span>
                </button>
              </td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-1"
                  @click="getHtml(zalba.id)"
                >
                  <div v-if="htmlZalbeLoading==zalba.id" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="code"/>
                  <span class="pl-2">Preuzmi HTML</span>
                </button>
              </td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-1"
                  @click="obradi(zalba.id)"
                >
                  <div v-if="htmlZalbeLoading==zalba.id" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="check"/>
                  <span class="pl-2">Obradi</span>
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
const { Octicon, clippy, code, x, check } = require('octicons-vue');
import zalbaNaOdlukuApi from '../../../api/zalba_na_odluku';
import { constructKolekcijaZalbi } from '../../../util';
export default {
  name: 'ZalbeNaOdlukuPoverenik',
  components: {
    Octicon
  },
  data: () => {
    return {
      clippy,
      code,
      x,
      check,
      zalbe: [],
      zalbeLoading: false,
      pdfZalbeLoading: null,
      htmlZalbeLoading: null
    };
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      this.zalbeLoading = true;
      this.zalbe = constructKolekcijaZalbi((await zalbaNaOdlukuApi.getAllNeobradjeneZalbeNaOdluku()).data);
      this.zalbeLoading = false;
    }
  },
  methods: {
    async getPdf(idZalbe) {
      this.pdfZalbeLoading = idZalbe;
      const response = await zalbaNaOdlukuApi.getPdf(idZalbe);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zalba-${idZalbe}.pdf`);
      document.body.appendChild(link);
      link.click();
      this.pdfZalbeLoading = null;
    },
    async getHtml(idZalbe) {
      this.htmlZalbeLoading = idZalbe;
      const response = await zalbaNaOdlukuApi.getHtml(idZalbe);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zalba-${idZalbe}.html`);
      document.body.appendChild(link);
      link.click();
      this.htmlZalbeLoading = null;
    },
    obradi(idZalbe) {
      this.$router.push({path: `resenje-zalba-na-odluku-create/${idZalbe}`});
    }
  }
}
</script>

<style>

</style>
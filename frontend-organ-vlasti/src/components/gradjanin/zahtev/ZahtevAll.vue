<template>
  <div class="container fluid mt-4">
    <div class="row mx-5">
      <div class="col-12">
        <h3 class="text-center my-3">Istorija zahteva</h3>
        <table class="table table-sm table-bordered mt-3">
          <thead>
            <tr>
              <th class="text-center">ID</th>
              <th class="text-center">Datum</th>
              <th class="text-center">Organ Vlasti</th>
              <th class="text-center" :style="{width: '14%'}">PDF</th>
              <th class="text-center" :style="{width: '15%'}">HTML</th>
            </tr>
          </thead>
          <tbody v-if="!zahteviLoading">
            <tr
              v-for="(zahtev, index) in zahtevi"
              :key="index"
            >
              <td class="text-center"><router-link :to="{path:`/zahtev/${zahtev.id}`}">{{zahtev.about}}</router-link></td>
              <td class="text-center">{{zahtev.datum}}</td>
              <td class="text-center">{{zahtev.organ.naziv}}</td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-1"
                  @click="getPdf(zahtev.id)"
                >
                  <div v-if="zahtevPdfLoadingId==zahtev.id" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="clippy"/>
                  <span>Preuzmi PDF</span>
                </button>
              </td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-1"
                  @click="getHtml(zahtev.id)"
                >
                  <div v-if="zahtevHtmlLoadingId==zahtev.id" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
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
const { Octicon, clippy, code } = require('octicons-vue');
import zahtevApi from '../../../api/zahtev';
import { constructKolekcijaZahteva } from '../../../util';
export default {
  name: 'ZahtevAll',
  components: {
    Octicon
  },
  data: () => {
    return {
      clippy,
      code,
      zahtevi: [],
      zahteviLoading: false,
      zahtevPdfLoadingId: null,
      zahtevHtmlLoadingId: null,
    };
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      this.zahteviLoading = true;
      this.zahtevi = constructKolekcijaZahteva((await zahtevApi.getByGradjanin()).data);
      this.zahteviLoading = false;
    }
  },
  methods: {
    async getPdf(idZahteva) {
      this.zahtevPdfLoadingId = idZahteva;
      const response = await zahtevApi.getPdf(idZahteva);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zahtev-${idZahteva}.pdf`);
      document.body.appendChild(link);
      link.click();
      this.zahtevPdfLoadingId = null;
    },
    async getHtml(idZahteva) {
      this.zahtevHtmlLoadingId = idZahteva;
      const response = await zahtevApi.getHtml(idZahteva);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zahtev-${idZahteva}.html`);
      document.body.appendChild(link);
      link.click();
      this.zahtevHtmlLoadingId = null;
    }
  }
}
</script>

<style>
.octicon-clippy {
  margin-top: 1px;
  margin-right: 5px;
}
.octicon-code {
  margin-top: 1px;
  margin-right: 5px;
}
</style>
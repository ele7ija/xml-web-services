<template>
  <div class="container">
    <div class="row">
      <div class="col-12">
        <h3 class="mt-5 mb-4 text-center">Resenja</h3>
        <table class="talble table-sm table-bordered">
          <thead>
            <tr>
              <th>Resenje</th>
              <th>Zalilac</th>
              <th>Organ vlasti</th>
              <th>Poverenik</th>
              <th class="text-center" :style="{width: '12%'}">PDF</th>
              <th class="text-center" :style="{width: '14%'}">HTML</th>
            </tr>
          </thead>
          <tbody v-if="!resenjaLoading">
            <tr
              v-for="(resenje, index) in resenja"
              :key="index"
            >
              <td class="text-center">{{resenje.id}}</td>
              <td class="text-center">{{resenje.zalilac}}</td>
              <td class="text-center">{{resenje.organVlasti.naziv}}</td>
              <td class="text-center">{{resenje.poverenik.imePrezime}}</td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-1"
                  @click="getPdf(resenje.id)"
                >
                  <div v-if="pdfResenjeLoading==resenje.id" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="clippy"/>
                  <span class="pl-2">Preuzmi PDF</span>
                </button>
              </td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-1"
                  @click="getHtml(resenje.id)"
                >
                  <div v-if="htmlResenjeLoading==resenje.id" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="code"/>
                  <span class="pl-2">Preuzmi HTML</span>
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
import resenjeApi from '../../../api/resenje';
import { constructKolekcijaResenja } from '../../../util';
export default {
  name: "ResenjaAll",
  components: {
    Octicon
  },
  data: () => {
    return {
      clippy,
      code,
      resenja: [],
      resenjaLoading: false,
      pdfResenjeLoading: false,
      htmlResenjeLoading: false
    };
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      this.resenjaLoading = true;
      this.resenja = constructKolekcijaResenja((await resenjeApi.getAll()).data);
      this.resenjaLoading = false;
    }
  },
  methods: {
    async getPdf(idresenja) {
      this.pdfResenjeLoading = idresenja;
      const response = await resenjeApi.getPdf(idresenja);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `resenje-${idresenja}.pdf`);
      document.body.appendChild(link);
      link.click();
      this.pdfResenjeLoading = null;
    },
    async getHtml(idresenja) {
      this.htmlResenjeLoading = idresenja;
      const response = await resenjeApi.getHtml(idresenja);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `resenje-${idresenja}.html`);
      document.body.appendChild(link);
      link.click();
      this.htmlResenjeLoading = null;
    }
  }
}
</script>

<style>

</style>
<template>
  <div class="container">
    <div class="row">
      <div class="col-12">
        <h3 class="text-center mt-5 mb-4">Neobradjene zalbe na cutanje</h3>
        <table class="table table-sm table-bordered">
          <thead>
            <th class="text-center">Zalba</th>
            <th class="text-center">Organ Vlasti</th>
            <th class="text-center">Datum</th>
            <th class="text-center" :style="{width: '12%'}">PDF</th>
            <th class="text-center" :style="{width: '14%'}">HTML</th>
            <th class="text-center" :style="{width: '10%'}">Odbi</th>
            <th class="text-center" :style="{width: '10%'}">Prihvati</th>
          </thead>
          <tbody v-if="!zalbeLoading">
            <tr
              v-for="(zalba, index) in zalbe"
              :key="index"
            >
              <td class="text-center">{{zalba.id}}</td>
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
                  @click="odbi(zalba.id)"
                >
                  <div v-if="htmlZalbeLoading==zalba.id" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="x"/>
                  <span class="pl-2">Odbi</span>
                </button>
              </td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-1"
                  @click="prihvati(zalba.id)"
                >
                  <div v-if="htmlZalbeLoading==zalba.id" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="check"/>
                  <span class="pl-2">Prihvati</span>
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
  
    <div class="row">
      <div class="col-12">
        <h3 class="text-center mt-5 mb-4">Neobradjene zalbe na odluku</h3>
        <table class="table table-sm table-bordered">
          <thead>
            <th class="text-center">Zalba</th>
            <th class="text-center">Organ Vlasti</th>
            <th class="text-center">Datum</th>
            <th class="text-center" :style="{width: '12%'}">PDF</th>
            <th class="text-center" :style="{width: '14%'}">HTML</th>
            <th class="text-center" :style="{width: '10%'}">Odbi</th>
            <th class="text-center" :style="{width: '10%'}">Prihvati</th>
          </thead>
          <tbody v-if="!zalbeOdlukaLoading">
            <tr
              v-for="(zalba, index) in zalbeOdluka"
              :key="index"
            >
              <td class="text-center">{{zalba.id}}</td>
              <td class="text-center">{{zalba.organVlasti.naziv}}</td>
              <td class="text-center">{{zalba.datum}}</td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-1"
                  @click="getPdfOdluka(zalba.id)"
                >
                  <div v-if="pdfZalbeOdlukaLoading==zalba.id" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="clippy"/>
                  <span class="pl-2">Preuzmi PDF</span>
                </button>
              </td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-1"
                  @click="getHtmlOdluka(zalba.id)"
                >
                  <div v-if="htmlZalbeOdlukaLoading==zalba.id" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="code"/>
                  <span class="pl-2">Preuzmi HTML</span>
                </button>
              </td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-1"
                  @click="odbiOdluka(zalba.id)"
                >
                  <div v-if="htmlZalbeOdlukaLoading==zalba.id" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="x"/>
                  <span class="pl-2">Odbi</span>
                </button>
              </td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-1"
                  @click="prihvatiOdluka(zalba.id)"
                >
                  <div v-if="htmlZalbeOdlukaLoading==zalba.id" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="check"/>
                  <span class="pl-2">Prihvati</span>
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
import zalbaNaCutanjeApi from '../../api/zalba_na_cutanje';
import { constructKolekcijaZalbiNaCutanje } from '../../util';
import zalbaNaOdlukuApi from '../../api/zalba_na_odluku';
import { constructKolekcijaZalbi } from '../../util';
export default {
  name: 'ZalbeNaOrganVlasti',
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
      zalbeOdluka: [],
      zalbeOdlukaLoading: false,
      zalbeLoading: false,
      pdfZalbeLoading: null,
      htmlZalbeLoading: null,
      pdfZalbeOdlukaLoading: null,
      htmlZalbeOdlukaLoading: null
    };
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      this.zalbeLoading = true;
      this.zalbe = constructKolekcijaZalbiNaCutanje((await zalbaNaCutanjeApi.getAllNeobradjeneZalbeNaCutanje()).data);
      this.zalbeLoading = false;


      //Zalbe odluke
      this.zalbeOdlukaLoading = true;
      this.zalbeOdluka = constructKolekcijaZalbi((await zalbaNaOdlukuApi.getAllNeobradjeneZalbeNaOdluku()).data);
      this.zalbeOdlukaLoading = false;

    
    }
  },
  methods: {
    async getPdf(idZalbe) {
      this.pdfZalbeLoading = idZalbe;
      const response = await zalbaNaCutanjeApi.getPdf(idZalbe);
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
      const response = await zalbaNaCutanjeApi.getHtml(idZalbe);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zalba-${idZalbe}.html`);
      document.body.appendChild(link);
      link.click();
      this.htmlZalbeLoading = null;
    },
    async getPdfOdluka(idZalbe) {
      this.pdfZalbeOdlukaLoading = idZalbe;
      const response = await zalbaNaOdlukuApi.getPdf(idZalbe);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zalba-${idZalbe}.pdf`);
      document.body.appendChild(link);
      link.click();
      this.pdfZalbeOdlukaLoading = null;
    },
    async getHtmlOdluka(idZalbe) {
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
    async prihvati(idZalbe) {
      console.log("Prihvati zalbu " + idZalbe);
    
      const response = await zalbaNaCutanjeApi.accept(idZalbe);
      console.log(response.status);
      this.zalbeLoading = true;
      this.zalbe = constructKolekcijaZalbiNaCutanje((await zalbaNaCutanjeApi.getAllNeobradjeneZalbeNaCutanje()).data);
      this.zalbeLoading = false;
      
    },
    async odbi(idZalbe) {
      console.log("Odbi zalbu " + idZalbe);

      const response = await zalbaNaCutanjeApi.decline(idZalbe);
      console.log(response.status);
      this.zalbeLoading = true;
      this.zalbe = constructKolekcijaZalbiNaCutanje((await zalbaNaCutanjeApi.getAllNeobradjeneZalbeNaCutanje()).data);
      this.zalbeLoading = false;
    },
    async prihvatiOdluka(idZalbe) {
      console.log("Prihvati zalbu Odluka " + idZalbe);
      
      const response = await zalbaNaOdlukuApi.accept(idZalbe);
      console.log(response.status);
      this.zalbeOdlukaLoading = true;
      this.zalbeOdluka = constructKolekcijaZalbi((await zalbaNaOdlukuApi.getAllNeobradjeneZalbeNaOdluku()).data);
      this.zalbeOdlukaLoading = false;
    },
    async odbiOdluka(idZalbe) {
      console.log("Odbi zalbu Odluka " + idZalbe);
      
      const response = await zalbaNaOdlukuApi.decline(idZalbe);
      console.log(response.status);
      this.zalbeOdlukaLoading = true;
      this.zalbeOdluka = constructKolekcijaZalbi((await zalbaNaOdlukuApi.getAllNeobradjeneZalbeNaOdluku()).data);
      this.zalbeOdlukaLoading = false;
    }
  }
}
</script>

<style>

</style>
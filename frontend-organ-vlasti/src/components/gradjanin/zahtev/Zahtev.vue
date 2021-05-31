<template>
  <div class="wrapper">
    <div class="container px-5">
      <div class="row">
        <div class="col-4 my-4" v-if="zahtev">
          <button
            class="btn btn-sm btn-light mx-2"
            @click="getPdf"
          >
            <div v-if="pdfLoading" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
              <span class="sr-only">Loading...</span>
            </div>
            <Octicon v-else :icon="clippy"/>
            <span class="text-dark">Preuzmi PDF</span>
          </button>
          <button
              class="btn btn-sm btn-light ml-1 mx-2"
              @click="getHtml"
            >
              <div v-if="htmlLoading" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                <span class="sr-only">Loading...</span>
              </div>
              <Octicon v-else :icon="code"/>
              <span class="text-dark">Preuzmi HTML</span>
            </button>
        </div>
        <div class="col-8 my-4 pl-5" v-if="zahtev">
            <button
              class="btn btn-sm btn-light mx-2"
              @click="getJsonMetadata"
            >
              <div v-if="jsonMetadataLoading" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                <span class="sr-only">Loading...</span>
              </div>
              <span class="text-dark">Preuzmi JSON metapodatke</span>
            </button>
            <button
              class="btn btn-sm btn-light ml-1 mx-2"
              @click="getXmlMetadata"
            >
              <div v-if="xmlMetadataLoading" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                <span class="sr-only">Loading...</span>
              </div>
              <span class="text-dark">Preuzmi XML metapodatke</span>
            </button>
            <button
              class="btn btn-sm btn-light ml-1 mx-2"
              @click="getRdfMetadata"
            >
              <div v-if="rdfMetadataLoading" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                <span class="sr-only">Loading...</span>
              </div>
              <span class="text-dark">Preuzmi RDF metapodatke</span>
            </button>
        </div>
      </div>
      <div class="row">
        <div class="col-12">
          <div :id="zahtevGradjanaViewIdWrapper"></div>
        </div>
      </div>
    </div>
    <div v-if="zahtevLoading" class="centered">
      <div  class="spinner-border spinner-border-sm" role="status" :style="{width: '10rem', height: '10rem'}">
        <span class="sr-only">Loading...</span>
      </div>
    </div>
    <div v-else-if="!zahtevLoading && !zahtev" :style="{height: '100%'}">
      <h1 class="text-center centered">Not found 404</h1>
    </div>
  </div>
</template>

<script>
/*eslint no-undef: "warn"*/
/*eslint no-unused-vars: "warn"*/
const { Octicon, clippy, code } = require('octicons-vue');
import zahtevApi from '../../../api/zahtev';
import { constructZahtev } from '../../../util';
import { zahtevXsl } from '../../../xsl-helper';
export default {
  name: "Zahtev",
  components: {
    Octicon
  },
  data: () => {
    return {
      clippy,
      code,
      zahtev: null,
      zahtevLoading: false,
      pdfLoading: false,
      htmlLoading: false,
      zahtevGradjanaViewIdWrapper: 'zahtevGradjanaViewIdWrapper',
      zahtevGradjanaViewId: 'zahtevGradjanaViewId',

      jsonMetadataLoading: false,
      xmlMetadataLoading: false,
      rdfMetadataLoading: false
    }
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      //fetch zahtev
      try {
        this.zahtevLoading = true;
        const xmlString = (await zahtevApi.getById(this.$route.params.id)).data;
        this.zahtev = constructZahtev(Xonomy.xml2js(xmlString));

        //xsl transformation and dom construction
        const xsltProcessor = new XSLTProcessor();
        const domParser = new DOMParser();
        const xmlSerializer = new XMLSerializer();
        
        xsltProcessor.reset();
        const stylesheetDocument = domParser.parseFromString(zahtevXsl, 'text/xml');
        xsltProcessor.importStylesheet(stylesheetDocument);
        const xmlDocument = domParser.parseFromString(xmlString, 'text/xml');
        const convertedDocument = xsltProcessor.transformToDocument(xmlDocument);

        const viewContainer = document.getElementById(this.zahtevGradjanaViewIdWrapper);
        let viewElement = document.getElementById(this.zahtevGradjanaViewId);
        if(!viewElement) {
          let page = document.createElement('div');
          page.id = this.zahtevGradjanaViewId;
          page.classList.add('page3');
          page.innerHTML = xmlSerializer.serializeToString(convertedDocument);
          viewContainer.appendChild(page);
        } else {
          viewElement.innerHTML = xmlSerializer.serializeToString(convertedDocument);
        }
      } catch (error) {
        console.log(error);
        this.zahtev = null;
      }
      this.zahtevLoading = false;
    }
      
  },
  methods: {
    async getPdf() {
      this.pdfLoading = true;
      const response = await zahtevApi.getPdf(this.zahtev.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zahtev-${this.zahtev.id}.pdf`);
      document.body.appendChild(link);
      link.click();
      this.pdfLoading = false;
    },
    async getHtml() {
      this.htmlLoading = true;
      const response = await zahtevApi.getHtml(this.zahtev.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zahtev-${this.zahtev.id}.html`);
      document.body.appendChild(link);
      link.click();
      this.htmlLoading = false;
    },
    async getJsonMetadata() {
      this.jsonMetadataLoading = true;
      const response = await zahtevApi.getJsonMetadata(this.zahtev.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zahtev-${this.zahtev.id}-metadata.json`);
      document.body.appendChild(link);
      link.click();
      this.jsonMetadataLoading = false;
    },
    async getXmlMetadata() {
      this.xmlMetadataLoading = true;
      const response = await zahtevApi.getXmlMetadata(this.zahtev.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zahtev-${this.zahtev.id}-metadata.xml`);
      document.body.appendChild(link);
      link.click();
      this.xmlMetadataLoading = false;
    },
    async getRdfMetadata() {
      this.rdfMetadataLoading = true;
      const response = await zahtevApi.getRdfMetadata(this.zahtev.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zahtev-${this.zahtev.id}-metadata.ttl`);
      document.body.appendChild(link);
      link.click();
      this.rdfMetadataLoading = false;
    }
  }

}
</script>

<style>
.wrapper{
  background-color: #696969;
  height: 100%;
}
.centered {
  position: fixed; /* or absolute */
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
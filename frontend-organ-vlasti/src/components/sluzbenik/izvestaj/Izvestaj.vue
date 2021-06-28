<template>
  <div class="wrapper">
    <div class="container px-5">
      <div class="row">
        <div class="col-4 my-4" v-if="izvestaj">
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
        <div class="col-8 my-4 pl-5" v-if="izvestaj">
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
          <div :id="izvestajGradjanaViewIdWrapper"></div>
        </div>
      </div>
    </div>
    <div v-if="izvestajLoading" class="centered">
      <div  class="spinner-border spinner-border-sm" role="status" :style="{width: '10rem', height: '10rem'}">
        <span class="sr-only">Loading...</span>
      </div>
    </div>
    <div v-else-if="!izvestajLoading && !izvestaj" :style="{height: '100%'}">
      <h1 class="text-center centered">Not found 404</h1>
    </div>
  </div>
</template>

<script>
/*eslint no-undef: "warn"*/
/*eslint no-unused-vars: "warn"*/
const { Octicon, clippy, code } = require('octicons-vue');
import izvestajApi from '../../../api/izvestaj';
import { constructIzvestaj } from '../../../util';
import { izvestajXSL } from '../../../xsl-helper/izvestaj';
export default {
  name: 'Izvestaj',
  components: {
    Octicon
  },
  data: () => {
    return {
      clippy,
      code,
      izvestaj: null,
      izvestajLoading: false,
      pdfLoading: false,
      htmlLoading: false,
      izvestajGradjanaViewIdWrapper: 'izvestajGradjanaViewIdWrapper',
      izvestajGradjanaViewId: 'izvestajGradjanaViewId',

      jsonMetadataLoading: false,
      xmlMetadataLoading: false,
      rdfMetadataLoading: false
    }
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      //fetch izvestaj
      try {
        this.izvestajLoading = true;
        const xmlString = (await izvestajApi.getById(this.$route.params.id)).data;
        this.izvestaj = constructIzvestaj(Xonomy.xml2js(xmlString));

        //xsl transformation and dom construction
        const xsltProcessor = new XSLTProcessor();
        const domParser = new DOMParser();
        const xmlSerializer = new XMLSerializer();
        
        xsltProcessor.reset();
        const stylesheetDocument = domParser.parseFromString(izvestajXSL, 'text/xml');
        xsltProcessor.importStylesheet(stylesheetDocument);
        const xmlDocument = domParser.parseFromString(xmlString, 'text/xml');
        const convertedDocument = xsltProcessor.transformToDocument(xmlDocument);

        const viewContainer = document.getElementById(this.izvestajGradjanaViewIdWrapper);
        let viewElement = document.getElementById(this.izvestajGradjanaViewId);
        if(!viewElement) {
          let page = document.createElement('div');
          page.id = this.izvestajGradjanaViewId;
          page.classList.add('page3');
          page.innerHTML = xmlSerializer.serializeToString(convertedDocument);
          viewContainer.appendChild(page);
        } else {
          viewElement.innerHTML = xmlSerializer.serializeToString(convertedDocument);
        }
      } catch (error) {
        console.log(error);
        this.izvestaj = null;
      }
      this.izvestajLoading = false;
    }
      
  },
  methods: {
    async getPdf() {
      this.pdfLoading = true;
      const response = await izvestajApi.getPdf(this.izvestaj.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `izvestaj-${this.izvestaj.id}.pdf`);
      document.body.appendChild(link);
      link.click();
      this.pdfLoading = false;
    },
    async getHtml() {
      this.htmlLoading = true;
      const response = await izvestajApi.getHtml(this.izvestaj.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `izvestaj-${this.izvestaj.id}.html`);
      document.body.appendChild(link);
      link.click();
      this.htmlLoading = false;
    },
    async getJsonMetadata() {
      this.jsonMetadataLoading = true;
      const response = await izvestajApi.getJsonMetadata(this.izvestaj.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `izvestaj-${this.izvestaj.id}-metadata.json`);
      document.body.appendChild(link);
      link.click();
      this.jsonMetadataLoading = false;
    },
    async getXmlMetadata() {
      this.xmlMetadataLoading = true;
      const response = await izvestajApi.getXmlMetadata(this.izvestaj.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `izvestaj-${this.izvestaj.id}-metadata.xml`);
      document.body.appendChild(link);
      link.click();
      this.xmlMetadataLoading = false;
    },
    async getRdfMetadata() {
      this.rdfMetadataLoading = true;
      const response = await izvestajApi.getRdfMetadata(this.izvestaj.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `izvestaj-${this.izvestaj.id}-metadata.ttl`);
      document.body.appendChild(link);
      link.click();
      this.rdfMetadataLoading = false;
    }
  }
}
</script>

<style>

</style>
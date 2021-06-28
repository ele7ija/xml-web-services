<template>
  <div class="wrapper">
    <div class="container px-5">
      <div class="row">
        <div class="col-4 my-4" v-if="zalbaNaOdluku">
          <button
            class="btn btn-sm btn-light mx-2"
            @click="getPdf"
          >
            <div v-if="pdfLoading" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
              <span class="sr-only">Loading...</span>
            </div>
            <Octicon v-else :icon="clippy"/>
            <span class="text-dark pl-2">Preuzmi PDF</span>
          </button>
          <button
              class="btn btn-sm btn-light ml-1 mx-2"
              @click="getHtml"
            >
              <div v-if="htmlLoading" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                <span class="sr-only">Loading...</span>
              </div>
              <Octicon v-else :icon="code"/>
              <span class="text-dark pl-2">Preuzmi HTML</span>
            </button>
        </div>
        <div class="col-8 my-4 pl-5" v-if="zalbaNaOdluku">
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
          <div :id="zalbaNaOdlukuViewIdWrapper"></div>
        </div>
      </div>
      <div class="row pb-4 pt-2" v-if="zalbaNaOdluku">
        <div class="col-12">
          <button
            class="btn btn-sm btn-light mx-2"
            @click="navigateToZahtev"
          >
            <Octicon :icon="mention"/>
            <span class="text-dark ml-2">Referencirani zahtev</span>
          </button>
        </div>
      </div>
    </div>
    <div v-if="zalbaNaOdlukuLoading" class="centered">
      <div  class="spinner-border spinner-border-sm" role="status" :style="{width: '10rem', height: '10rem'}">
        <span class="sr-only">Loading...</span>
      </div>
    </div>
    <div v-else-if="!zalbaNaOdlukuLoading && !zalbaNaOdluku" :style="{height: '100%'}">
      <h1 class="text-center centered">Not found 404</h1>
    </div>
  </div>
</template>

<script>
/*eslint no-undef: "warn"*/
/*eslint no-unused-vars: "warn"*/
const { Octicon, clippy, code, mention } = require('octicons-vue');
import zalbaNaOdlukuApi from '../../../api/zalba_na_odluku';
import { constructZalbaNaOdluku } from '../../../util';
import { zalbaNaOdlukuXsl } from '../../../xsl-helper';
export default {
  name: "ZalbaNaOdlukuView",
  components: {
    Octicon
  },
  data: () => {
    return {
      clippy,
      code,
      mention,
      zalbaNaOdluku: null,
      zalbaNaOdlukuLoading: false,
      pdfLoading: false,
      htmlLoading: false,
      zalbaNaOdlukuViewIdWrapper: 'zalbaNaOdlukuViewIdWrapper',
      zalbaNaOdlukuViewId: 'zalbaNaOdlukuViewId',

      jsonMetadataLoading: false,
      xmlMetadataLoading: false,
      rdfMetadataLoading: false
    }
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      //fetch zahtev
      try {
        this.zalbaNaOdlukuLoading = true;
        const xmlString = (await zalbaNaOdlukuApi.getById(this.$route.params.id)).data;
        this.zalbaNaOdluku = constructZalbaNaOdluku(Xonomy.xml2js(xmlString));
        const xsltProcessor = new XSLTProcessor();
        const domParser = new DOMParser();
        const xmlSerializer = new XMLSerializer();
        
        xsltProcessor.reset();
        const stylesheetDocument = domParser.parseFromString(zalbaNaOdlukuXsl, 'text/xml');
        xsltProcessor.importStylesheet(stylesheetDocument);
        const xmlDocument = domParser.parseFromString(xmlString, 'text/xml');
        const convertedDocument = xsltProcessor.transformToDocument(xmlDocument);

        const viewContainer = document.getElementById(this.zalbaNaOdlukuViewIdWrapper);
        console.log(viewContainer)
        let viewElement = document.getElementById(this.zalbaNaOdlukuViewId);
        if(!viewElement) {
          let page = document.createElement('div');
          page.id = this.zalbaNaOdlukuViewId;
          page.classList.add('page4');
          page.innerHTML = xmlSerializer.serializeToString(convertedDocument);
          viewContainer.appendChild(page);
        } else {
          viewElement.innerHTML = xmlSerializer.serializeToString(convertedDocument);
        }
      } catch(error) {
        this.zalbaNaOdluku = null;
        console.log(error);
      }
      this.zalbaNaOdlukuLoading = false;

      
    }
  },
  methods: {
    async getPdf() {
      this.pdfLoading = true;
      const response = await zalbaNaOdlukuApi.getPdf(this.zalbaNaOdluku.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zalba-${this.zalbaNaOdluku.id}.pdf`);
      document.body.appendChild(link);
      link.click();
      this.pdfLoading = false;
    },
    async getHtml() {
      this.htmlLoading = true;
      const response = await zalbaNaOdlukuApi.getHtml(this.zalbaNaOdluku.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zalba-${this.zalbaNaOdluku.id}.html`);
      document.body.appendChild(link);
      link.click();
      this.htmlLoading = false;
    },
    async getJsonMetadata() {
      this.jsonMetadataLoading = true;
      const response = await zalbaNaOdlukuApi.getJsonMetadata(this.zalbaNaOdluku.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zalba-${this.zalbaNaOdluku.id}-metadata.json`);
      document.body.appendChild(link);
      link.click();
      this.jsonMetadataLoading = false;
    },
    async getXmlMetadata() {
      this.xmlMetadataLoading = true;
      const response = await zalbaNaOdlukuApi.getXmlMetadata(this.zalbaNaOdluku.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zalba-${this.zalbaNaOdluku.id}-metadata.xml`);
      document.body.appendChild(link);
      link.click();
      this.xmlMetadataLoading = false;
    },
    async getRdfMetadata() {
      this.rdfMetadataLoading = true;
      const response = await zalbaNaOdlukuApi.getRdfMetadata(this.zalbaNaOdluku.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zalba-${this.zalbaNaOdluku.id}-metadata.ttl`);
      document.body.appendChild(link);
      link.click();
      this.rdfMetadataLoading = false;
    },
    navigateToZahtev() {
      window.location.href = this.zalbaNaOdluku.zahtev_url;
    }
  }

}
</script>

<style>
.wrapper{
  background-color: #696969 !important;
  height: 100%;
}
.centered {
  position: fixed; /* or absolute */
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.octicon-mention {
  margin-top: 3px;
}
</style>
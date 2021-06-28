<template>
  <div class="wrapper">
    <div class="container px-5">
      <div class="row">
        <div class="col-4 my-4" v-if="zalbaNaCutanje">
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
        <div class="col-8 my-4 pl-5" v-if="zalbaNaCutanje">
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
          <div :id="zalbaNaCutanjeViewIdWrapper"></div>
        </div>
      </div>
      <div class="row pb-4 pt-2" v-if="zalbaNaCutanje">
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
    <div v-if="zalbaNaCutanjeLoading" class="centered">
      <div  class="spinner-border spinner-border-sm" role="status" :style="{width: '10rem', height: '10rem'}">
        <span class="sr-only">Loading...</span>
      </div>
    </div>
    <div v-else-if="!zalbaNaCutanjeLoading && !zalbaNaCutanje" :style="{height: '100%'}">
      <h1 class="text-center centered">Not found 404</h1>
    </div>
  </div>
</template>

<script>
/*eslint no-undef: "warn"*/
/*eslint no-unused-vars: "warn"*/
const { Octicon, clippy, code, mention } = require('octicons-vue');
import zalbaNaCutanjeApi from '../../../api/zalba_na_cutanje';
import { constructZalbaNaCutanje } from '../../../util';
import { zalbaCutanjaXsl } from '../../../xsl-helper';
export default {
  name: "ZalbaNaCutanjeView",
  components: {
    Octicon
  },
  data: () => {
    return {
      clippy,
      code,
      mention,
      zalbaNaCutanje: null,
      zalbaNaCutanjeLoading: false,
      pdfLoading: false,
      htmlLoading: false,
      zalbaNaCutanjeViewIdWrapper: 'zalbaNaCutanjeViewIdWrapper',
      zalbaNaCutanjeViewId: 'zalbaNaCutanjeViewId',

      jsonMetadataLoading: false,
      xmlMetadataLoading: false,
      rdfMetadataLoading: false
    }
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      //fetch zahtev
      try {
        this.zalbaNaCutanjeLoading = true;
        const xmlString = (await zalbaNaCutanjeApi.getById(this.$route.params.id)).data;
        this.zalbaNaCutanje = constructZalbaNaCutanje(Xonomy.xml2js(xmlString));
        const xsltProcessor = new XSLTProcessor();
        const domParser = new DOMParser();
        const xmlSerializer = new XMLSerializer();
        
        xsltProcessor.reset();
        const stylesheetDocument = domParser.parseFromString(zalbaCutanjaXsl, 'text/xml');
        xsltProcessor.importStylesheet(stylesheetDocument);
        const xmlDocument = domParser.parseFromString(xmlString, 'text/xml');
        const convertedDocument = xsltProcessor.transformToDocument(xmlDocument);

        const viewContainer = document.getElementById(this.zalbaNaCutanjeViewIdWrapper);
        console.log(viewContainer)
        let viewElement = document.getElementById(this.zalbaNaCutanjeViewId);
        if(!viewElement) {
          let page = document.createElement('div');
          page.id = this.zalbaNaCutanjeViewId;
          page.classList.add('page5');
          page.innerHTML = xmlSerializer.serializeToString(convertedDocument);
          viewContainer.appendChild(page);
        } else {
          viewElement.innerHTML = xmlSerializer.serializeToString(convertedDocument);
        }
      } catch(error) {
        this.zalbaNaCutanje = null;
        console.log(error);
      }
      this.zalbaNaCutanjeLoading = false;

      
    }
  },
  methods: {
    async getPdf() {
      this.pdfLoading = true;
      const response = await zalbaNaCutanjeApi.getPdf(this.zalbaNaCutanje.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zalba-${this.zalbaNaCutanje.id}.pdf`);
      document.body.appendChild(link);
      link.click();
      this.pdfLoading = false;
    },
    async getHtml() {
      this.htmlLoading = true;
      const response = await zalbaNaCutanjeApi.getHtml(this.zalbaNaCutanje.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zalba-${this.zalbaNaCutanje.id}.html`);
      document.body.appendChild(link);
      link.click();
      this.htmlLoading = false;
    },
    async getJsonMetadata() {
      this.jsonMetadataLoading = true;
      const response = await zalbaNaCutanjeApi.getJsonMetadata(this.zalbaNaCutanje.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zalba-${this.zalbaNaCutanje.id}-metadata.json`);
      document.body.appendChild(link);
      link.click();
      this.jsonMetadataLoading = false;
    },
    async getXmlMetadata() {
      this.xmlMetadataLoading = true;
      const response = await zalbaNaCutanjeApi.getXmlMetadata(this.zalbaNaCutanje.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zalba-${this.zalbaNaCutanje.id}-metadata.xml`);
      document.body.appendChild(link);
      link.click();
      this.xmlMetadataLoading = false;
    },
    async getRdfMetadata() {
      this.rdfMetadataLoading = true;
      const response = await zalbaNaCutanjeApi.getRdfMetadata(this.zalbaNaCutanje.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zalba-${this.zalbaNaCutanje.id}-metadata.ttl`);
      document.body.appendChild(link);
      link.click();
      this.rdfMetadataLoading = false;
    },
    navigateToZahtev() {
      window.location.href = this.zalbaNaCutanje.zahtev_url;
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
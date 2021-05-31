<template>
  <div class="wrapper">
    <div class="container px-5">
      <div class="row">
        <div class="col-4 my-4" v-if="obavestenje">
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
        <div class="col-8 my-4 pl-5" v-if="obavestenje">
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
          <div :id="obavestenjeViewIdWrapper"></div>
        </div>
      </div>
      <div class="row pb-4 pt-2" v-if="obavestenje">
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
    <div v-if="obavestenjeLoading" class="centered">
      <div  class="spinner-border spinner-border-sm" role="status" :style="{width: '10rem', height: '10rem'}">
        <span class="sr-only">Loading...</span>
      </div>
    </div>
    <div v-else-if="!obavestenjeLoading && !obavestenje" :style="{height: '100%'}">
      <h1 class="text-center centered">Not found 404</h1>
    </div>
  </div>
</template>

<script>
/*eslint no-undef: "warn"*/
/*eslint no-unused-vars: "warn"*/
const { Octicon, clippy, code, mention } = require('octicons-vue');
import obavestenjeApi from '../../../api/obavestenje';
import { constructObavestenje } from '../../../util';
import { obavestenjeXsl } from '../../../xsl-helper';
export default {
  name: "Obavestenje",
  components: {
    Octicon
  },
  data: () => {
    return {
      clippy,
      code,
      mention,
      obavestenje: null,
      obavestenjeLoading: false,
      pdfLoading: false,
      htmlLoading: false,
      obavestenjeViewIdWrapper: 'obavestenjeViewIdWrapper',
      obavestenjeViewId: 'obavestenjeViewId',

      jsonMetadataLoading: false,
      xmlMetadataLoading: false,
      rdfMetadataLoading: false
    }
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      //fetch zahtev
      try {
        this.obavestenjeLoading = true;
        const xmlString = (await obavestenjeApi.getById(this.$route.params.id)).data;
        this.obavestenje = constructObavestenje(Xonomy.xml2js(xmlString));
        if(!this.obavestenje.odbijen && !this.obavestenje.istekao) {
          //xsl transformation and dom construction
          const xsltProcessor = new XSLTProcessor();
          const domParser = new DOMParser();
          const xmlSerializer = new XMLSerializer();
          
          xsltProcessor.reset();
          const stylesheetDocument = domParser.parseFromString(obavestenjeXsl, 'text/xml');
          xsltProcessor.importStylesheet(stylesheetDocument);
          const xmlDocument = domParser.parseFromString(xmlString, 'text/xml');
          const convertedDocument = xsltProcessor.transformToDocument(xmlDocument);

          const viewContainer = document.getElementById(this.obavestenjeViewIdWrapper);
          console.log(viewContainer)
          let viewElement = document.getElementById(this.obavestenjeViewId);
          if(!viewElement) {
            let page = document.createElement('div');
            page.id = this.obavestenjeViewId;
            page.classList.add('page');
            page.innerHTML = xmlSerializer.serializeToString(convertedDocument);
            viewContainer.appendChild(page);
          } else {
            viewElement.innerHTML = xmlSerializer.serializeToString(convertedDocument);
          }
        } else {
          this.obavestenje = null;
        }
      } catch(error) {
        this.obavestenje = null;
        console.log(error);
      }
      this.obavestenjeLoading = false;

      
    }
  },
  methods: {
    async getPdf() {
      this.pdfLoading = true;
      const response = await obavestenjeApi.getPdf(this.obavestenje.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `obavestenje-${this.obavestenje.id}.pdf`);
      document.body.appendChild(link);
      link.click();
      this.pdfLoading = false;
    },
    async getHtml() {
      this.htmlLoading = true;
      const response = await obavestenjeApi.getHtml(this.obavestenje.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `obavestenje-${this.obavestenje.id}.html`);
      document.body.appendChild(link);
      link.click();
      this.htmlLoading = false;
    },
    async getJsonMetadata() {
      this.jsonMetadataLoading = true;
      const response = await obavestenjeApi.getJsonMetadata(this.obavestenje.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `obavestenje-${this.obavestenje.id}-metadata.json`);
      document.body.appendChild(link);
      link.click();
      this.jsonMetadataLoading = false;
    },
    async getXmlMetadata() {
      this.xmlMetadataLoading = true;
      const response = await obavestenjeApi.getXmlMetadata(this.obavestenje.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `obavestenje-${this.obavestenje.id}-metadata.xml`);
      document.body.appendChild(link);
      link.click();
      this.xmlMetadataLoading = false;
    },
    async getRdfMetadata() {
      this.rdfMetadataLoading = true;
      const response = await obavestenjeApi.getRdfMetadata(this.obavestenje.id);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `obavestenje-${this.obavestenje.id}-metadata.ttl`);
      document.body.appendChild(link);
      link.click();
      this.rdfMetadataLoading = false;
    },
    navigateToZahtev() {
      window.location.href = this.obavestenje.zahtev_url;
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
<template>
  <div class="container-fluid my-4">
    <div class="row">
      <div class="col-6 px-3">
        <div :id="zalbaNaCutanjeEditorIdWrapper"></div>
      </div>
      <div class="col-6 px-3">
        <div :id="zalbaNaCutanjeViewIdWrapper"></div>
      </div>
    </div>
    <div v-if="zahtevLoading" class="centered3">
      <div  class="spinner-border spinner-border-sm" role="status" :style="{width: '10rem', height: '10rem'}">
        <span class="sr-only">Loading...</span>
      </div>
    </div>

    <div class="row">
      <div class="col-12 px-5">
        <button
          class="btn btn-outline-primary my-1"
          :style="{width: '100%'}"
          @click="submit"
          v-if="!zahtevLoading"
        >
          <div v-if="loadingSubmit" class="spinner-border mr-2" role="status" :style="{width: '1rem', height: '1rem', 'font-size': '8px'}">
            <span class="sr-only">Loading...</span>
          </div>
          <span>Posalji</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
/*eslint no-undef: "warn"*/
/*eslint no-unused-vars: "warn"*/
import zahtevApi from '../../../api/zahtev';
import obavestenjeApi from '../../../api/obavestenje';
import zalbaNaCutanjeApi from '../../../api/zalba_na_cutanje';
import { constructZahtev, constructObavestenje } from '../../../util';
import { zalbaCutanjaXsl } from '../../../xsl-helper';
export default {
  name: 'ZalbaNaCutanjeCreate',
  data: () => {
    return {
      zalbaNaCutanjeEditorIdWrapper: "zalbaNaCutanjeEditorIdWrapper",
      zalbaNaCutanjeEditorId: 'zalbaNaCutanjeEditorId',
      zalbaNaCutanjeViewIdWrapper: 'zalbaNaCutanjeViewIdWrapper',
      zalbaNaCutanjeViewId: 'zalbaNaCutanjeViewId',
      loadingSubmit: false,
      zahtev: null,
      zahtevLoading: false,
      zalbaNaCutanjeSpec: {
        onchange: function(){
          console.log("I been changed now!")
        },
        validate: function (jsElement) {
          //Validate the element
          let elementSpec = this.elements[jsElement.name];

          if (elementSpec.validate) elementSpec.validate(jsElement);

          //Cycle through the element's attributes
          for (let i = 0; i < jsElement.attributes.length; i++) {
            let jsAttribute = jsElement.attributes[i];
            let attributeSpec = elementSpec.attributes[jsAttribute.name];
            if (attributeSpec.validate) attributeSpec.validate(jsAttribute);
          }

          //Cycle through the element's children
          for (let i = 0; i < jsElement.children.length; i++) {
            let jsChild = jsElement.children[i];
            if (jsChild.type == "element") { //if element
              this.validate(jsChild); //recursion
            }
          }
        },
        elements: {
          'zc:organ_vlasti': {
            isInvisible: true,
            hasText: false
          },
          'zc:datum_zahteva': {
            isInvisible: true,
            hasText: false
          },
          'zc:razlog_zalbe': {
            hasText: true,
            asker: Xonomy.askPicklist,
            askerParameter: ["није поступио", "није поступио у целости"],
          },
          'zc:drugi_podaci_za_kontakt': {
            hasText: true
          },
          'zc:datum_zalbe': {
            isInvisible: true,
            hasText: false
          },
          'zc:odgovor_organa_vlasti': {
            isInvisible: true,
            hasText: false
          },
        }
      }
    };
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      this.zahtevLoading = true;
      this.obavestenje = constructObavestenje(Xonomy.xml2js((await obavestenjeApi.getById(this.$route.params.idObavestenja)).data));
      this.zahtev = constructZahtev(Xonomy.xml2js((await zahtevApi.getById(this.obavestenje.id_zahteva)).data));
      this.zahtevLoading = false;
      this.renderEditor();
    }
  },
  methods: {
    renderEditor() {
      const container = document.getElementById(this.zalbaNaCutanjeEditorIdWrapper);
      let page = document.createElement('div');
      page.id = this.zalbaNaCutanjeEditorId;
      page.classList.add('page2');
      container.appendChild(page);
      const xmlString = 
        `<?xml version="1.0" encoding="UTF-8"?>
        <zc:Zalba_cutanja 
            xmlns:zc="http://ftn.uns.ac.rs/tim5/model/zalba_cutanja"
            xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            id_zahteva="${this.zahtev.id}">
            <zc:poverenik>
                <util:Mesto>${process.env.VUE_APP_POVERENIK_ADRESA_MESTO}</util:Mesto>
                <util:Postanski_broj>${process.env.VUE_APP_POVERENIK_ADRESA_POSTANSKI_BROJ}</util:Postanski_broj>
                <util:Ulica>${process.env.VUE_APP_POVERENIK_ADRESA_ULICA}</util:Ulica>
                <util:Broj>${process.env.VUE_APP_POVERENIK_ADRESA_BROJ}</util:Broj>
            </zc:poverenik>
            <zc:zalilac>
                <zc:osnovni_podaci>
                    <util:Adresa>
                        <util:Mesto>${this.obavestenje.trazilac.adresa.mesto}</util:Mesto>
                        <util:Ulica>${this.obavestenje.trazilac.adresa.ulica}</util:Ulica>
                        <util:Broj>${this.obavestenje.trazilac.adresa.broj}</util:Broj>
                    </util:Adresa>
                    <util:Ime>${this.obavestenje.trazilac.ime}</util:Ime>
                    <util:Prezime>${this.obavestenje.trazilac.prezime}</util:Prezime>
                </zc:osnovni_podaci>
                <zc:drugi_podaci_za_kontakt></zc:drugi_podaci_za_kontakt>
            </zc:zalilac>
            <zc:organ_vlasti>
                <util:Adresa>
                    <util:Mesto>${this.obavestenje.organ.adresa.mesto}</util:Mesto>
                    <util:Ulica>${this.obavestenje.organ.adresa.ulica}</util:Ulica>
                    <util:Broj>${this.obavestenje.organ.adresa.broj}</util:Broj>
                </util:Adresa>
                <util:Naziv>${this.obavestenje.organ.naziv}</util:Naziv>
            </zc:organ_vlasti>
            <zc:razlog_zalbe>није поступио</zc:razlog_zalbe>
            <zc:datum_zahteva>
                <util:dan>${"---"+this.zahtev.datum.split('.')[0]}</util:dan>
                <util:mesec>${"--"+this.zahtev.datum.split('.')[1]}</util:mesec>
                <util:godina>${this.zahtev.datum.split('.')[2]}</util:godina>
            </zc:datum_zahteva>
            <zc:zahtevana_informacije>${this.obavestenje.predmet.opisZahteva}</zc:zahtevana_informacije>
            <zc:mesto_zalbe property="pred:mesto">${this.obavestenje.trazilac.adresa.mesto}</zc:mesto_zalbe>
            <zc:datum_zalbe>
                <util:dan>${"---"+new Date().getDate()}</util:dan>
                <util:mesec>${"--"+this.getCurrentMonth()}</util:mesec>
                <util:godina>${new Date().getFullYear()}</util:godina>
            </zc:datum_zalbe>
            <zc:odgovor_organa_vlasti>
                <zc:prihvatio>ne</zc:prihvatio>
                <zc:odbio>ne</zc:odbio>
            </zc:odgovor_organa_vlasti>
        </zc:Zalba_cutanja>`;
      Xonomy.render(xmlString, page, {
        validate: this.zalbaNaCutanjeSpec.validate,
        elements: this.zalbaNaCutanjeSpec.elements,
        onchange: () => { this.onEditorChange() }
      });
      this.onEditorChange();
    },
    onEditorChange() {
      const xsltProcessor = new XSLTProcessor();
      const domParser = new DOMParser();
      const xmlSerializer = new XMLSerializer();
      
      xsltProcessor.reset();
      const stylesheetDocument = domParser.parseFromString(zalbaCutanjaXsl, 'text/xml');
      xsltProcessor.importStylesheet(stylesheetDocument);
      const xmlDocument = domParser.parseFromString(Xonomy.harvest(), 'text/xml');
      const convertedDocument = xsltProcessor.transformToDocument(xmlDocument);

      const viewContainer = document.getElementById(this.zalbaNaCutanjeViewIdWrapper);
      let viewElement = document.getElementById(this.zalbaNaCutanjeViewId);
      if(!viewElement) {
        let page = document.createElement('div');
        page.id = this.zalbaNaCutanjeViewId;
        page.classList.add('page3');
        page.innerHTML = xmlSerializer.serializeToString(convertedDocument);
        viewContainer.appendChild(page);
      } else {
        viewElement.innerHTML = xmlSerializer.serializeToString(convertedDocument);
      }
    },
    getCurrentMonth() {
      const month = new Date().getMonth() + 1;
      if(month < 10) {
        return '0' + month;
      } else {
        return month;
      }
    },
    async submit() {
      this.loadingSubmit = true;
      await zalbaNaCutanjeApi.create(Xonomy.harvest());
      this.loadingSubmit = false;
      this.$router.push({path: '/'});
    }
  }
}
</script>

<style>
.centered3 {
  position: fixed; /* or absolute */
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
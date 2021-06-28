<template>
  <div class="container-fluid my-4">
    <div class="row">
      <div class="col-6 px-3">
        <div :id="resenjeEditorIdWrapper"></div>
      </div>
      <div class="col-6 px-3">
        <div :id="resenjeViewIdWrapper"></div>
      </div>
    </div>
    <div v-if="zalbaLoading" class="centered3">
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
          v-if="!zalbaLoading"
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
import resenjeApi from '../../../api/resenje';
import zalbaNaCutanjeApi from '../../../api/zalba_na_cutanje';
import poverenikApi from '../../../api/poverenik';
import { constructZalbaNaCutanje, constructPoverenik } from '../../../util';
import { resenjeXsl } from '../../../xsl-helper';
export default {
  name: 'ResenjeZalbaCutanjaCreate',
  data: () => {
    return {
      resenjeEditorIdWrapper: "resenjeEditorIdWrapper",
      resenjeEditorId: 'resenjeEditorId',
      resenjeViewIdWrapper: 'resenjeViewIdWrapper',
      resenjeViewId: 'resenjeViewId',
      loadingSubmit: false,
      zalba: null,
      poverenik: null,
      zalbaLoading: false,
      resenjeSpec: {
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
          're:datum_resenja': {
            isInvisible: true,
            hasText: false
          },
          're:broj_resenja': {
            hasText: true
          },
          're:odluka': {
            hasText: false
          },
          're:prihvaceno': {
            hasText: true
          },
          're:odbijena_zalba': {
            hasText: true
          },
          're:odbijen_zahtev': {
            hasText: true
          },
          're:obrazlozenje': {
            hasText: true
          },
          're:poverenik': {
            isInvisible: true,
            hasText: false
          },
        }
      }
    };
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      this.zalbaLoading = true;
      this.zalba = constructZalbaNaCutanje(Xonomy.xml2js((await zalbaNaCutanjeApi.getById(this.$route.params.idZalbe)).data));
      this.poverenik = constructPoverenik((await poverenikApi.getAuthenticatedPoverenik()).data);
      this.zalbaLoading = false;
      this.renderEditor();
    }
  },
  methods: {
    renderEditor() {
      const container = document.getElementById(this.resenjeEditorIdWrapper);
      let page = document.createElement('div');
      page.id = this.resenjeEditorId;
      page.classList.add('page2');
      container.appendChild(page);
      const xmlString = 
        `<?xml version="1.0" encoding="UTF-8"?>
        <re:Resenje 
            xmlns:re="http://ftn.uns.ac.rs/tim5/model/resenje"
            xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            id_zalbe="${this.zalba.id}"
            tip_zalbe="zalba_cutanja">
            <re:datum_resenja>
                <util:dan>${"---"+new Date().getDate()}</util:dan>
                <util:mesec>${"--"+this.getCurrentMonth()}</util:mesec>
                <util:godina>${new Date().getFullYear()}</util:godina>
            </re:datum_resenja>
            <re:broj_resenja></re:broj_resenja>
            <re:zalba>
                <re:Datum_podnosenja_zahteva>
                    <util:dan>${"---"+this.zalba.datumZahteva.split('.')[0]}</util:dan>
                    <util:mesec>${"--"+this.zalba.datumZahteva.split('.')[1]}</util:mesec>
                    <util:godina>${this.zalba.datumZahteva.split('.')[2]}</util:godina>
                </re:Datum_podnosenja_zahteva>
                <re:Tip_zalbe>zalba_cutanja</re:Tip_zalbe>
                <re:Podnosilac>${this.zalba.zalilac.ime + " " + this.zalba.zalilac.prezime}</re:Podnosilac>
                <re:Organ_vlasti>
                    <util:Adresa>
                        <util:Mesto>${this.zalba.organVlasti.adresa.mesto}</util:Mesto>
                        <util:Ulica>${this.zalba.organVlasti.adresa.ulica}</util:Ulica>
                        <util:Broj>${this.zalba.organVlasti.adresa.broj}</util:Broj>
                    </util:Adresa>
                    <util:Naziv>${this.zalba.organVlasti.naziv}</util:Naziv>
                </re:Organ_vlasti>
            </re:zalba>
            <re:odluka>
                <re:prihvaceno>true</re:prihvaceno>
                <re:odbijena_zalba>false</re:odbijena_zalba>
                <re:odbijen_zahtev>false</re:odbijen_zahtev>
            </re:odluka>
            <re:obrazlozenje></re:obrazlozenje>
            <re:poverenik>
                <util:Adresa>
                    <util:Mesto>${this.poverenik.podaci.adresa.mesto}</util:Mesto>
                    <util:Ulica>${this.poverenik.podaci.adresa.ulica}</util:Ulica>
                    <util:Broj>${this.poverenik.podaci.adresa.broj}</util:Broj>
                </util:Adresa>
                <util:Ime>${this.poverenik.podaci.ime}</util:Ime>
                <util:Prezime>${this.poverenik.podaci.prezime}</util:Prezime>
            </re:poverenik>
        </re:Resenje>`;
      Xonomy.render(xmlString, page, {
        validate: this.resenjeSpec.validate,
        elements: this.resenjeSpec.elements,
        onchange: () => { this.onEditorChange() }
      });
      this.onEditorChange();
    },
    onEditorChange() {
      const xsltProcessor = new XSLTProcessor();
      const domParser = new DOMParser();
      const xmlSerializer = new XMLSerializer();
      
      xsltProcessor.reset();
      const stylesheetDocument = domParser.parseFromString(resenjeXsl, 'text/xml');
      xsltProcessor.importStylesheet(stylesheetDocument);
      const xmlDocument = domParser.parseFromString(Xonomy.harvest(), 'text/xml');
      const convertedDocument = xsltProcessor.transformToDocument(xmlDocument);

      const viewContainer = document.getElementById(this.resenjeViewIdWrapper);
      let viewElement = document.getElementById(this.resenjeViewId);
      if(!viewElement) {
        let page = document.createElement('div');
        page.id = this.resenjeViewId;
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
      await resenjeApi.create(Xonomy.harvest());
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
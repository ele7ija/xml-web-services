<template>
  <div class="container-fluid my-4">
    <div class="row">
      <div class="col-6 px-5">
        <div :id="zalbaNaOdlukuEditorIdWrapper"></div>
      </div>
      <div class="col-6 px-5">
        <div :id="zalbaNaOdlukuViewIdWrapper"></div>
      </div>
    </div>
    <div v-if="gradjaninLoading" class="centered3">
      <div  class="spinner-border spinner-border-sm" role="status" :style="{width: '10rem', height: '10rem'}">
        <span class="sr-only">Loading...</span>
      </div>
    </div>
  </div>
</template>

<script>
/*eslint no-undef: "warn"*/
/*eslint no-unused-vars: "warn"*/
import { mapGetters } from 'vuex';
export default {
  name: 'ZalbaNaOdlukuCreate',
  data: () => {
    return {
      zalbaNaOdlukuEditorIdWrapper: "zalbaNaOdlukuEditorIdWrapper",
      zalbaNaOdlukuEditorId: 'zalbaNaOdlukuEditorId',
      zalbaNaOdlukuViewIdWrapper: 'zalbaNaOdlukuViewIdWrapper',
      zalbaNaOdlukuViewId: 'zalbaNaOdlukuViewId',
      loadingSubmit: false,
      zahtev: null,
      zahtevLoading: false,
      zalbaNaOdlukuSpec: {
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

        }
      }
    };
  },
  computed: {
    ...mapGetters({
      obavestenje: 'obavestenje/getOabranoObavestenje'
    })
  },
  mounted() {
    if (sessionStorage.getItem('access_token')) {
      this.renderEditor();
    }
  },
  methods: {
    renderEditor() {
      const container = document.getElementById(this.zalbaNaOdlukuEditorIdWrapper);
      let page = document.createElement('div');
      page.id = this.zalbaNaOdlukuEditorId;
      page.classList.add('page2');
      container.appendChild(page);

      const xmlString = 
        `<?xml version="1.0" encoding="UTF-8"?>
        <zo:Zalba_na_odluku
            xmlns:zo="http://ftn.uns.ac.rs/tim5/model/zalba_na_odluku"
            xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
            <zo:poverenik>
                <util:Mesto>${process.end.VUE_APP_POVERENIK_ADRESA_MESTO}</util:Mesto>
                <util:Postanski_broj>${process.end.VUE_APP_POVERENIK_ADRESA_POSTANSKI_BROJ}</util:Postanski_broj>
                <util:Ulica>${process.end.VUE_APP_POVERENIK_ADRESA_ULICA}</util:Ulica>
                <util:Broj>${process.end.VUE_APP_POVERENIK_ADRESA_BROJ}</util:Broj>
            </zo:poverenik>
            <zo:zalilac>
                <zo:osnovni_podaci>
                    <util:Adresa>
                        <util:Mesto>${this.obavestenje.trazilac.adresa.mesto}</util:Mesto>
                        <util:Ulica>${this.obavestenje.trazilac.adresa.ulica}</util:Ulica>
                        <util:Broj>${this.obavestenje.trazilac.adresa.broj}</util:Broj>
                    </util:Adresa>
                    <util:Ime>${this.obavestenje.trazilac.ime}</util:Ime>
                    <util:Prezime>${this.obavestenje.trazilac.prezime}</util:Prezime>
                </zo:osnovni_podaci>
                <zo:drugi_podaci_za_kontakt></zo:drugi_podaci_za_kontakt>
            </zo:zalilac>
            <zo:organ_vlasti>
                <util:Adresa>
                    <util:Mesto>${this.obavestenje.organ.adresa.mesto}</util:Mesto>
                    <util:Ulica>${this.obavestenje.organ.adresa.ulica}</util:Ulica>
                    <util:Broj>${this.obavestenje.organ.adresa.broj}</util:Broj>
                </util:Adresa>
                <util:Naziv>${this.obavestenje.organ.naziv}</util:Naziv>
            </zo:organ_vlasti>
            <zo:datum_zahteva>
                <util:dan>${"---"+this.obavestenje.predmet.}</util:dan>
                <util:mesec>${"---"+new Date().getDate()}</util:mesec>
                <util:godina>${"---"+new Date().getDate()}</util:godina>
            </zo:datum_zahteva>
            <zo:odluka>
                <zo:broj_odluke></zo:broj_odluke>
                <zo:godina></zo:godina>
            </zo:odluka>
            <zo:osnova_za_zaljenje></zo:osnova_za_zaljenje>
            <zo:mesto_zalbe property="pred:mesto"></zo:mesto_zalbe>
            <zo:datum_zalbe>
                <util:dan>${"---"+new Date().getDate()}</util:dan>
                <util:mesec>${"--"+this.getCurrentMonth()}</util:mesec>
                <util:godina>${new Date().getFullYear()}</util:godina>
            </zo:datum_zalbe>
            <zo:odgovor_organa_vlasti>
                <zo:prihvatio>ne</zo:prihvatio>
                <zo:odbio>ne</zo:odbio>
            </zo:odgovor_organa_vlasti>
        </zo:Zalba_na_odluku>`;
      Xonomy.render(xmlString, page, {
        validate: this.obavestenjeSpec.validate,
        elements: this.obavestenjeSpec.elements,
        onchange: () => { this.onEditorChange() }
      });
      this.onEditorChange();
    },
    onEditorChange() {
      console.log(Xonomy.harvest());
      const xsltProcessor = new XSLTProcessor();
      const domParser = new DOMParser();
      const xmlSerializer = new XMLSerializer();
      
      xsltProcessor.reset();
      const stylesheetDocument = domParser.parseFromString(obavestenjeXsl, 'text/xml');
      xsltProcessor.importStylesheet(stylesheetDocument);
      const xmlDocument = domParser.parseFromString(Xonomy.harvest(), 'text/xml');
      const convertedDocument = xsltProcessor.transformToDocument(xmlDocument);

      const viewContainer = document.getElementById(this.obavestenjeViewIdWrapper);
      let viewElement = document.getElementById(this.obavestenjeViewId);
      if(!viewElement) {
        let page = document.createElement('div');
        page.id = this.obavestenjeViewId;
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
      await obavestenjeApi.create(Xonomy.harvest());
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
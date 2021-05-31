<template>
  <div class="container-fluid my-4">
    <div class="row is-table-row">
      <div class="col-6 px-5">
        <div :id="obavestenjeEditorIdWrapper"></div>
      </div>
      <div class="col-6 px-5">
        <div :id="obavestenjeViewIdWrapper"></div>
      </div>
    </div>

    <div v-if="zahtevLoading" class="centered">
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
import { obavestenjeXsl } from '../../../xsl-helper/index';
import { constructZahtev } from '../../../util';
export default {
  name: 'ObavestenjeCreate',
  data: () => {
    return {
      obavestenjeEditorIdWrapper: "obavestenjeEditorIdWrapper",
      obavestenjeEditorId: 'obavestenjeEditorId',
      obavestenjeViewIdWrapper: 'obavestenjeViewIdWrapper',
      obavestenjeViewId: 'obavestenjeViewId',
      zahtev: null,
      zahtevLoading: true,
      loadingSubmit: false,
      obavestenjeSpec: {
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
          'ob:odbijen': {
            isInvisible: true,
            hasText: false
          },
          'ob:istekao': {
            isInvisible: true,
            hasText: false
          },
          'ob:Trazilac': {
            isInvisible: true,
            hasText: false
          },
          'ob:Organ': {
            isInvisible: true,
            hasText: false
          },
          'ob:Predmet': {
            hasText: false
          },
          'ob:Broj_Predmeta': {
            hasText: true,
            mustBeBefore: ["ob:Odgovor", "ob:Uplata"]
          },
          'ob:Datum': {
            isInvisible: true
          },
          'util:dan': {
            displayValue: function(jsElement) {
              return jsElement.getText().split("---")[1]
            }
          },
          'util:mesec': {
            displayValue: function(jsElement) {
              return jsElement.getText().split("--")[1]
            }
          },
          'ob:Opis_Trazene_Informacije': {
            isInvisible: true,
            hasText: false
          },
          'ob:Pravni_Osnov': {
            isInvisible: true,
            hasText: false
          },
          'ob:Odgovor': {
            hasText: false,
            mustBeBefore: ["ob:Uplata"],
            menu: [
              {
                caption: "Novi <util:Uvid/>",
                action: Xonomy.newElementChild,
                actionParameter: '<util:Uvid xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"></util:Uvid>',
                hideIf: function (jsElement) {
                  return jsElement.hasChildElement("util:Uvid");
                }
              },
              {
                caption: "Novi <util:Trosak/>",
                action: Xonomy.newElementChild,
                actionParameter: '<util:Trosak xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"></util:Trosak>',
                hideIf: function (jsElement) {
                  return jsElement.hasChildElement("util:Trosak");
                }
              },
            ]
          },
          "util:Odobren": {
            hasText: true,
            asker: Xonomy.askPicklist,
            askerParameter: [true, false],
            mustBeBefore: ["util:Element_Zahteva", "util:Uvid", "util:Trosak"],
            isInvisible: true
          },
          'util:Element_Zahteva': {
            isInvisible: true
          },
          'util:Uvid': {
            hasText: false,
            mustBeBefore: ["util:Trosak"],
            menu: [
              {
                caption: "Ukloni <util:Uvid>",
                action: Xonomy.deleteElement,
              },
            ]
          },
          'ob:Uplata': {
            hasText: false
          },
          "util:Iznos": {
            hasText: true,
            mustBeBefore: ["util:Poziv_Na_Broj", "util:PravniOsnov"]
          },
          "util:Pravni_Osnov": {
            isInvisible: true
          }
        }
      }
    };
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      this.zahtevLoading = true;
      this.zahtev = constructZahtev(Xonomy.xml2js((await zahtevApi.getById(this.$route.params.idZahteva)).data));
      this.zahtevLoading = false;
      this.renderEditor();
    }
  },
  methods: {
    renderEditor() {
      const container = document.getElementById(this.obavestenjeEditorIdWrapper);
      let page = document.createElement('div');
      page.id = this.obavestenjeEditorId;
      page.classList.add('page2');
      container.appendChild(page);

      const xmlString = 
        `<?xml version="1.0" encoding="UTF-8"?>
        <ob:Obavestenje
            xmlns:ob="http://ftn.uns.ac.rs/tim5/model/obavestenje"
            xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"
            xmlns:xs="http://www.w3.org/2001/XMLSchema-instance"
            id_zahteva="${this.zahtev.id}">
            <ob:odbijen>false</ob:odbijen>
            <ob:istekao>false</ob:istekao>
            <ob:Trazilac>
                <util:Adresa>
                    <util:Mesto>${this.zahtev.trazilac.adresa.mesto}</util:Mesto>
                    <util:Ulica>${this.zahtev.trazilac.adresa.ulica}</util:Ulica>
                    <util:Broj>${this.zahtev.trazilac.adresa.broj}</util:Broj>
                </util:Adresa>
                <util:Ime>${this.zahtev.trazilac.ime}</util:Ime>
                <util:Prezime>${this.zahtev.trazilac.prezime}</util:Prezime>
            </ob:Trazilac>
            <ob:Organ>
                <util:Adresa>
                    <util:Mesto>${this.zahtev.organ.adresa.mesto}</util:Mesto>
                    <util:Ulica>${this.zahtev.organ.adresa.ulica}</util:Ulica>
                    <util:Broj>${this.zahtev.organ.adresa.broj}</util:Broj>
                </util:Adresa>
                <util:Naziv>${this.zahtev.organ.naziv}</util:Naziv>
            </ob:Organ>
            <ob:Predmet>
                <ob:Broj_Predmeta></ob:Broj_Predmeta>
                <ob:Datum>
                    <util:dan>${"---"+new Date().getDate()}</util:dan>
                    <util:mesec>${"--"+this.getCurrentMonth()}</util:mesec>
                    <util:godina>${new Date().getFullYear()}</util:godina>
                </ob:Datum>
                <ob:Opis_Trazene_Informacije>${this.zahtev.opisZahteva}</ob:Opis_Trazene_Informacije>
                <ob:Pravni_Osnov>
                    <util:Naziv>Закона о слободном приступу информацијама од јавног значаја</util:Naziv>
                    <util:Clan>16</util:Clan>
                    <util:Strana>1</util:Strana>
                    <util:Dopune>
                        <util:Dopuna>
                            <util:Broj_dopune>16</util:Broj_dopune>
                            <util:Godina>2020</util:Godina>
                        </util:Dopuna>
                    </util:Dopune>
                </ob:Pravni_Osnov>
                ${
                  this.zahtev.elementiZahteva.map((x,i) => {
                    return `
                      <ob:Odgovor>
                        <util:Odobren>true</util:Odobren>
                        <util:Element_Zahteva>
                            <util:Tekst>${x.tekst}</util:Tekst>
                            ${x.metodDostave ? `<util:Metod_Dostave>${x.metodDostave}</util:Metod_Dostave>` : ""}
                        </util:Element_Zahteva>
                        ${
                          i == 0 ?
                          `<util:Uvid>
                            <util:Datum_Uvida>
                                <util:dan>${"---"+new Date().getDate()}</util:dan>
                                <util:mesec>${"--"+this.getCurrentMonth()}</util:mesec>
                                <util:godina>${new Date().getFullYear()}</util:godina>
                            </util:Datum_Uvida>
                            <util:Vreme_Uvida>
                                <util:Sat>15</util:Sat>
                                <util:Minut>30</util:Minut>
                            </util:Vreme_Uvida>
                            </util:Uvid>`
                            :
                            ''
                        }
                       </ob:Odgovor>
                    `;
                  })
                }
                <ob:Uplata>
                    <util:Racun>
                        <util:Broj_Racuna>840-742328843-30</util:Broj_Racuna>
                    </util:Racun>
                    <util:Iznos>0</util:Iznos>
                    <util:Poziv_Na_Broj>97</util:Poziv_Na_Broj>
                    <util:Pravni_Osnov>
                        <util:Naziv>Правилника о условима и начину вођења рачуна</util:Naziv>
                        <util:Dopune>
                            <util:Dopuna>
                                <util:Broj_dopune>20</util:Broj_dopune>
                                <util:Godina>2007</util:Godina>
                            </util:Dopuna>
                            <util:Dopuna>
                                <util:Broj_dopune>40</util:Broj_dopune>
                                <util:Godina>2010</util:Godina>
                            </util:Dopuna>
                        </util:Dopune>
                    </util:Pravni_Osnov>
                </ob:Uplata>
            </ob:Predmet>
        </ob:Obavestenje>`;
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
.centered {
  position: fixed; /* or absolute */
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
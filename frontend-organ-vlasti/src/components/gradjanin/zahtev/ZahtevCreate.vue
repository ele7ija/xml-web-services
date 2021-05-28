<template>
  <div class="container-fluid my-4">
    <div class="row is-table-row">
      <div class="col-6 px-5">
        <div :id="zahtevGradjanaEditorIdWrapper"></div>
      </div>
      <div class="col-6 px-5">
        <div :id="zahtevGradjanaViewIdWrapper"></div>
      </div>
    </div>

    <div v-if="gradjaninLoading" class="centered">
      <div  class="spinner-border spinner-border-sm" role="status" :style="{width: '10rem', height: '10rem'}">
        <span class="sr-only">Loading...</span>
      </div>
    </div>

    <button
      class="btn btn-outline-primary my-1"
      :style="{width: '100%'}"
      @click="submit"
      v-if="!gradjaninLoading"
    >
      <div v-if="loadingSubmit" class="spinner-border mr-2" role="status" :style="{width: '1rem', height: '1rem', 'font-size': '8px'}">
        <span class="sr-only">Loading...</span>
      </div>
      <span>Podnesi</span>
    </button>
  </div>
</template>

<script>
/*eslint no-undef: "warn"*/
/*eslint no-unused-vars: "warn"*/
import gradjaninApi from '../../../api/gradjanin';
import zahtevApi from '../../../api/zahtev';
import { zahtevXsl } from '../../../xsl-helper/index';
import { constructGradjanin } from '../../../util';
export default {
  name: 'ZahtevCreate',
  data: () => {
    return {
      zahtevGradjanaEditorIdWrapper: "zahtevGradjanaEditorIdWrapper",
      zahtevGradjanaEditorId: 'zahtevGradjanaEditorId',
      zahtevGradjanaViewIdWrapper: 'zahtevGradjanaViewIdWrapper',
      zahtevGradjanaViewId: 'zahtevGradjanaViewId',
      gradjanin: null,
      gradjaninLoading: true,
      loadingSubmit: false,
      zahtevSpec: {
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
          'za:Zahtev': {
            hasText: false
          },
          'za:trazilac': {
            hasText: false,
            mustBeBefore: ["za:organ", "za:pravni_osnov", "za:opis_zahteva", "za:elementi_zahteva", "za:datum", "za:mesto"]
          },
          'util:Ime': {
            mustBeBefore: ["util:Prezime"],
          },
          'util:Prezime': {},
          "za:organ": {
            isReadOnly: true,
            mustBeBefore: ["za:pravni_osnov", "za:opis_zahteva", "za:elementi_zahteva", "za:datum", "za:mesto"]
          },
          'util:Adresa': {
            hasText: false, 
            mustBeBefore: ["util:Ime", "util:Prezime"]
          },
          'util:Mesto': {
            mustBeBefore: ["util:Ulica", "util:Broj"]
          },
          'util:Ulica': {
            mustBeBefore: ["util:Broj"]
          },
          'util:Broj': {},
          'za:pravni_osnov': {
            hasText: false,
            mustBeBefore: ["za:opis_zahteva", "za:elementi_zahteva", "za:datum", "za:mesto"]
          },
          'util:Naziv': {
            isReadOnly: true,
            mustBeBefore: ["util:Clan", "util:Strana", 'util:Dopune']
          },
          'util:Clan': {
            isReadOnly: true,
            mustBeBefore: ["util:Strana", 'util:Dopune']
          },
          'util:Strana': {
            isReadOnly: true,
            mustBeBefore: ['util:Dopune']
          },
          'util:Dopune': {
            menu: [
              {
                caption: "Nova dopuna <util:Dopuna/>",
                action: Xonomy.newElementChild,
                actionParameter: '<util:Dopuna xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"/>'
              }
            ],
            hasText: false
          },
          'util:Dopuna': {
            menu: [
              {
                caption: "Ukloni <util:Dopuna>",
                action: Xonomy.deleteElement,
                hideIf: function (jsElement) {
                  return jsElement.parent().children.filter(x => x.name == 'util:Dopuna').length == 1;
                }
              },
              {
                caption: "Novi broj dopune <util:Broj_dopune/>",
                action: Xonomy.newElementChild,
                actionParameter: '<util:Broj_dopune xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"></util:Broj_dopune>',
                hideIf: function (jsElement) {
                  return jsElement.hasChildElement("util:Broj_dopune");
                }
              },
              {
                caption: "Nova godina <util:Godina/>",
                action: Xonomy.newElementChild,
                actionParameter: '<util:Godina xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"></util:Godina>',
                hideIf: function (jsElement) {
                  return jsElement.hasChildElement("util:Godina");
                }
              }
            ]
          },
          'util:Broj_dopune': {
            mustBeBefore: ['util:Godina'],
            hasText: true
          },
          'util:Godina': {
            hasText: true
          },
          'za:opis_zahteva': {
            hasText: true,
            mustBeBefore: ["za:elementi_zahteva", "za:datum", "za:mesto"]
          },
          'za:elementi_zahteva': {
            hasText: false,
            mustBeBefore: ["za:datum", "za:mesto"],
            menu: [
              {
                caption: "Novi element zahteva <za:Element_Zahteva/>",
                action: Xonomy.newElementChild,
                actionParameter: '<za:Element_Zahteva xmlns:za="http://ftn.uns.ac.rs/tim5/model/zahtev"></za:Element_Zahteva>',
                hideIf: function (jsElement) {
                  return jsElement.children.filter(x => x.name == 'za:Element_Zahteva').length == 4;
                }
              }
            ]
          },
          'za:Element_Zahteva': {
            hasText: false,
            menu: [
              {
                caption: "Ukloni <za:Element_Zahteva>",
                action: Xonomy.deleteElement,
                hideIf: function (jsElement) {
                  return jsElement.parent().children.filter(x => x.name == 'za:Element_Zahteva').length == 1;
                }
              },
              {
                caption: "Novi element <util:Tekst/>",
                action: Xonomy.newElementChild,
                actionParameter: '<util:Tekst xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"></util:Tekst>',
                hideIf: function (jsElement) {
                  return jsElement.hasChildElement("util:Tekst");
                }
              },
              {
                caption: "Novi element <util:Metod_Dostave/>",
                action: Xonomy.newElementChild,
                actionParameter: '<util:Metod_Dostave xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"></util:Metod_Dostave>',
                hideIf: function (jsElement) {
                  return jsElement.parent().children.filter(x => x.name == 'util:Metod_Dostave').length == 3;
                }
              }
            ]
          },
          'util:Tekst': {
            hasText: true,
            asker: Xonomy.askPicklist,
            askerParameter: ["Obavestenje o posedovanju", "Uvid u dokument", "Kopija dokumenta", "Dostavljanje kopije"],
          },
          'util:Metod_Dostave': {
            hasText: true,
            asker: Xonomy.askPicklist,
            askerParameter: ["posta", "elektronska posta", "faks", "bez dostave"],
          },
          'za:datum': {
            isReadOnly: true,
            hasText: false,
            mustBeBefore: ["za:mesto"]
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
          'za:mesto': {
            hasText: true,
          },

        }
      }
    };
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      this.gradjaninLoading = true;
      this.gradjanin = constructGradjanin((await gradjaninApi.getAuthenticatedGradjanin()).data);
      this.gradjaninLoading = false;
      this.renderEditor();
    }
  },
  methods: {
    renderEditor() {
      const container = document.getElementById(this.zahtevGradjanaEditorIdWrapper);
      let page = document.createElement('div');
      page.id = this.zahtevGradjanaEditorId;
      page.classList.add('page2');
      container.appendChild(page);

      const xmlString = 
        `<?xml version="1.0" encoding="UTF-8"?>
        <za:Zahtev
            xmlns:za="http://ftn.uns.ac.rs/tim5/model/zahtev"
            xmlns:util="http://ftn.uns.ac.rs/tim5/model/util"
            xmlns:xs="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:pred="http://ftn.uns.ac.rs/tim5/model/predicate">
            <za:trazilac>
                <util:Adresa>
                    <util:Mesto>${this.gradjanin.podaci.adresa.mesto}</util:Mesto>
                    <util:Ulica>${this.gradjanin.podaci.adresa.ulica}</util:Ulica>
                    <util:Broj>${this.gradjanin.podaci.adresa.broj}</util:Broj>
                </util:Adresa>
                <util:Ime>${this.gradjanin.podaci.ime}</util:Ime>
                <util:Prezime>${this.gradjanin.podaci.prezime}</util:Prezime>
            </za:trazilac>
            <za:organ>
                <util:Adresa>
                    <util:Mesto>${process.env.VUE_APP_PREDUZECE_ADRESA_MESTO}</util:Mesto>
                    <util:Ulica>${process.env.VUE_APP_PREDUZECE_ADRESA_ULICA}</util:Ulica>
                    <util:Broj>${process.env.VUE_APP_PREDUZECE_ADRESA_BROJ}</util:Broj>
                </util:Adresa>
                <util:Naziv>${process.env.VUE_APP_PREDUZECE_NAZIV}</util:Naziv>
            </za:organ>
            <za:pravni_osnov>
                <util:Naziv>${process.env.VUE_APP_PRAVNI_OSNOV_NAZIV}</util:Naziv>
                <util:Clan>${process.env.VUE_APP_PRAVNI_OSNOV_CLAN}</util:Clan>
                <util:Strana>${process.env.VUE_APP_PRAVNI_OSNOV_STRANA}</util:Strana>
                <util:Dopune>
                    <util:Dopuna>
                        <util:Broj_dopune>120</util:Broj_dopune>
                        <util:Godina>2004</util:Godina>
                    </util:Dopuna>
                </util:Dopune>
            </za:pravni_osnov>
            <za:opis_zahteva></za:opis_zahteva>
            <za:elementi_zahteva>
                <za:Element_Zahteva>
                    <util:Tekst>Obavestenje o posedovanju</util:Tekst>
                    <util:Metod_Dostave>bez dostave</util:Metod_Dostave>
                </za:Element_Zahteva>
            </za:elementi_zahteva>
            <za:datum>
                <util:dan>${"---"+new Date().getDate()}</util:dan>
                <util:mesec>${"--"+this.getCurrentMonth()}</util:mesec>
                <util:godina>${new Date().getFullYear()}</util:godina>
            </za:datum>
            <za:mesto>${process.env.VUE_APP_PREDUZECE_ADRESA_MESTO}</za:mesto>
        </za:Zahtev>`;
      Xonomy.render(xmlString, page, {
        validate: this.zahtevSpec.validate,
        elements: this.zahtevSpec.elements,
        onchange: () => { this.onEditorChange() }
      });
      this.onEditorChange();
    },
    onEditorChange() {
      const xsltProcessor = new XSLTProcessor();
      const domParser = new DOMParser();
      const xmlSerializer = new XMLSerializer();
      
      xsltProcessor.reset();
      const stylesheetDocument = domParser.parseFromString(zahtevXsl, 'text/xml');
      xsltProcessor.importStylesheet(stylesheetDocument);
      const xmlDocument = domParser.parseFromString(Xonomy.harvest(), 'text/xml');
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
    },
    getCurrentMonth() {
      const month = new Date().getMonth() + 1;
      if(month < 10) {
        return '0' + month;
      }
    },
    async submit() {
      this.loadingSubmit = true;
      console.log(Xonomy.harvest());
      await zahtevApi.create(Xonomy.harvest());
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
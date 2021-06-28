<template>
  <div class="container-fluid my-4">
    <div class="row is-table-row">
      <div class="col-6 px-4">
        <div id="izvestajEditorWrapper">
        </div>
      </div>
      <div class="col-6 px-4">
        <div id="izvestajViewerWrapper"></div>
      </div>
    </div>

    <button
      class="btn btn-outline-primary my-1"
      :style="{width: '100%'}"
      @click="submit"
    >
      <span>Podnesi</span>
    </button>
  </div>
</template>

<script>
/*eslint no-undef: "warn"*/
/*eslint no-unused-vars: "warn"*/
const { Octicon, check, x, clippy, code } = require('octicons-vue');
import izvestajApi from '../../../api/izvestaj';

import zahtevApi from '../../../api/zahtev';
import obavestenjeApi from '../../../api/obavestenje';
import zalbaNaCutanjeApi from '../../../api/zalba_cutanje';
import zalbaNaOdlukuApi from '../../../api/zalba_odluka';
import { izvestajXSL } from '../../../xsl-helper/izvestaj';
import { constructKolekcijaZahteva, odbijObavestenjeXml, constructObavestenje, constructKolekcijaZalbi, constructKolekcijaZalbiNaCutanje } from '../../../util';
export default {
  name: 'IzvestajCreate',
  data: () => {
    return {
      check,
      x,
      clippy,
      code,
      zahtevi: [],
      loading: false,

      idZahtevaSelected: null,
      odbijLoading: false,
      pdfLoading: false,
      htmlLoading: false,

      obradjeniZahtevi: [],
      obavestenja: [],
      idObavestenjaSelected: null,

      zalbe_cutanje: [],
      zalbe_odluka: [],

      izvestajEditorIdWrapper: "izvestajEditorWrapper",
      izvestajEditorId: 'izvestajEditor',
      izvestajViewIdWrapper: 'izvestajViewerWrapper',
      izvestajViewId: 'izvestajView',

      izvestajSpec: {
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
          'iz:Izvestaj': {
            hasText: false
          },
          'iz:statistika_zahteva': {
            isReadOnly: false,
            mustBeBefore: ['iz:statistika_zalbi', 'iz:organ_vlasti', 'iz:datum_podnosenja']
          },
          'iz:broj_prihvacenih': {
            mustBeBefore: ['iz:broj_odbijenih', 'iz:broj_isteklih']
          },
          'iz:broj_odbijenih': {
            mustBeBefore: ['iz:broj_isteklih']
          },
          'iz:broj_isteklih': {
          },
          'iz:statistika_zalbi': {
            isReadOnly: false,
            mustBeBefore: ['iz:organ_vlasti', 'iz:datum_podnosenja']
          },
          'iz:broj_zalbi_na_odluku': {
            mustBeBefore: ['zalbe_cutanja']
          },
          'iz:zalbe_cutanja': {
          },
          'iz:nije_postupio': {
            mustBeBefore: ['iz:nije_postupio_u_celosti']
          },
          'iz:nije_postupio_u_celosti': {

          },
          "iz:organ_vlasti": {
            isReadOnly: true,
            mustBeBefore: ["iz:datum_podnosenja"]
          },
          'util:Adresa': {
            hasText: false, 
            mustBeBefore: ["util:Naziv"]
          },
          'util:Mesto': {
            mustBeBefore: ["util:Ulica", "util:Broj"]
          },
          'util:Ulica': {
            mustBeBefore: ["util:Broj"]
          },
          'util:Broj': {},
          'util:Naziv': {},
          'iz:datum_podnosenja': {
            isReadOnly: true,
            hasText: false,
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

        }
      }
    };
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      this.loading = true;
      const tempZahtevi = constructKolekcijaZahteva((await zahtevApi.getByNazivOrganaVlasti()).data);
      const results = await Promise.all(
        tempZahtevi.map(zahtev => obavestenjeApi.getObavestenjeByIdZahteva(zahtev.id))
      );
      for(const index in results) {
        if (!results[index].data) {
          this.zahtevi.push(tempZahtevi[index]);
        } else {
          this.obradjeniZahtevi.push(tempZahtevi[index]);
        }
      }
      this.obavestenja = results.filter(x => x.data).map(x => constructObavestenje(Xonomy.xml2js(x.data)));
      this.loading = false;

      // get zalbe
      const zalbe_odluka = (await zalbaNaOdlukuApi.getAll()).data;
      this.zalbe_odluka = constructKolekcijaZalbi(zalbe_odluka);

      const zalbe_cutanje = (await zalbaNaCutanjeApi.getAll()).data;
      this.zalbe_cutanje = constructKolekcijaZalbiNaCutanje(zalbe_cutanje);
    }
    this.renderEditor();
  },
  methods: {
    getStatistikaZahteva() {
      let statistika = {
        brojPrihvacenih: 0,
        brojOdbijenih: 0,
        brojIsteklih: 0
      }
      for (let obavestenje of this.obavestenja) {
        if (obavestenje.odbijen) {
          statistika.brojOdbijenih += 1
        }
        else if (obavestenje.istekao) {
          statistika.brojIsteklih += 1
        }
        else {
          statistika.brojPrihvacenih += 1
        }
      }
      return statistika
    },
    getBrojZalbiNaOdluku() {
      return this.zalbe_odluka.length
    },
    getStatistikaZalbiNaCutanje() {
      let brNijePostupio = 0;
      let brNijePostupioUCelosti = 0;
      for (let zalba_cutanje of this.zalbe_cutanje) {
        if (zalba_cutanje.razlog_zalbe == 'није поступио') {
          brNijePostupio++;
        }
        else {
          brNijePostupioUCelosti++;
        }
      }
      return {
        brNijePostupio,
        brNijePostupioUCelosti
      }
    },
    renderEditor() {
      const container = document.getElementById(this.izvestajEditorIdWrapper);
      let page = document.createElement('div');
      page.id = this.izvestajEditorId;
      page.classList.add('page2');
      container.appendChild(page);
      const str = 
      `<?xml version="1.0" encoding="UTF-8"?>
      <iz:Izvestaj
              xmlns:iz="http://ftn.uns.ac.rs/tim5/model/izvestaj"
              xmlns:util="http://ftn.uns.ac.rs/tim5/model/util">
          <iz:statistika_zahteva>
              <iz:broj_prihvacenih>${this.getStatistikaZahteva().brojPrihvacenih}</iz:broj_prihvacenih>
              <iz:broj_odbijenih>${this.getStatistikaZahteva().brojOdbijenih}</iz:broj_odbijenih>
              <iz:broj_isteklih>${this.getStatistikaZahteva().brojIsteklih}</iz:broj_isteklih>
          </iz:statistika_zahteva>
          <iz:statistika_zalbi>
              <iz:broj_zalbi_na_odluku>${this.getBrojZalbiNaOdluku()}</iz:broj_zalbi_na_odluku>
              <iz:zalbe_cutanja>
                  <iz:nije_postupio>${this.getStatistikaZalbiNaCutanje().brNijePostupio}</iz:nije_postupio>
                  <iz:nije_postupio_u_celosti>${this.getStatistikaZalbiNaCutanje().brNijePostupioUCelosti}</iz:nije_postupio_u_celosti>
              </iz:zalbe_cutanja>
          </iz:statistika_zalbi>
          <iz:organ_vlasti>
              <util:Adresa>
                  <util:Mesto>${process.env.VUE_APP_PREDUZECE_ADRESA_MESTO}</util:Mesto>
                  <util:Ulica>${process.env.VUE_APP_PREDUZECE_ADRESA_ULICA}</util:Ulica>
                  <util:Broj>${process.env.VUE_APP_PREDUZECE_ADRESA_BROJ}</util:Broj>
              </util:Adresa>
              <util:Naziv>${process.env.VUE_APP_PREDUZECE_NAZIV}</util:Naziv>
          </iz:organ_vlasti>
          <iz:datum_podnosenja>
              <util:dan>${"---"+new Date().getDate()}</util:dan>
              <util:mesec>${"--"+this.getCurrentMonth()}</util:mesec>
              <util:godina>${new Date().getFullYear()}</util:godina>
          </iz:datum_podnosenja>
      </iz:Izvestaj>`
      Xonomy.render(str, page, {
        validate: this.izvestajSpec.validate,
        elements: this.izvestajSpec.elements,
        onchange: () => { this.onEditorChange() }
      });
      this.onEditorChange();
    },
    onEditorChange() {
      const xsltProcessor = new XSLTProcessor();
      const domParser = new DOMParser();
      const xmlSerializer = new XMLSerializer();
      
      xsltProcessor.reset();
      const stylesheetDocument = domParser.parseFromString(izvestajXSL, 'text/xml');

      xsltProcessor.importStylesheet(stylesheetDocument);

      const xmlDocument = domParser.parseFromString(Xonomy.harvest(), 'text/xml');
      const convertedDocument = xsltProcessor.transformToDocument(xmlDocument);

      const viewContainer = document.getElementById(this.izvestajViewIdWrapper);
      let viewElement = document.getElementById(this.izvestajViewId);
      if(!viewElement) {
        let page = document.createElement('div');
        page.id = this.izvestajViewId;
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
      await izvestajApi.create(Xonomy.harvest())
      this.$router.push({path: '/'});
      
    }
  }
}
</script>

<style>

</style>
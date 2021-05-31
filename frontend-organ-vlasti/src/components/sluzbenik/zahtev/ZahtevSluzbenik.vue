<template>
  <div class="container-fluid mt-4">
    <div class="row m-5">
      <div class="col-12">
        <h3 class="text-center">Neobradjeni zahtevi</h3>
        <table class="table table-sm table-bordered my-4">
          <thead>
            <tr>
              <th class="text-center">ID</th>
              <th class="text-center">Datum</th>
              <th class="text-center">Organ Vlasti</th>
              <th class="text-center">Trazioc</th>
              <th class="text-center" :style="{width: '6%'}">Prihvati</th>
              <th class="text-center" :style="{width: '6%'}">Odbij</th>
              <th class="text-center" :style="{width: '8%'}">PDF</th>
              <th class="text-center" :style="{width: '8%'}">HTML</th>
            </tr>
          </thead>
          <tbody v-if="!zahteviLoading">
            <tr
              v-for="(zahtev, index) in zahtevi"
              :key="index"
            >
              <td class="text-center"><a :href="zahtev.about">{{zahtev.about}}</a></td>
              <td class="text-center">{{zahtev.datum}}</td>
              <td class="text-center">{{zahtev.organ.naziv}}</td>
              <td class="text-center">{{zahtev.trazilac.imePrezime}}</td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-2"
                  @click="prihvati(zahtev.id)"
                >
                  <Octicon :icon="check"/>
                  <span>Prihvati</span>
                </button>
              </td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-2"
                  @click="odbij(zahtev.id)"
                >
                  <div v-if="idZahtevaSelected==zahtev.id && odbijLoading" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="x"/>
                  <span>Odbij</span>
                </button>
              </td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-2"
                  @click="getPdf(zahtev.id)"
                >
                  <div v-if="idZahtevaSelected==zahtev.id && pdfLoading" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="clippy"/>
                  <span>Preuzmi PDF</span>
                </button>
              </td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary"
                  @click="getHtml(zahtev.id)"
                >
                  <div v-if="idZahtevaSelected==zahtev.id && htmlLoading" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="code" class="pt-1"/>
                  <span>Preuzmi HTML</span>
                </button>
              </td>
            </tr>
          </tbody>
          <tbody v-else>
            <tr>
              <td colspan="8">
                <div class="spinner-border" role="status" :style="{width: '2rem', height: '2rem', 'font-size': '10px', left: '50%', position: 'relative'}">
                  <span class="sr-only">Loading...</span>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="row m-5">
      <div class="col-12">
        <h3 class="text-center">Obradjeni i istekli zahtevi</h3>
        <table class="table table-sm table-bordered my-4">
          <thead>
            <tr>
              <th class="text-center">Zahtev</th>
              <th class="text-center">Prihvacen</th>
              <th class="text-center">Obavestenje</th>
              <th class="text-center">Organ Vlasti</th>
              <th class="text-center">Trazilac</th>
              <th class="text-center" :style="{width: '8%'}">PDF zahteva</th>
              <th class="text-center" :style="{width: '8%'}">HTML zahteva</th>
              <th class="text-center" :style="{width: '8%'}">PDF obavestenja</th>
              <th class="text-center" :style="{width: '9%'}">HTML obavestenja</th>
            </tr>
          </thead>
          <tbody v-if="!zahteviLoading">
            <tr
              v-for="(zahtev, index) in obradjeniZahtevi"
              :key="index"
            >
              <td class="text-center"><a :href="zahtev.about">{{zahtev.about}}</a></td>
              <td class="text-center"><strong>{{!obavestenja[index].odbijen && !obavestenja[index].istekao ? 'DA' : 'NE'}}</strong></td>
              <td class="text-center"><a v-if="!obavestenja[index].odbijen && !obavestenja[index].istekao" :href="obavestenja[index].about">{{obavestenja[index].about}}</a></td>
              <td class="text-center">{{zahtev.organ.naziv}}</td>
              <td class="text-center">{{zahtev.trazilac.imePrezime}}</td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-2"
                  @click="getPdf(zahtev.id)"
                >
                  <div v-if="idZahtevaSelected==zahtev.id && pdfLoading" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="clippy"/>
                  <span>Preuzmi PDF</span>
                </button>
              </td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary"
                  @click="getHtml(zahtev.id)"
                >
                  <div v-if="idZahtevaSelected==zahtev.id && htmlLoading" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="code" class="pt-1"/>
                  <span>Preuzmi HTML</span>
                </button>
              </td>
              <td>
                <button
                  v-if="!obavestenja[index].odbijen && !obavestenja[index].istekao"
                  class="btn btn-sm btn-outline-primary ml-2"
                  @click="getPdfObavestenje(obavestenja[index].id)"
                >
                  <div v-if="idObavestenjaSelected==obavestenja[index].id && pdfLoading" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="clippy"/>
                  <span>Preuzmi PDF</span>
                </button>
              </td>
              <td>
                <button
                  v-if="!obavestenja[index].odbijen && !obavestenja[index].istekao"
                  class="btn btn-sm btn-outline-primary ml-2"
                  @click="getHtmlObavestenje(obavestenja[index].id)"
                >
                  <div v-if="idObavestenjaSelected==obavestenja[index].id && htmlLoading" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="code" class="pt-1"/>
                  <span>Preuzmi HTML</span>
                </button>
              </td>
            </tr>
          </tbody>
          <tbody v-else>
            <tr>
              <td colspan="9">
                <div class="spinner-border" role="status" :style="{width: '2rem', height: '2rem', 'font-size': '10px', left: '50%', position: 'relative'}">
                  <span class="sr-only">Loading...</span>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
/*eslint no-undef: "warn"*/
/*eslint no-unused-vars: "warn"*/
const { Octicon, check, x, clippy, code } = require('octicons-vue');
import zahtevApi from '../../../api/zahtev';
import obavestenjeApi from '../../../api/obavestenje';
import { constructKolekcijaZahteva, odbijObavestenjeXml, constructObavestenje } from '../../../util';
export default {
  name: 'ZahtevSluzbenik',
  components: {
    Octicon
  },
  data: () => {
    return {
      check,
      x,
      clippy,
      code,
      zahtevi: [],
      zahteviLoading: false,

      idZahtevaSelected: null,
      odbijLoading: false,
      pdfLoading: false,
      htmlLoading: false,

      obradjeniZahtevi: [],
      obavestenja: [],
      idObavestenjaSelected: null
    };
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      this.zahteviLoading = true;
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
      this.zahteviLoading = false;
    }
  },
  methods: {
    prihvati(idZahteva) {
      this.$router.push({path: `/obavestenje-create/${idZahteva}`});
    },
    async odbij(idZahteva) {
      const zahtev = this.zahtevi.find(x => x.id == idZahteva);
      const xmlString = odbijObavestenjeXml(zahtev);
      this.idZahtevaSelected = idZahteva;
      this.odbijLoading = true;
      await obavestenjeApi.create(xmlString);
      this.odbijLoading = false;
      this.$router.push({path: `/`});
    },
    async getPdf(idZahteva) {
      this.idZahtevaSelected = idZahteva;
      this.pdfLoading = true;
      const response = await zahtevApi.getPdf(idZahteva);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zahtev-${idZahteva}.pdf`);
      document.body.appendChild(link);
      link.click();
      this.pdfLoading = false;
      this.idZahtevaSelected = null;
    },
    async getHtml(idZahteva) {
      this.idZahtevaSelected = idZahteva;
      this.htmlLoading = true;
      const response = await zahtevApi.getHtml(idZahteva);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zahtev-${idZahteva}.html`);
      document.body.appendChild(link);
      link.click();
      this.htmlLoading = false;
      this.idZahtevaSelected = null;
    },
    async getPdfObavestenje(idObavestenja) {
      this.idObavestenjaSelected = idObavestenja;
      this.pdfLoading = true;
      const response = await obavestenjeApi.getPdf(idObavestenja);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `obavestenje-${idObavestenja}.pdf`);
      document.body.appendChild(link);
      link.click();
      this.pdfLoading = false;
      this.idObavestenjaSelected = null;
    },
    async getHtmlObavestenje(idObavestenja) {
      this.idObavestenjaSelected = idObavestenja;
      this.htmlLoading = true;
      const response = await obavestenjeApi.getHtml(idObavestenja);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zahtev-${idObavestenja}.html`);
      document.body.appendChild(link);
      link.click();
      this.htmlLoading = false;
      this.idObavestenjaSelected = null;
    }
  }
}
</script>

<style>
.octicon-clippy {
  margin-top: 1px;
  margin-right: 5px;
}
.octicon-code {
  margin-top: 1px;
  margin-right: 5px;
}
.octicon-check {
  margin-top: 1px;
  margin-right: 5px;
}
.octicon-x {
  margin-top: 1px;
  margin-right: 5px;
}
</style>
<template>
  <div class="container">
    <div class="row">
      <div class="col-12">
        <h3 class="text-center mt-5 mb-4">Osnov za zalbu na cutanje uprave</h3>
        <table class="table table-sm table-bordered">
          <thead>
            <th class="text-center">Zahtev</th>
            <th class="text-center">Istekao</th>
            <th class="text-center">Organ Vlasti</th>
            <th class="text-center">Datum</th>
            <th class="text-center">Akcija</th>
          </thead>
          <tbody v-if="!obavestenjaLoading">
            <tr
              v-for="(obavestenje, index) in obavestenja"
              :key="index"
            >
              <td class="text-center"><a :href="obavestenje.zahtev_url">{{obavestenje.zahtev_url}}</a></td>
              <td class="text-center">{{obavestenje.istekao}}</td>
              <td class="text-center">{{obavestenje.organ.naziv}}</td>
              <td class="text-center">{{obavestenje.predmet.datum}}</td>
              <td class="text-center" :style="{width: '13%'}">
                <button
                  class="btn btn-sm btn-outline-primary ml-2"
                  @click="navigate(obavestenje)"
                >
                  <Octicon :icon="clippy"/>
                  <span class="ml-1">Podnesi zalbu</span>
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

    <div class="row">
      <div class="col-12">
        <h3 class="text-center mt-5 mb-4">Podnete zalbe na cutanje</h3>
        <table class="table table-sm table-bordered">
          <thead>
            <th class="text-center">Zalba</th>
            <th class="text-center">Status</th>
            <th class="text-center">Organ Vlasti</th>
            <th class="text-center">Datum</th>
            <th class="text-center" :style="{width: '12%'}">PDF</th>
            <th class="text-center" :style="{width: '14%'}">HTML</th>
          </thead>
          <tbody v-if="!obavestenjaLoading">
            <tr
              v-for="(zalba, index) in zalbe"
              :key="index"
            >
              <td class="text-center"><a :href="zalba.about">{{zalba.about}}</a></td>
              <td class="text-center">{{zalba.status}}</td>
              <td class="text-center">{{zalba.organVlasti.naziv}}</td>
              <td class="text-center">{{zalba.datum}}</td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-1"
                  @click="getPdf(zalba.id)"
                >
                  <div v-if="pdfZalbeLoading==zalba.id" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="clippy"/>
                  <span class="pl-2">Preuzmi PDF</span>
                </button>
              </td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-1"
                  @click="getHtml(zalba.id)"
                >
                  <div v-if="htmlZalbeLoading==zalba.id" class="spinner-border mr-2 pb-1" role="status" :style="{width: '0.9rem', height: '0.9rem', 'font-size': '10px'}">
                    <span class="sr-only">Loading...</span>
                  </div>
                  <Octicon v-else :icon="code"/>
                  <span class="pl-2">Preuzmi HTML</span>
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
const { Octicon, clippy, code } = require('octicons-vue');
import obavestenjeApi from '../../../api/obavestenje';
import zalbaNaCutanjeApi from '../../../api/zalba_na_cutanje';
import { constructKolekcijaObavestenja, constructKolekcijaZalbiNaCutanje } from '../../../util';
export default {
  name: 'ZalbaNaCutanje',
  components: {
    Octicon
  },
  data: () => {
    return {
      clippy,
      code,
      obavestenja: [],
      obavestenjaLoading: false,
      zalbe: [],
      pdfZalbeLoading: null,
      htmlZalbeLoading: null
    };
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      this.obavestenjaLoading = true;
      this.obavestenja = constructKolekcijaObavestenja((await obavestenjeApi.getIstekla()).data);
      this.zalbe = constructKolekcijaZalbiNaCutanje((await zalbaNaCutanjeApi.getAllUlogovanogKorisnika()).data);
      this.obavestenjaLoading = false;
    }
  },
  methods: {
    navigate (obavestenje) {
      this.$router.push({path: `zalba-na-cutanje-create/${obavestenje.id}`});
    },
    async getPdf(idZalbe) {
      this.pdfZalbeLoading = idZalbe;
      const response = await zalbaNaCutanjeApi.getPdf(idZalbe);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zalba-${idZalbe}.pdf`);
      document.body.appendChild(link);
      link.click();
      this.pdfZalbeLoading = null;
    },
    async getHtml(idZalbe) {
      this.htmlZalbeLoading = idZalbe;
      const response = await zalbaNaCutanjeApi.getHtml(idZalbe);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zalba-${idZalbe}.html`);
      document.body.appendChild(link);
      link.click();
      this.htmlZalbeLoading = null;
    }
  }
}
</script>

<style>

</style>
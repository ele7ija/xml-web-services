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
              <th class="text-center" :style="{width: '5%'}">Odbi</th>
              <th class="text-center" :style="{width: '8%'}">PDF</th>
              <th class="text-center" :style="{width: '8%'}">HTML</th>
            </tr>
          </thead>
          <tbody v-if="!zahteviLoading">
            <tr
              v-for="(zahtev, index) in zahtevi"
              :key="index"
            >
              <td class="text-center"><router-link :to="{path:`/zahtev/${zahtev.id}`}">{{zahtev.about}}</router-link></td>
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
                  @click="odbi(zahtev.id)"
                >
                  <Octicon :icon="x"/>
                  <span>Odbi</span>
                </button>
              </td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary ml-2"
                  @click="getPdf(zahtev.id)"
                >
                  <Octicon :icon="clippy"/>
                  <span>Preuzmi PDF</span>
                </button>
              </td>
              <td>
                <button
                  class="btn btn-sm btn-outline-primary"
                  @click="getHtml(zahtev.id)"
                >
                  <Octicon :icon="code" class="pt-1"/>
                  <span>Preuzmi HTML</span>
                </button>
              </td>
            </tr>
          </tbody>
          <tbody v-else>
            <tr>
              <td colspan="5">
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
        <h3 class="text-center">Obradjeni i istekli zahtevi</h3>
      </div>
    </div>
  </div>
</template>

<script>
const { Octicon, check, x, clippy, code } = require('octicons-vue');
import zahtevApi from '../../../api/zahtev';
import { constructKolekcijaZahteva } from '../../../util';
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

    };
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      this.zahteviLoading = true;
      this.zahtevi = constructKolekcijaZahteva((await zahtevApi.getByNazivOrganaVlasti()).data);
      this.zahteviLoading = false;
    }
  },
  methods: {
    prihvati(idZahteva) {
      this.$router.push({path: `/obavestenje-create/${idZahteva}`});
    },
    odbi(idZahteva) {
      this.$router.push({path: `/zahtev/${idZahteva}`});
    },
    async getPdf(idZahteva) {
      const response = await zahtevApi.getPdf(idZahteva);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zahtev-${idZahteva}.pdf`);
      document.body.appendChild(link);
      link.click();
    },
    async getHtml(idZahteva) {
      const response = await zahtevApi.getHtml(idZahteva);
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(
          new Blob([response.data])
      );
      link.setAttribute('download', `zahtev-${idZahteva}.html`);
      document.body.appendChild(link);
      link.click();
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
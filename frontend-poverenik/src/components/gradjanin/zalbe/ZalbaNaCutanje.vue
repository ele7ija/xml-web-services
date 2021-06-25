<template>
  <div class="container">
    <div class="row">
      <div class="col-12">
        <h3 class="text-center mt-5 mb-4">Osnov za zalbu na cutanje uprave</h3>
        <table class="table table-sm table-bordered">
          <thead>
            <th class="text-center">Zahtev</th>
            <th class="text-center">Odbijen</th>
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
              <td class="text-center">{{obavestenje.odbijen}}</td>
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
  </div>
</template>

<script>
const { Octicon, clippy } = require('octicons-vue');
import obavestenjeApi from '../../../api/obavestenje';
import { constructKolekcijaObavestenja } from '../../../util';
export default {
  name: 'ZalbaNaCutanje',
  components: {
    Octicon
  },
  data: () => {
    return {
      clippy,
      obavestenja: [],
      obavestenjaLoading: false
    };
  },
  async mounted() {
    if (sessionStorage.getItem('access_token')) {
      this.obavestenjaLoading = true;
      this.obavestenja = constructKolekcijaObavestenja((await obavestenjeApi.getIstekla()).data);
      this.obavestenjaLoading = false;
    }
  },
  methods: {
    navigate (obavestenje) {
      this.$store.commit('obavestenje/setOdabranoObavestenje', obavestenje, {root: true});
      this.$router.push({path: 'zalba-na-cutanje/create'});
    }
  }
}
</script>

<style>

</style>
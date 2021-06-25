<template>
<div class='container-fluid'>
  <div v-if='zahtevi.length == 0 && resenja.length == 0 && izvestaji.length == 0' class="row m-5">
    <div class="col-12">
      <div class="alert alert-info" role="alert">
        Search for something.
      </div>
    </div>
  </div>
  <div class="row m-5">
    <div class="col-4">
      <div class="card">
        
        <div class="card-body">
        <div class="form-group">
          <label>Search Term</label>
          <input type="text" class="form-control form-control-sm" v-model="search.term">
          <small class="form-text text-muted">e.g. "zahtev"</small>
        </div>
        <div class="form-group">
          <label>Metadata</label>
          <input type="text" class="form-control form-control-sm" v-model="search.metadata">
          <small class="form-text text-muted">e.g. "22.6.2021*AND*НОВИ САД"<br>e.g. "22.6.2021*OR*25.6.2021"</small>
        </div>
        <div class="btn btn-primary" @click="doSearch()">Apply</div>
        </div>
      </div>
    </div>
    <div class="col-8">
      <h3 class="text-center">Zahtevi</h3>
      <table class="table table-sm table-bordered my-4">
        <thead>
          <tr>
            <th class="text-center">Zahtev</th>
            
          </tr>
        </thead>
        <tbody v-if="!loading">
          <tr
            v-for="(zahtev, index) in zahtevi"
            :key="index"
          >
            <td class="text-center"><a :href="zahtev.about">{{zahtev.about}}</a></td>
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
// const { Octicon, check, x, clippy, code } = require('octicons-vue');
import zahtevApi from '../../../api/zahtev';
import obavestenjeApi from '../../../api/obavestenje';
import pretragaApi from '../../../api/pretraga';
import { constructKolekcijaZahteva, constructRezultatPretrage, pretragaXML } from '../../../util';
export default {
  name: 'Pretrage',
  components: {
    // Octicon
  },
  data: () => {
    return {

      zahtevi: [],
      resenja: [],
      izvestaji: [],
      loading: false,

      search: {
        term: '',
        metadata: ''
      }
    };
  },
  async mounted() {
  
  },
  methods: {
    
    


    async doSearch() {
      this.loading = true;
      let pretragaxml = pretragaXML(this.search);
      console.log('pretragaxml: ' + pretragaxml);
      const rezultatPretrage = constructRezultatPretrage((await pretragaApi.post(pretragaxml)).data);
      console.log('rez: ' + rezultatPretrage.zahtevi.length)

      if (rezultatPretrage.zahtevi.length != 0) {
        this.zahtevi = rezultatPretrage.zahtevi;
      } else {
        this.zahtevi.splice(0);
      }
      this.resenja = rezultatPretrage.resenja.length != 0 ? rezultatPretrage.resenja : [];
      this.izvestaji = rezultatPretrage.izvestaji.length != 0 ? rezultatPretrage.izvestaji : [];
      this.loading = false;
    }
  }
}
</script>

<style>

</style>
<template>
<div class='container-fluid'>
  <div v-if='zalbe_cutanje.length != 0 || zalbe_odluka.length != 0 || resenja.length != 0 && izvestaji.length != 0' class="row ml-5 mr-5 mt-4">
    <div class="col-12">
      <div class="alert alert-success" role="alert">
        Prikazuje se {{zalbe_cutanje.length + zalbe_odluka.length + resenja.length + izvestaji.length}} rezultata.
      </div>
    </div>
  </div>
  <div class="row ml-5 mr-5 mt-4">
    <div class="col-4">
      <div class="card">
        
        <div class="card-body">
        <div class="form-group">
          <label>Termin</label>
          <input type="text" class="form-control form-control-sm" v-model="search.term">
          <small class="form-text text-muted">e.g. "resenje"</small>
        </div>
        <div class="form-group">
          <label>Metapodaci</label>
          <input type="text" class="form-control form-control-sm" v-model="search.metadata">
          <small class="form-text text-muted">e.g. "22.6.2021*AND*НОВИ САД"<br>e.g. "22.6.2021*OR*25.6.2021"</small>
        </div>
        <div class="btn btn-primary" @click="doSearch()">Apply</div>
        </div>
      </div>
    </div>
    <div class="col-8">
      <h3 class="text-center">Zalbe na odluku</h3>
      <table class="table table-sm table-bordered my-4">
        <thead>
          <tr>
            <th class="text-center">URL zalbe</th>
            <th class="text-center">Akcije</th>
          </tr>
        </thead>
        <tbody v-if="!loading">
          <tr
            v-for="(zalba, index) in zalbe_odluka"
            :key="index"
          >
            <td class="text-center"><a :href="zalba.about">{{zalba.about}}</a></td>
            <td class="text-center">
              <a class="btn btn-primary" data-toggle="collapse" :href="'#collapsezalbeodluka'+getId(zalba.about)" role="button" aria-expanded="false" :aria-controls="'collapsezalbeodluka'+getId(zalba.about)"
                @click="getReferencedBy(zalba.about)">
                Referenced By
              </a>
              <div class="collapse mt-1" :id="'collapsezalbeodluka'+getId(zalba.about)">
                  <ul class='list-group' v-if='referencedBy.length != 0'>
                    <li v-for='id of referencedBy' v-bind:key='id' class='list-group-item'>
                      <a :href="id">{{id}}</a>
                    </li>
                  </ul>
                  <div v-else>Nijedan dokument ne referencira</div>
              </div>
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
      <h3 class="text-center">Zalbe na cutanje</h3>
      <table class="table table-sm table-bordered my-4">
        <thead>
          <tr>
            <th class="text-center">URL zalbe</th>
            <th class="text-center">Akcije</th>
          </tr>
        </thead>
        <tbody v-if="!loading">
          <tr
            v-for="(zalba, index) in zalbe_cutanje"
            :key="index"
          >
            <td class="text-center"><a :href="zalba.about">{{zalba.about}}</a></td>
            <td class="text-center">
              <a class="btn btn-primary" data-toggle="collapse" :href="'#collapsezalbecutanje'+getId(zalba.about)" role="button" aria-expanded="false" :aria-controls="'collapsezalbecutanje'+getId(zalba.about)"
                @click="getReferencedBy(zalba.about)">
                Referenced By
              </a>
              <div class="collapse mt-1" :id="'collapsezalbecutanje'+getId(zalba.about)">
                  <ul class='list-group' v-if='referencedBy.length != 0'>
                    <li v-for='id of referencedBy' v-bind:key='id' class='list-group-item'>
                      <a :href="id">{{id}}</a>
                    </li>
                  </ul>
                  <div v-else>Nijedan dokument ne referencira</div>
              </div>
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
      <h3 class="text-center">Resenja</h3>
      <table class="table table-sm table-bordered my-4">
        <thead>
          <tr>
            <th class="text-center">URL resenja</th>
            <th class="text-center">Akcije</th>
          </tr>
        </thead>
        <tbody v-if="!loading">
          <tr
            v-for="(resenje, index) in resenja"
            :key="index"
          >
            <td class="text-center"><a :href="resenje.about">{{resenje.about}}</a></td>
            <td class="text-center">
              <a class="btn btn-primary" data-toggle="collapse" :href="'#collapseresenja'+getId(resenje.about)" role="button" aria-expanded="false" :aria-controls="'collapseresenja'+getId(resenje.about)"
                @click="getReferencedBy(resenje.about)">
                Referenced By
              </a>
              <div class="collapse mt-1" :id="'collapseresenja'+getId(resenje.about)">
                  <ul class='list-group' v-if='referencedBy.length != 0'>
                    <li v-for='id of referencedBy' v-bind:key='id' class='list-group-item'>
                      <a :href="id">{{id}}</a>
                    </li>
                  </ul>
                  <div v-else>Nijedan dokument ne referencira</div>
              </div>
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
      <h3 class="text-center">Izvestaji</h3>
      <table class="table table-sm table-bordered my-4">
        <thead>
          <tr>
            <th class="text-center">URL izvestaja</th>
            <th class="text-center">Akcije</th>
          </tr>
        </thead>
        <tbody v-if="!loading">
          <tr
            v-for="(izvestaj, index) in izvestaji"
            :key="index"
          >
            <td class="text-center"><a :href="izvestaj.about">{{izvestaj.about}}</a></td>
            <td class="text-center">
              <a class="btn btn-primary" data-toggle="collapse" :href="'#collapseizvestaji'+getId(izvestaj.about)" role="button" aria-expanded="false" :aria-controls="'collapseizvestaji'+getId(izvestaj.about)"
                @click="getReferencedBy(izvestaj.about)">
                Referenced By
              </a>
              <div class="collapse mt-1" :id="'collapseizvestaji'+getId(izvestaj.about)">
                  <ul class='list-group' v-if='referencedBy.length != 0'>
                    <li v-for='id of referencedBy' v-bind:key='id' class='list-group-item'>
                      <a :href="id">{{id}}</a>
                    </li>
                  </ul>
                  <div v-else>Nijedan dokument ne referencira</div>
              </div>
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
// const { Octicon, check, x, clippy, code } = require('octicons-vue');
import zahtevApi from '../../../api/zahtev';
import obavestenjeApi from '../../../api/obavestenje';
import pretragaApi from '../../../api/pretraga';
import { constructKolekcijaZahteva, constructRezultatPretrage, pretragaXML, constructReferencedBy, referencedByXML } from '../../../util';
export default {
  name: 'Pretrage',
  components: {
    // Octicon
  },
  data: () => {
    return {

      zalbe_odluka: [],
      zalbe_cutanje: [],
      resenja: [],
      izvestaji: [],
      loading: false,

      search: {
        term: '',
        metadata: ''
      },

      referencedBy: [],
      flag: false

    };
  },
  async mounted() {
  
  },
  methods: {
    
    getId(url) {
      let tokens = url.split("/")
      return tokens[4]
    },


    async doSearch() {
      this.loading = true;
      let pretragaxml = pretragaXML(this.search);
      console.log('pretragaxml: ' + pretragaxml);
      const rezultatPretrage = constructRezultatPretrage((await pretragaApi.post(pretragaxml)).data);

      this.zalbe_odluka = rezultatPretrage.zalbe_odluka.length != 0 ? rezultatPretrage.zalbe_odluka : [];
      this.zalbe_cutanje = rezultatPretrage.zalbe_cutanje.length != 0 ? rezultatPretrage.zalbe_cutanje : [];
      this.resenja = rezultatPretrage.resenja.length != 0 ? rezultatPretrage.resenja : [];
      this.izvestaji = rezultatPretrage.izvestaji.length != 0 ? rezultatPretrage.izvestaji : [];
      this.loading = false;
    },

    async getReferencedBy(about) {
      if (this.flag) {
        this.flag = false;
        this.referencedBy = [];
        return;
      }
      this.flag = true;

      let aboutxml = referencedByXML(about);
      console.log('aboutxml' + aboutxml)
      const referencedBy = constructReferencedBy((await pretragaApi.getReferencedBy(aboutxml)).data);
      console.log('ref by: ' + referencedBy)

      this.referencedBy = referencedBy;
    }
  }
}
</script>

<style>

</style>
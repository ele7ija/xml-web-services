<template>
  <div>
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark" v-if="role">
      <router-link to="/" class="navbar-brand" :style="{'margin-left': '10px'}">Portal sluzbenika</router-link>
      <div class="navbar-collapse">
        <ul class="navbar-nav mr-auto mt-1">
          <div
            v-for="option in userOptions[role]"
            :key="option.id"
          >
            <div v-if="!option.hasChildren">
              <router-link :to="option.path" class="nav-link">{{option.displayName}}</router-link>
            </div>
            <div v-else>
              <div class="btn-group">
                <a class="nav-link dropdown-toggle" href="#" :id="option.id" data-toggle="dropdown">
                    {{option.displayName}}
                </a>
                <div class="dropdown-menu" :aria-labelledby="option.id">
                  <router-link
                    v-for="child in option.children"
                    :key="child.id"
                    :to="child.path"
                    class="dropdown-item"
                  >{{child.displayName}}</router-link>
                </div>
              </div>
            </div>
          </div>
        </ul>

        <ul class="navbar-nav mt-2" :style="{'margin-left': 'auto', 'margin-right': '20px'}">
          <li v-if="loadingFlag" class="nav-item mt-n1">
            <div class="spinner-border spinner-border-sm mr-2 spinner2" role="status">
                <span class="sr-only">Loading...</span>
            </div>
          </li>
          <li class="nav-item" @click="logOut">
            <Octicon :icon="signOut"/>
          </li>
        </ul>
      </div>
    </nav>

    <nav class="navbar navbar-expand-sm navbar-dark bg-dark" v-if="!role">
      <h3 class="navbar-brand" :style="{'margin-left': '10px'}">Portal sluzbenika</h3>
      <ul class="navbar-nav" :style="{'margin-left': 'auto', 'margin-right': '20px'}">
        <router-link to="login" class="nav-link">Prijava</router-link>
        <router-link to="register" class="nav-link">Registracija</router-link>
      </ul>
    </nav>

    <router-view></router-view>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import router from './vue-router';
import jwt_decode from 'jwt-decode';
const { Octicon, signOut } = require('octicons-vue');
export default {
  name: 'App',
  components: {
    Octicon
  },
  data: () => {
    return {
      signOut,
      loadingFlag: false
    };
  },
  computed: {
    ...mapGetters({
      userOptions: 'userOptions/getOptions',
      role: 'userOptions/getRole'
    })
  },
  created() {
    const token = sessionStorage.getItem('access_token');
    if (token) {
      const tokenParsed = jwt_decode(token);
      this.$store.commit('userOptions/setRole', tokenParsed.user.userRole, { root: true });
    }
  },
  methods: {
    logOut() {
      this.loadingFlag = true;
      setTimeout(
        () =>{
          this.$store.commit('userOptions/setRole', '', { root: true });
          sessionStorage.removeItem('access_token');
          this.loadingFlag = false;
          router.push({path: '/login'});
        },
        1000
      );
    }
  }
  
}
</script>

<style>
.octicon-sign-out {
  color: lightgrey;
  cursor: pointer;
  width: 18px;
  height: 18px;
}
.octicon-sign-out:hover {
  color: white;
  cursor: pointer;
}
.spinner2 {
  color: white;
}
</style>

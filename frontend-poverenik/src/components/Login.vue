<template>
  <div class="container centered5">
    <div class="row">
      <div class="col-12">
        <h2 class="text-center mb-4">Prijava</h2>
        <div v-if="loginFailed" class="alert alert-danger" role="alert">
          Nevalidno korisnicko ime ili lozinka
        </div>
        <div class="form-group">
          <label for="exampleInputEmail1">Korisnicko ime</label>
          <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" v-model="email">
          <small id="emailHelp" class="form-text text-muted">Unesite korisnicko ime da bi se ulogovali</small>
        </div>
        <div class="form-group">
          <label for="exampleInputPassword1">Lozinka</label>
          <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" v-model="password">
          <small id="emailHelp" class="form-text text-muted">Unesite lozinku da bi se ulogovali</small>
        </div>
        <button class="btn btn-outline-primary mt-3" :style="{width: '100%'}" @click="login" :disabled="!isValid">
          <div v-if="loading" class="spinner-border spinner-border-sm mr-2" role="status">
            <span class="sr-only">Loading...</span>
          </div>
          Uloguj se
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import {mapActions, mapGetters} from 'vuex';
import router from '../vue-router';
export default {
  name: 'Login',
  data: () => {
    return {
      email: '',
      password: '',
      loading: false,
      loginFailed: false
    };
  },
  computed: {
    ...mapGetters({
      redirectUri: 'userOptions/getRedirectUri'
    }),
    isValid() {
      return this.email && this.password;
    }
  },
  methods: {
    ...mapActions({
      vuexLogin: 'userOptions/login'
    }),
    async login() {
      this.loginFailed = false;
      try {
        this.loading = true;
        await this.vuexLogin({email: this.email, password: this.password});
        this.loading = false;
        if (this.redirectUri) {
          router.push({path: this.redirectUri});
          this.$store.commit('userOptions/setRedirectUri', '', {root: true});
        } else {
          router.push({path: '/'});
        }
      } catch(error) {
        this.loading = false;
        this.loginFailed = true;
        console.log(error);
      }
    }
  }
}
</script>

<style>
.centered5 {
  position: fixed; /* or absolute */
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
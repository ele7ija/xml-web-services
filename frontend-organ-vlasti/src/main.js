import Vue from 'vue'
import App from './App.vue'
import store from './vuex';
import router from './vue-router';
import 'popper.js';
import 'jquery';
import 'bootstrap/dist/js/bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';

Vue.config.productionTip = false

axios.interceptors.request.use(function (config) {
  if (config.url != 'http://localhost:8080/auth/login') {
    config.headers['Content-Type'] = 'application/xml'; 
  }
  if(
    config.url == 'http://localhost:8080/auth/login' ||
    (config.url == 'http://localhost:8080/gradjanin' && config.method == 'POST') ||
    (config.url == 'http://localhost:8080/sluzbenik' && config.method == 'POST')
  ) {
    return config;
  }
  const token = sessionStorage.getItem('access_token');
  if(token) {
    config.headers.Authorization =  `Bearer ${token}`;
  }
  return config;
});

new Vue({
  render: h => h(App),
  store,
  router
}).$mount('#app')

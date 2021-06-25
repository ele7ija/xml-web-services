import Vue from 'vue';
import Vuex from 'vuex';
import userOptions from './modules/userOptions';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    userOptions
  },
});
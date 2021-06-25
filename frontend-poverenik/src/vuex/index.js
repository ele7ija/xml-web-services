import Vue from 'vue';
import Vuex from 'vuex';
import userOptions from './modules/userOptions';
import obavestenje from './modules/obavestenje';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    userOptions,
    obavestenje
  },
});
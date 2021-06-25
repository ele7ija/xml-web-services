const state = {
  odabranoObavestenje: null,
};

const getters = {
  getOdabranoObavestenje: state => state.odabranoObavestenje
};

const actions = {};

const mutations = {
  setOdabranoObavestenje: (state, value) => state.odabranoObavestenje = value
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
};
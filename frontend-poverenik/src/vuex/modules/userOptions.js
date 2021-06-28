import loginApi from "../../api/login";
import jwt_decode from "jwt-decode";

const state = {
  options: {
    GRADJANIN: [
      {
        displayName: 'Zalba na odluku',
        hasChildren: false,
        fullName: "Zalba na odluku",
        description: 'Podnosenje zalbe na odluku uprave organa vlasti',
        name: 'ZalbaNaOdluku',
        id: 1
      },
      {
        displayName: 'Zalba na cutanje',
        hasChildren: false,
        fullName: "Zalba na cutanje",
        description: 'Podnosenje zalbe na cutanje uprave organa vlasti',
        name: 'ZalbaNaCutanje',
        id: 2
      }
    ],
    POVERENIK: [
      {
        displayName: 'Zalbe na odluku',
        fullName: "Pregled svih zalbi na odluku",
        description: 'Pregledaj sve zalbe na odluku protiv organa vlasti',
        name: 'ZalbeNaOdlukuPoverenik',
        hasChildren: false,
        id: 3
      },
      {
        displayName: 'Zalbe na cutanje',
        fullName: "Pregled svih zalbi na cutanje",
        description: 'Pregledaj sve zalbe na cutanje protiv organa vlasti',
        name: 'ZalbeNaCutanjePoverenik',
        hasChildren: false,
        id: 4
      },
      {
        displayName: 'Resenja',
        fullName: "Resenja",
        description: 'Pregledaj izdata resenja na sve vrste zalbi.',
        name: 'ResenjaAll',
        hasChildren: false,
        id: 5
      },
      {
        displayName: 'Izvestaj',
        fullName: "Godisnji izvestaj",
        description: 'Pregledaj godisnji izvestaj organa vlasti.',
        name: 'Izvestaji',
        hasChildren: false,
        id: 6
      }
    ]
  },
  role: ''
};

const getters = {
  getOptions: state => state.options,
  getRole: state => state.role
};

const actions = {
  async login({commit}, {email, password}) {
    const response = await loginApi.login({email, password});
    sessionStorage.setItem('access_token', response.data.jwt);
    const tokenParsed = jwt_decode(response.data.jwt);
    commit('setRole', tokenParsed.user.userRole);
  }
};

const mutations = {
  setRole: (state, role) => state.role = role
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
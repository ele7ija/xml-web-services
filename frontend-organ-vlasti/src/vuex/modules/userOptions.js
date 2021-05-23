import loginApi from "../../api/login";
import jwt_decode from "jwt-decode";

const state = {
  options: {
    GRADJANIN: [
      {
        displayName: 'Zahtev',
        hasChildren: true,
        id: 1,
        children: [
          {
            displayName: 'Podnesi',
            fullName: "Podnesi Novi Zahtev",
            description: 'Podnesi novi zahtev za pristup informaciji od javnog znacaja. Zahtev ce biti poslat sluzbeniku organa vlasti koji ce vas potom kontaktirati kada donese odluku po pitanju pristupa informaciji.',
            path: 'zahtev-create',
            id: 2
          },
          {
            displayName: 'Pregled',
            fullName: "Pregled Podnetih Zahteva",
            description: 'Pregledaj sve zahteve koje ste podneli ovom organu vlasti u proteklom periodu.',
            path: 'zahtev-all',
            id: 3
          }
        ]
      },
      {
        displayName: 'Obavestenje',
        fullName: "Pregled Svih Obavestenja",
        description: 'Pregledaj sva obavestenja o odlukama sluzbenika po pitanju Vasih podnetih zahteva za pristup informacijama od javnog znacaja',
        path: 'obavestenje-all',
        hasChildren: false,
        id: 4
      }
    ],
    SLUZBENIK: [
      {
        displayName: 'Zahtevi',
        fullName: "Pregled Svih Zahteva",
        description: 'Odabirom odredjenog zahteva mozete da ga prihvatite ili odbijete',
        path: 'sluzbenik-zahtev',
        hasChildren: false,
        id: 5
      },
      {
        displayName: 'Izvestaj',
        fullName: "Godisnji izvestaj",
        description: 'Podnesite godisnji izvestaj o broju podnetih i odbijenih zahteva, kao i broju i sadrzini zalbi protiv obavestenja. Uzvestaj se podnosi povereniku.',
        path: 'izvestaj',
        hasChildren: false,
        id: 6
      },
      {
        displayName: 'Pretraga',
        fullName: "Pretrage",
        description: 'Pretrage svih dokumenata po tekstualnom sadrzaju i po metapodacima.',
        path: 'pretrage',
        hasChildren: false,
        id: 7
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
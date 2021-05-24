import axios from 'axios';

export default {
  login({email, password}) {
    return axios.post(`${process.env.VUE_APP_BACKEND_ROOT}/auth/login`, {email, password});
  },
  createGradjanin(body) {
    return axios.post(`${process.env.VUE_APP_BACKEND_ROOT}/gradjanin`, body);
  },
  createSluzbenik(body) {
    return axios.post(`${process.env.VUE_APP_BACKEND_ROOT}/sluzbenik`, body);
  }
};
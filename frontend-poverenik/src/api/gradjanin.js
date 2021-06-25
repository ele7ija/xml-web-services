import axios from 'axios';

export default {
  getAuthenticatedGradjanin: () => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/gradjanin/authenticated`)
};
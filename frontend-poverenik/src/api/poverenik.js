import axios from 'axios';

export default {
  getAuthenticatedPoverenik: () => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/poverenik/authenticated`)
};
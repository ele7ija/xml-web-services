import axios from 'axios';

export default {
  getOdbijena: () => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/obavestenje/odbijena`),
  getIstekla: () => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/obavestenje/istekla`),
}
import axios from 'axios';

export default {
  getById: idObavestenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/obavestenje/${idObavestenja}`),
  getOdbijena: () => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/obavestenje/odbijena`),
  getIstekla: () => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/obavestenje/istekla`),
}
import axios from "axios";

export default {
  getById: id => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zahtev/${id}`),
  create: xml => axios.post(`${process.env.VUE_APP_BACKEND_ROOT}/zahtev`, xml),
  getByGradjanin: () => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zahtev/by-gradjanin`),
  getByNazivOrganaVlasti: () => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zahtev/by-organ-vlasti`),
  getPdf: idZahteva => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zahtev/pdf/${idZahteva}`, {responseType: 'blob'}),
  getHtml: idZahteva => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zahtev/html/${idZahteva}`, {responseType: 'blob'})
}
import axios from "axios";

export default {
  create: xml => axios.post(`${process.env.VUE_APP_BACKEND_ROOT}/resenje`, xml),
  getAll: () => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/resenje`),
  getById: idResenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/resenje/${idResenja}`),
  getPdf: idResenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/resenje/pdf/${idResenja}`, {responseType: 'blob'}),
  getHtml: idResenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/resenje/html/${idResenja}`, {responseType: 'blob'}),
  getJsonMetadata: idResenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/resenje/metadata/json/${idResenja}`, {responseType: 'blob'}),
  getXmlMetadata: idResenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/resenje/metadata/xml/${idResenja}`, {responseType: 'blob'}),
  getRdfMetadata: idResenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/resenje/metadata/rdf/${idResenja}`, {responseType: 'blob'}),
}
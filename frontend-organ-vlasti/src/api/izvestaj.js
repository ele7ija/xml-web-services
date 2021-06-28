import axios from "axios";

export default {
  getAll: () => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/izvestaj`),
  getById: id => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/izvestaj/${id}`),
  create: xml => axios.post(`${process.env.VUE_APP_BACKEND_ROOT}/izvestaj`, xml),
  getPdf: idizvestaja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/izvestaj/pdf/${idizvestaja}`, {responseType: 'blob'}),
  getHtml: idizvestaja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/izvestaj/html/${idizvestaja}`, {responseType: 'blob'}),
  getJsonMetadata: idizvestaja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/izvestaj/metadata/json/${idizvestaja}`, {responseType: 'blob'}),
  getXmlMetadata: idizvestaja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/izvestaj/metadata/xml/${idizvestaja}`, {responseType: 'blob'}),
  getRdfMetadata: idizvestaja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/izvestaj/metadata/rdf/${idizvestaja}`, {responseType: 'blob'}),
}
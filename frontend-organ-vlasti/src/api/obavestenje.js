import axios from "axios";

export default {
  create: xml => axios.post(`${process.env.VUE_APP_BACKEND_ROOT}/obavestenje`, xml),
  getById: idObavestenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/obavestenje/${idObavestenja}`),
  getObavestenjeByIdZahteva: idZahteva => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/obavestenje/by-zahtev/${idZahteva}`),
  getByUlogovaniTrazilac: () => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/obavestenje/by-trazilac`),
  getPdf: idObavestenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/obavestenje/pdf/${idObavestenja}`, {responseType: 'blob'}),
  getHtml: idObavestenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/obavestenje/html/${idObavestenja}`, {responseType: 'blob'}),
  getJsonMetadata: idObavestenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/obavestenje/metadata/json/${idObavestenja}`, {responseType: 'blob'}),
  getXmlMetadata: idObavestenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/obavestenje/metadata/xml/${idObavestenja}`, {responseType: 'blob'}),
  getRdfMetadata: idObavestenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/obavestenje/metadata/rdf/${idObavestenja}`, {responseType: 'blob'}),
}
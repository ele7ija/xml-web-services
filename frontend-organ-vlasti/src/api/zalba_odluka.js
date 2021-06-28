import axios from "axios";

export default {
    create: xml => axios.post(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku`, xml),
    getAll: () => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku`),
    getById: idObavestenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku/${idObavestenja}`),
    getObavestenjeByIdZahteva: idZahteva => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku/by-zahtev/${idZahteva}`),
    getByUlogovaniTrazilac: () => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku/by-trazilac`),
    getPdf: idObavestenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku/pdf/${idObavestenja}`, {responseType: 'blob'}),
    getHtml: idObavestenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku/html/${idObavestenja}`, {responseType: 'blob'}),
    getJsonMetadata: idObavestenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku/metadata/json/${idObavestenja}`, {responseType: 'blob'}),
    getXmlMetadata: idObavestenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku/metadata/xml/${idObavestenja}`, {responseType: 'blob'}),
    getRdfMetadata: idObavestenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku/metadata/rdf/${idObavestenja}`, {responseType: 'blob'}),
  }
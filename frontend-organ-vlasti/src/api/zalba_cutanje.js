import axios from "axios";

export default {
    create: xml => axios.post(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje`, xml),
    getAll: () => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje`),
    getById: idObavestenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje/${idObavestenja}`),
    getObavestenjeByIdZahteva: idZahteva => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje/by-zahtev/${idZahteva}`),
    getByUlogovaniTrazilac: () => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje/by-trazilac`),
    getPdf: idObavestenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje/pdf/${idObavestenja}`, {responseType: 'blob'}),
    getHtml: idObavestenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje/html/${idObavestenja}`, {responseType: 'blob'}),
    getJsonMetadata: idObavestenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje/metadata/json/${idObavestenja}`, {responseType: 'blob'}),
    getXmlMetadata: idObavestenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje/metadata/xml/${idObavestenja}`, {responseType: 'blob'}),
    getRdfMetadata: idObavestenja => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje/metadata/rdf/${idObavestenja}`, {responseType: 'blob'}),
  }
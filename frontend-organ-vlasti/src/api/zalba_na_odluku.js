import axios from "axios";

export default {
  create: xml => axios.post(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku`, xml),
  accept: idZalbe => axios.put(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku/accept/${idZalbe}`),
  decline: idZalbe => axios.put(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku/decline/${idZalbe}`),
  getAllUlogovanogKorisnika: () => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku/by-gradjanin/get`),
  getAllNeobradjeneZalbeNaOdluku: () => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku/neobradjene/get`),
  getById: idZalbe => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku/${idZalbe}`),
  getPdf: idZalbe => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku/pdf/${idZalbe}`, {responseType: 'blob'}),
  getHtml: idZalbe => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku/html/${idZalbe}`, {responseType: 'blob'}),
  getJsonMetadata: idZalbe => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku/metadata/json/${idZalbe}`, {responseType: 'blob'}),
  getXmlMetadata: idZalbe => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku/metadata/xml/${idZalbe}`, {responseType: 'blob'}),
  getRdfMetadata: idZalbe => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-odluku/metadata/rdf/${idZalbe}`, {responseType: 'blob'}),
}
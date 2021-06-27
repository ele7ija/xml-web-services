import axios from "axios";

export default {
  create: xml => axios.post(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje`, xml),
  accept: idZalbe => axios.put(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje/accept/${idZalbe}`),
  decline: idZalbe => axios.put(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje/decline/${idZalbe}`),
  getAllUlogovanogKorisnika: () => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje/by-gradjanin/get`),
  getAllNeobradjeneZalbeNaCutanje: () => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje/neobradjene/get`),
  getById: idZalbe => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje/${idZalbe}`),
  getPdf: idZalbe => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje/pdf/${idZalbe}`, {responseType: 'blob'}),
  getHtml: idZalbe => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje/html/${idZalbe}`, {responseType: 'blob'}),
  getJsonMetadata: idZalbe => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje/metadata/json/${idZalbe}`, {responseType: 'blob'}),
  getXmlMetadata: idZalbe => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje/metadata/xml/${idZalbe}`, {responseType: 'blob'}),
  getRdfMetadata: idZalbe => axios.get(`${process.env.VUE_APP_BACKEND_ROOT}/zalba-na-cutanje/metadata/rdf/${idZalbe}`, {responseType: 'blob'}),
}
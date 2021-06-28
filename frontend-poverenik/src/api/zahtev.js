import axios from "axios";

export default {
  getById: id => {
    console.log(process.env.VUE_APP_ORGAN_VLASTI_BACKEND_ROOT)
    return axios.get(`${process.env.VUE_APP_ORGAN_VLASTI_BACKEND_ROOT}/zahtev/${id}`)
  }
}
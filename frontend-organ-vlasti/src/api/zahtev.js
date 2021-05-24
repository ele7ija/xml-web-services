import axios from "axios";

export default {
  create: xml => axios.post(`${process.env.VUE_APP_BACKEND_ROOT}/zahtev`, xml)
}
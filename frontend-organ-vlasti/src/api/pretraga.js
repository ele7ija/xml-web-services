import axios from "axios";

export default {
  post: searchObj => axios.post(`${process.env.VUE_APP_BACKEND_ROOT}/pretraga`, searchObj),
  getReferencedBy: about => axios.post(`${process.env.VUE_APP_BACKEND_ROOT}/pretraga/referenced-by`, about)
}
import axios from "axios";
import { ref } from "vue";

export async function useAxios(method, url, requestData) {
  const data = ref(null);
  const error = ref(null);

  console.log("Axios 도착 : " + method + "     " + url + "     " + requestData);

  await axios({
    method: method, // 'post'
    url: url, //'/user/12345'
    data: requestData,
    header: {
      "Context-Type": "multipart/form-data",
    },
  })
    .then((response) => {
      //console.log("axios 성공");
      data.value = response.data;
    })
    .catch((errorRes) => {
      //console.log("axios 실패");
      error.value = errorRes;
    });

  return { data, error };
}

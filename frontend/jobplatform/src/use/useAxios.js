import axios from "axios";
import { ref } from "vue";

export async function useAxios(method, url, requestData) {
  const data = ref(null);
  const error = ref(null);

  // const axiosData = async () => {
  // data.value = null;
  // error.value = null;

  await axios({
    method: method, // 'post'
    url: url, //'/user/12345'
    data: requestData,
    header: {
      "Context-Type": "multipart/form-data",
    },
  })
    .then((response) => {
      console.log("axios 성공");
      console.log(response.data);
      data.value = response.data;
      console.log(data);
      //data.value = [{ a: 0 }, { a: 1 }, { a: 2 }, { a: 3 }];
    })
    .catch((errorRes) => {
      console.log("axios 실패");
      console.error(errorRes);
      error.value = errorRes;
    });

  return { data, error };
  // };

  // watchEffect(() => {
  //   axiosData();
  // });
}

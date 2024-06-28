import axios from 'axios';
import { ref } from 'vue';

export async function useAxios(method, url, requestData) {
  const success = ref(null);
  const error = ref(null);

  console.log('Axios 도착 : ' + method + '     ' + url + '     ' + requestData);

  await axios({
    method: method, // 'post'
    url: url, //'/user/12345'
    data: requestData,
    header: {
      'Context-Type': 'multipart/form-data',
    },
  })
    .then((response) => {
      //console.log("axios 성공");
      if (response.data != null) {
        success.value = response.data;
      }
    })
    .catch((response) => {
      //console.log("axios 실패");
      if (response.data != null) {
        error.value = response.data;
      }
    });
  console.log('Axios 리턴 직전');
  return { success, error };
}

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
      //console.log('axios 성공');
      success.value = response.data;
    })
    .catch((response) => {
      //console.log('axios 실패');
      error.value = response.data;
    });

  return { success, error };

  // const { success, error } = await useAxios(
  //     "get",
  //     "/resumes/resume-list/" +
  //     resumeListData.value.searchData.mbr_sq +
  //     "/" +
  //     resumeListData.value.searchData.sort +
  //     "/" +
  //     resumeListData.value.searchData.pageNo,
  //     null
  // );

  // if (success.value != null) {
  //     // 성공 로직
  //     resumeListData.value = success.value;
  // } else if (error.value != null) {
  //     // 실패시 로직
  //     console.log("useAxios 실패" + error.value);
  // }
}

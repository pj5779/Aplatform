<template>
  <div class="boardMain">
    <div class="container mb-5">
    <table class="table table-striped mb-0 text-white">
      <thead>
        <tr>
          <th class="th-title">제목</th>
          <th class="th-writer">작성자</th>
          <th class="th-category" v-if="showCategory">카테고리</th>
          <th class="th-date" v-if="showDate">등록일</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, idx) in paginatedItems" :key="idx">
          <td class="td-title" ><router-link :to="`/board/detail/${item.brdSq}`">{{ item.brdTl }}</router-link></td>
          <td class="td-writer" >{{ item.insrtMbrSq ? item.insrtMbrSq.mbrId : 'Unknown' }}</td>
          <td class="td-category" v-if="showCategory">{{ item.brdCtgryCd }}</td>
          <td class="td-date" v-if="showDate">{{ formatDate(item.insrtDtm,formatType)}}</td>
        </tr>
      </tbody>
    </table>
  </div>
  </div>
    <BasePagination class="BasePagination" v-if="showPagination" :currentPage="currentPage" :totalPages="totalPages" :goToPage="goToPage" />
</template>

<script setup>
import axios from "axios";
import { reactive, computed, onMounted, defineProps } from "vue";
import BasePagination from "../common/BasePagination.vue";

// eslint-disable-next-line no-unused-vars
const props = defineProps({
  showCategory : {
    type : Boolean,
    default : true
  },
  showDate : {
    type : Boolean,
    default : true
  },
  showPagination : {
    type : Boolean,
    default : true
  },
  formatType: {
  type: String,
  default: "full"
}
})

const state = reactive({
  items: [], //서버에 있는 데이터를 배열에 담음
  currentPage: 1, //현재 기본 페이지
  itemsPerPage: 10 //최대 10개까지가 한 페이지
});


// 컴포넌트가 마운트될 때 데이터 가져오기
onMounted(() => {
try{
axios.get("/board/list").then(({ data }) => {
  state.items = data.filter(item => item.brdCtgryCd === "general");
}); //카테고리가 일반인것만 가져와서 items 배열에 담음
} catch ( error ) {
  console.log("통신실패")
}
});


//현재 페이지에 표시될 항목을 계산하는 couputed 속성 (첫번째 항목 인덱스와 마지막 항목의 인덱스 계산)
const paginatedItems = computed(() => {

  const sortedItems = state.items.slice().sort((a, b) => {
  // 예시로 등록일 기준으로 내림차순 정렬
  return new Date(b.insrtDtm) - new Date(a.insrtDtm);
});

const startIndex = (state.currentPage - 1) * state.itemsPerPage;
const endIndex = startIndex + state.itemsPerPage;
return sortedItems.slice(startIndex, endIndex);
});

//전체 항목수를 페이지당 항목수로 나눈 결과를 올림하여 전체 페이지수를 계산
const totalPages = computed(() => Math.ceil(state.items.length / state.itemsPerPage));

//받은 페이지번호가 1보다 크거나 같고 전체 페이지수보다 작거나 같은지 확인 후 범위내에 있다면 해당 페이지로 이동
function goToPage(page) {
  if (page >= 1 && page <= totalPages.value) {
    state.currentPage = page;
  }
}

// 메인홈에선 등록일이 아닌 몇분전이나 몇시간전으로 출력하기
const formatDate  = (datetimeString, formatType) => {
const date = new Date(datetimeString);
const now = new Date();
const diffTime = Math.abs(now - date);
const diffMinutes = Math.ceil(diffTime / (1000 * 60)); // 차이를 분 단위로 계산
const diffDays = Math.floor(diffTime / (1000 * 60 * 60 * 24)); // 차이를 일 단위로 계산

if (formatType === "relative") {
  // 상대적인 형태로 변환하는 경우
  if (diffMinutes < 5) {
    return "방금 전";

    } else if (diffMinutes < 60) {
    return `${diffMinutes}분 전`;

    } else if (diffDays < 1) {
    return `${Math.floor(diffMinutes / 60)}시간 전`;

    } else if (diffDays === 1) {
    return "1일 전";

    } else {
    return `${diffDays}일 전`;
  }
} else {
  return date.toLocaleDateString(); // 날짜를 년-월-일 형식으로 변환하여 출력
}
};
</script>

<style scoped="">

.boardMain {
  overflow: hidden;
}

.table-container {
  overflow-y: auto; /* 세로 스크롤이 필요할 경우 스크롤바 표시 */
  max-height: 100%; /* 최대 높이 설정 */
}

.th-title, .td-title{
  text-align: left;
  width: 70%;
}

.th-writer, .td-writer {

text-align: center;
}

.th-category, .td-category{

text-align: center;
}

.th-date, .td-date {
text-align: center;
}

</style>

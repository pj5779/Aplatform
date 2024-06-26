<template>
  <div id="examples" class="container py-2">
    <div class="col pb-3 pt-3">
      <div class="row">
        <h1 class="form-group col-md-12">
          <strong class="font-weight-extra-bold"> 지원자 목록 </strong>
        </h1>

        <hr class="gradient" />
      </div>

      <div class="row">
        <!-- 코드화 안되어있어서 하드코딩 -->
        <div class="form-group col-md-2">
          <!-- 여기 해야함 -->
          <select class="form-select form-control h-auto py-2" v-model="selectValue"
            @change="changeDivision(selectValue)">
            <option :value='ref("all")' :key="all">구분</option>
            <option :value='ref("apply")' :key="apply">지원</option>
            <option :value='ref("proposal")' :key="proposal">제안</option>
          </select>
        </div>

        <!-- 코드화 -->
        <div class="form-group col-md-2">
          <select class="form-select form-control h-auto py-2">
            <option :value="0">상태</option>
            <option v-for="applyCondition in applyListData.applyConditions" :key="applyCondition.code_id"
              :value="applyCondition.code_id">
              {{ applyCondition.code_name }}
            </option>
          </select>
        </div>

        <div class="form-group col-md-6"></div>

        <!-- 코드화 안되어있어서 하드코딩 -->
        <div class="form-group col-md-2">
          <select class="form-select form-control h-auto py-2">
            <option :value='ref("asc")'>올림차순</option>
            <option :value='ref("desc")'>내림차순</option>
          </select>
        </div>
      </div>

      <div class="row">
        <!-- 자료없을때 예외 -->
        <div v-if="applyListData.applyDatas.length == 0">
          <strong class="font-weight-extra-bold"> 자료가 없습니다. </strong>
        </div>
        <!-- 자료있을때 for -->
        <div v-else>
          <div v-for="applyData in applyListData.applyDatas" :key="applyData.a">
            <ApplyDatas :applyData="applyData" />
          </div>
        </div>
      </div>
      <hr class="gradient" />
      <div class="row">
        <div
          v-if="applyListData.paginationData.totalDataCount != undefined && applyListData.paginationData.totalDataCount != 0">
          <PaginationData :paginationData="applyListData.paginationData" @change-page-no="changePageNo" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onBeforeMount, onMounted, ref } from "vue";

import ApplyDatas from "../../../../components/fo/enterprise/apply/ApplyDatas.vue";
import PaginationData from "../../../../components/fo/enterprise/common/PaginationData.vue";
import { useAxios } from "../../../../use/useAxios";

const applyListData = ref({
  applyDatas: [],
  paginationData: [],
  searchListData: [],
  applyConditions: [],
});

// const jbp_sq = ref();

console.log("일반")

// axios 함수
const callAxios = async () => {
  await useAxios("get", "/applys/apply-list/" + applyListData.value.searchListData.jbp_sq + "/" + applyListData.value.searchListData.division + "/" + applyListData.value.searchListData.condition + "/" + applyListData.value.searchListData.sort + "/" + applyListData.value.searchListData.pageNo, null)
    .then((success) => {
      applyListData.value = success.data.value;

    })
    .catch((error) => {
      console.log(error.error.value);
    });
};

onMounted(() => {
  console.log("온마운트")

});

onBeforeMount(() => {
  console.log("비포마운트");
  applyListData.value.searchListData = { jbp_sq: 1, division: "all", condition: 0, sort: "asc", pageNo: 1 };
  callAxios();
})

// 이벤트 모음
// 페이지네이션 페이지 변경 클릭
const changePageNo = (event) => {
  applyListData.value.searchListData.pageNo = event;
  callAxios();
  console.log(applyListData.value.searchListData.pageNo)
}
const changeDivision = (event) => {
  console.log(event)
  applyListData.value.searchListData.division = event;
  callAxios();
}


</script>

<style scoped></style>

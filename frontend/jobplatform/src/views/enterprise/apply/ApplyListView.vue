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
          <select class="form-select form-control h-auto py-2">
            <option :value="all">구분</option>
            <option :value="apply">지원</option>
            <option :value="proposal">제안</option>
          </select>
        </div>

        <!-- 코드화 -->
        <div class="form-group col-md-2">
          <select class="form-select form-control h-auto py-2">
            <option :value="0">상태</option>
            <option v-for="applyCondition in applyListData.applyConditions" :key="applyCondition.code_id" :value="applyCondition.code_id">
              {{ applyCondition.code_name }}
            </option>
          </select>
        </div>

        <div class="form-group col-md-6"></div>

        <!-- 코드화 안되어있어서 하드코딩 -->
        <div class="form-group col-md-2">
          <select class="form-select form-control h-auto py-2">
            <option :value="asc">올림차순</option>
            <option :value="desc">내림차순</option>
          </select>
        </div>
      </div>

      <div class="row">
        <!-- 자료없을때 예외 -->
        <div v-if="applyListData.applyDatas === null">
          <strong class="font-weight-extra-bold"> 자료가 없습니다. </strong>
        </div>
        <!-- 자료있을때 for -->
        <div v-for="applyData in applyListData.applyDatas" :key="applyData.a">
          <ApplyDatas :applyData="applyData" />
        </div>
      </div>
      <hr class="gradient" />
      <div class="row">
        <div v-if="applyListData.paginationData !== null">
          <PaginationData :paginationData="applyListData.paginationData" />
        </div>
      </div>

      <!-- 연습 -->
      <button @click="addCom()">컴포넌트 추가</button>
      <button @click="minCom()">컴포넌트 삭제</button>
      <div id="com"></div>
      <!-- 연습 -->
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { useAxios } from "../use/useAxios";
import ApplyDatas from "../../../components/enterprise/apply/ApplyDatas.vue";
import PaginationData from "../../../components/enterprise/common/PaginationData.vue";

const applyListData = ref({
  applyDatas: [],
  paginationData: [],
  searchListData: [],
  applyConditions: [],
});

onMounted(async () => {
  const { data, error } = await useAxios("get", "/applys/apply-list/1/all/0/asc", null);
  applyListData.value.applyDatas = [{ a: 0 }, { a: 1 }, { a: 2 }];
  console.log(data);
  console.log(error);
});

const addCom = () => {
  const input = `<div><Applys /></div>`;

  const com = document.getElementById("com");
  com.appendChild(input);
};

const minCom = () => {};
</script>

<style scoped></style>

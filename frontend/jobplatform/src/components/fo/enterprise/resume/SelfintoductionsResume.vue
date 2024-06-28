<template>
  <div class="col pb-3 pt-1">
    <h4 class="mb-0">
      자기소개서 &nbsp;
      <a href="#" class="btn btn-primary btn-circle mb-2" @click="addComponents()">
        <i class="fa fa-plus"></i>
      </a>
    </h4>
    <hr class="mt-1 mb-2" />
    <div>
      <div v-for="(selfintoductionData, index) in selfintoductionDatas" :key="index">
        <SelfintoductionDatas :selfintoductionDatas="selfintoductionData" @remove-components="removeComponents" />

        <!-- 여기에 박아넣는다 SelfintoductionDatas-->
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import SelfintoductionDatas from "./SelfintoductionDatas.vue";

const selfintoductionDatas = ref([]);
let count = 1;

const addComponents = () => {
  selfintoductionDatas.value.push({ id: count, title: "", content: "" });

  count++;
};

const removeComponents = (emit) => {
  let index = -1;

  for (let i = 0; i < selfintoductionDatas.value.length; i++) {

    if (selfintoductionDatas.value[i].id == emit) {
      index = i;
    }
  }

  selfintoductionDatas.value.splice(index, 1);
};
</script>

<style scoped></style>

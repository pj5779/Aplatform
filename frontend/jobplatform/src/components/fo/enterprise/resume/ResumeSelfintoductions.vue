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
        <SelfintoductionDatas :id="selfintoductionData.id" @remove-components="removeComponents" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from "vue";
import SelfintoductionDatas from "./SelfintoductionDatas.vue";

const selfintoductionDatas = reactive([]);
let count = 1;

const addComponents = () => {
  selfintoductionDatas.value.push({ id: count });
  count++;
};

const removeComponents = (event) => {
  let index = -1;

  for (let i = 0; i < selfintoductionDatas.value.length; i++) {
    console.log(selfintoductionDatas.value[i].id);
    if (selfintoductionDatas.value[i].id == event) {
      index = i;
    }
  }

  selfintoductionDatas.value.splice(index, 1);
};
</script>

<style scoped></style>

<template>
  <div class="col-lg-4">
    <ul class="pagination">
      <li class="page-item" v-if="props.paginationData.pageGroupsOfCurrentPage > 1">
        <a href="#" class="page-link" :value="props.paginationData.startNumOfPageGroups - 1">
          <i class="fas fa-angle-left"></i>
        </a>
      </li>

      <li
        class="page-item"
        v-for="(page, index) in props.paginationData.endNumOfPageGroups"
        :key="props.paginationData.startNumOfPageGroups + index"
        :value="props.paginationData.startNumOfPageGroups + index"
      >
        <a href="#" class="page-link">{{ props.paginationData.startNumOfPageGroups + index }}</a>
      </li>
      <!-- 온클릭시 페이지 이동 이벤트 일반화 방법이 있을듯??? 찾아보고 하기-->
      <li class="page-item" v-if="props.paginationData.pageGroupsOfCurrentPage < props.paginationData.totalPageGroupsCount">
        <a href="#" class="page-link" :value="props.paginationData.endNumOfPageGroups + 1">
          <i class="fas fa-angle-right"></i>
        </a>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { defineProps } from "vue";

const props = defineProps(["paginationData"]);

console.log("음?");
console.log(props.paginationData.endNumOfPageGroups);
console.log(props.paginationData.endNumOfPageGroups - props.paginationData.showPageGroupsCount * (props.paginationData.pageGroupsOfCurrentPage - 1));
</script>

<style scoped></style>

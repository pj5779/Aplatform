<template>
  <div class="col-lg-4">
    <ul class="pagination">
      <li
        class="page-item"
        v-if="props.paginationData.pageGroupsOfCurrentPage > 1"
        :value="props.paginationData.startNumOfPageGroups - 1"
        @click="$emit('changePageNo', props.paginationData.startNumOfPageGroups - 1)"
      >
        <a href="#" class="page-link">
          <i class="fas fa-angle-left"></i>
        </a>
      </li>
      <li
        class="page-item"
        :class="[props.paginationData.startNumOfPageGroups + index == props.paginationData.pageNo ? 'active' : '']"
        v-for="(page, index) in props.paginationData.endNumOfPageGroups -
        props.paginationData.showPageGroupsCount * (props.paginationData.pageGroupsOfCurrentPage - 1)"
        :key="props.paginationData.startNumOfPageGroups + index"
        :value="props.paginationData.startNumOfPageGroups + index"
        @click="$emit('changePageNo', props.paginationData.startNumOfPageGroups + index)"
      >
        <a href="#" class="page-link">{{ props.paginationData.startNumOfPageGroups + index }}</a>
      </li>
      <li
        class="page-item"
        v-if="props.paginationData.pageGroupsOfCurrentPage < props.paginationData.totalPageGroupsCount"
        :value="props.paginationData.endNumOfPageGroups + 1"
        @click="$emit('changePageNo', props.paginationData.endNumOfPageGroups + 1)"
      >
        <a href="#" class="page-link">
          <i class="fas fa-angle-right"></i>
        </a>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { defineProps } from "vue";

const props = defineProps(["paginationData"]);
</script>

<style scoped></style>

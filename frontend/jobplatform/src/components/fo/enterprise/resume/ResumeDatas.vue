<template>
  <section class="call-to-action with-full-borders mb-2">
    <div class="row col-sm-12 col-lg-12">
      <div class="col-sm-10 col-lg-10">
        <div class="call-to-action-content">
          <h3>
            <strong class="font-weight-extra-bold">{{ props.resumeData.rsm_tl }}</strong>
          </h3>
        </div>
      </div>
      <div class="col-sm-2 col-lg-2">
        <div class="call-to-action-btn">
          <a v-if="props.resumeData.rsm_rprsntv_yn === 'Y'" href="#"
            class="btn btn-rounded btn-quaternary mb-2">대표이력서</a>
          <a v-if="props.resumeData.rsm_rprsntv_yn === 'N'" href="#"
            class="btn btn-outline btn-rounded btn-quaternary mb-2">대표이력서 설정</a>
        </div>
      </div>
    </div>
    <div class="row col-sm-12 col-lg-12">
      <div class="col-sm-4 col-lg-4">
        <p class="mb-0">{{ props.resumeData.insrt_dtm }}</p>
      </div>
      <div class="col-sm-5 col-lg-5"></div>
      <div class="col-sm-1 col-lg-1">
        <div class="call-to-action-btn">
          <a class="btn btn-outline btn-rounded btn-primary mb-2"
            @click="$emit('addResumes', props.resumeData.rsm_sq)">복제</a>
        </div>
      </div>
      <div class="col-sm-1 col-lg-1">
        <div class="call-to-action-btn">
          <a href="#" class="btn btn-outline btn-rounded btn-tertiary mb-2"
            @click="$emit('modifyResumes', props.resumeData.rsm_sq)">수정</a>
        </div>
      </div>
      <div class="col-sm-1 col-lg-1">
        <div class="call-to-action-btn">
          <a href="#" class="btn btn-outline btn-rounded btn-secondary mb-2"
            @click="$emit('deleteResumes', props.resumeData.rsm_sq)">삭제</a>
        </div>
      </div>
    </div>
  </section>

  <!-- <Resumes :resumeData="item" @add-resumes="addResumes" @modify-resumes="modifyResumes" @adelete-resumes="deleteResumes"/> -->
</template>

<script setup>
import { defineProps } from "vue";

const props = defineProps(["resumeData"]);

</script>

<style scoped></style>
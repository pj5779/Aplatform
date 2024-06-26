<template>
  <section class="call-to-action with-full-borders mb-2 mainContainer">
    <div class="col-sm-2 col-lg-2">
      <div class="col-sm-12 col-lg-12 imgContainer">
        <span class="img-thumbnail d-block">
          <img
            class="img-fluid"
            src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdraZyZ8CC81iPsZST1_TLB0SBOXC6wfvQ_Exx1uKkGsFpi4t_Mj4o8HhM_vPwI60yBF8&usqp=CAU"
            alt
            style="width: 200px"
          />
        </span>
      </div>
    </div>
    <div class="row col-sm-10 col-lg-10">
      <div class="col-sm-4 col-lg-4">이름 : {{ props.applyData.rsm_name }}</div>
      <div class="col-sm-3 col-lg-3">희망연봉 : {{ fomatNull(props.applyData.rsm_es) }}</div>
      <div class="col-sm-3 col-lg-3">최종학력 : {{ props.applyData.rsm_fnl_edctn_name }}</div>
      <div class="col-sm-2 col-lg-2">학점 : {{ fomatNull(props.applyData.rsm_grd) }}</div>
      <hr class="gradient mt-4" />
      <div class="col-sm-2 col-lg-3">상태 : {{ props.applyData.apy_cndtn_name }}</div>
      <div class="col-sm-3 col-lg-2">구분 : {{ props.applyData.rnqns_name }}</div>
      <div class="col-sm-4 col-lg-4">지원일시 :{{ fomatDate(props.applyData.apy_dtm) }}</div>
      <div class="col-sm-3 col-lg-3">
        <div class="call-to-action-btn">
          <!-- 루트에 pk 들고 가는거 만들어야함 -->
          <a class="btn btn-outline btn-rounded btn-light"> 상세열람 </a>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import { defineProps } from "vue";

const props = defineProps(["applyData"]);

const fomatDate = (dateTime) => {
  const returnData = new Date(dateTime).toLocaleDateString();

  return returnData;
};

const fomatNull = (data) => {
  let returnData = data;

  if(data == 0) {
    returnData = "미입력";
  } else {
    returnData = data + "/ 4.5";
  }

  return returnData;
};



</script>

<style scoped>
.mainContainer {
  padding-left: 10px;
  padding-right: 10px;
}
.imgContainer {
  padding-right: 5px;
}
</style>

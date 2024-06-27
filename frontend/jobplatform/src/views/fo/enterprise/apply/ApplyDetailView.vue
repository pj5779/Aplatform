<template>
  <div id="examples" class="container py-2">
    <div class="col pb-3 pt-3">
      <div class="row">
        <h1 class="form-group col-md-12">
          <strong class="font-weight-extra-bold"> 지원자 상세 </strong>
        </h1>
        <hr class="gradient" />
      </div>
      <div class="row">
        <h3 class="form-group col-md-6">
          <div class="col-sm-2 col-lg-4"> 제목 </div>
        </h3>
      </div>
      <div class="row">
        <section class="call-to-action with-full-borders mb-2 mainContainer">
          <div class="col-sm-2 col-lg-2">
            <div class="col-sm-12 col-lg-12 imgContainer">
              <span class="img-thumbnail d-block">
                <img class="img-fluid"
                  src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdraZyZ8CC81iPsZST1_TLB0SBOXC6wfvQ_Exx1uKkGsFpi4t_Mj4o8HhM_vPwI60yBF8&usqp=CAU"
                  alt style="width: 200px" />
              </span>
            </div>
          </div>
          <div class="row col-sm-10 col-lg-10">
            <div class="col-sm-4 col-lg-4">이름</div>
            <div class="col-sm-3 col-lg-1">성별</div>
            <div class="col-sm-3 col-lg-1">나이</div>
            <div class="col-sm-2 col-lg-2">생년월일</div>
            <div class="col-sm-2 col-lg-4">전화번호</div>
            <hr class="gradient mt-4" />
            <div class="col-sm-2 col-lg-8">주소</div>
            <div class="col-sm-3 col-lg-4">이메일</div>
            <hr class="gradient mt-4" />
            <div class="col-sm-4 col-lg-3">희망연봉</div>
            <div class="col-sm-3 col-lg-3">최종학력</div>
            <div class="col-sm-3 col-lg-3">학점</div>
            <div class="col-sm-3 col-lg-3"></div>
          </div>
        </section>
        <div class="row"></div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useAxios } from "@/use/useAxios";
import { onMounted } from "vue";


//루트에서 들고온 PK 값
const { apy_sq } = history.state;

// axios 함수
const callAxios = async () => {
  await useAxios("get", "/applys/apply-detail/" + apy_sq, null)
    .then((data) => {
      console.log(data.success.value);
      console.log(data.error.value);
    })
    .catch((data) => {
      console.log(data.error.value);
    });
};

onMounted(() => {
  // 첫페이지 입장시 정보 받아오기
  callAxios();
});
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

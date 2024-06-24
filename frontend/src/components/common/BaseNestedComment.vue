<template>
  <div class="post-block mt-5 post-leave-comment">
    <h4 class="mb-3">대댓글작성</h4>
    <div class="p-2">
      <div class="row">
        <div class="form-group col">
          <textarea
            class="form-control"
            maxlength="10000"
            v-model="nstdcCntnt"
            id="nstdcCntnt"
            rows="5"
            placeholder="내용을 입력하세요"
          ></textarea>
        </div>
      </div>
      <div class="row">
        <div class="form-group col mb-0">
          <button @click="submitComment()">작성</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import { ref, computed, defineProps } from "vue";
import axios from "axios";
import store from "@/store";

const props = defineProps({
  cmntSq: {
    required: true,
  },
});

const nstdcCntnt = ref("");
const router = useRouter();
const isLoginMember = computed(() => store.getters.isLoginMember);

const submitComment = () => {
  if (nstdcCntnt.value.trim() === "") {
    alert("내용을 확인하세요.");
    return;
  }

  if (!isLoginMember.value) {
    alert("로그인 후 이용해주세요");
    router.push("/member/login");
    return;
  }

  const requestData = {
    authorId: store.getters.getMember.mbrId,
    nstdcCntnt: nstdcCntnt.value,
    dltChck: false, // 예시로 false로 설정
    useChck: true, // 예시로 true로 설정
  };

  axios
    .post(`/nestedComment/insert/${props.cmntSq}`, requestData)
    .then((response) => {
      console.log(response);
      alert("댓글 성공적으로 작성되었습니다.");
      router.go(0);
      nstdcCntnt.value = ""; // 작성 내용 초기화
    })
    .catch((error) => {
      console.error(error);
      alert("댓글 작성에 실패하였습니다.");
    });
};
</script>

<style scoped></style>

<template>
    <!-- 원래 BaseComment -->
    <div class="post-block mt-5 post-leave-comment">
      <h4 class="mb-3">댓글작성</h4>
      <div class="p-2">
        <div class="row">
          <div class="form-group col">
            <textarea
              class="form-control"
              maxlength="10000"
              v-model="cmntCntnt"
              id="cmntCntnt"
              rows="5"
              placeholder="내용을 입력하세요"
            ></textarea>
          </div>
        </div>
        <div class="row">
          <div class="form-group col mb-0">
            <button class="btn btn-primary" @click="submitComment()" :typeBoard = "true">작성</button>
          </div>
        </div>
      </div>
    </div>
    <!-- 원래 BaseComment -->
</template>

<script setup>

import { useRoute, useRouter } from "vue-router";
import { ref, computed, defineProps } from "vue";
import axios from "axios";
import store from "@/store";

const dltChck = ref("false");
const useChck = ref("true");
const cmntCntnt = ref("");
const route = useRoute();
const router = useRouter();
const brdSq = route.params.brdSq;
const isLoginMember = computed(() => store.getters.isLoginMember);

// eslint-disable-next-line no-unused-vars
const props = defineProps({
  typeBoard : {
    type : Boolean,
    default : true
  },
  answrSq: {
        type: Number,
        default: null,
        required: false,
    },
})

const submitComment = () => {
  console.log(cmntCntnt.value);
  if (cmntCntnt.value.trim() === "") {
    alert("내용을 확인하세요.");
    return;
  }

  if (!isLoginMember.value) {
    alert("로그인 후 이용해주세요");
    router.push("/member/login");
    return;
  }

  if(props.typeBoard === true) {
    axios
      .post(`/comment/insert/board/${brdSq}`, {
        authorId: store.getters.getMember.mbrId,
        cmntCntnt: cmntCntnt.value,
        dltChck: dltChck.value,
        useChck: useChck.value,
        brdSq: brdSq
      })
      .then((response) => {
        console.log(response);
        alert("댓글 성공적으로 작성되었습니다.");
        router.go(0);
        cmntCntnt.value = "";
      })
      .catch((error) => {
        console.error(error);
        alert("댓글 작성에 실패하였습니다.");
      });
  } else {
    axios
      .post(`/comment/insert/answer/${props.answrSq}`, {
        authorId: store.getters.getMember.mbrId,
        cmntCntnt: cmntCntnt.value,
        dltChck: dltChck.value,
        useChck: useChck.value,
        answrSq: props.answrSq
      })
      .then((response) => {
        console.log(response);
        alert("댓글 성공적으로 작성되었습니다.");
        router.go(0);
        cmntCntnt.value = "";
      })
      .catch((error) => {
        console.error(error);
        alert("댓글 작성에 실패하였습니다.");
      });
  }
};

</script>

<style scoped></style>

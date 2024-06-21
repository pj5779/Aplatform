<template>
    <div class="post-block mt-5 post-leave-comment">
        <h4 class="mb-3">답변작성</h4>
        <div class="p-2">
            <div class="row">
                <div class="form-group col">
                    <textarea class="form-control" maxlength="10000" v-model="answrCntnt" id="answrCntnt" rows="5" placeholder="내용을 입력하세요"></textarea>
                </div>
            </div>
            <div class="row">
                <div class="form-group col mb-0">
                    <button mb-3 btn btn-light @click="submitAnswer()">작성</button>
                </div>
            </div>
            
            <!-- 댓글 -->
            <div class="comments">
                <button class="mb-3" @click="toggleComments">댓글</button>
                <div v-if="showComments">
                    <div class="comment">
                        <div class="comment-block" v-for="(comment, idx) in board.commentEntity" :key="idx">
                            <div class="comment-block">
                                <span class="comment-by">
                                    <strong>{{ comment.insrtMbrSq.mbrId }}</strong>
                                    <span class="float-end">
                                        <button class="btn comment" @click="toggleReply(comment.cmntSq)">
                                            <i class="fas fa-reply" v-if="!comment.replying"></i>대댓글작성
                                        </button>
                                        <button class="btn delete" @click="deleteComment(comment.cmntSq)" v-if="isLoginMember && comment.insrtMbrSq.mbrId == store.getters.getMember.mbrId">
                                            <i class="fas fa-trash-alt"></i>
                                        </button>
                                    </span>
                                </span>
                                <span class="float-end">{{ formatDate(comment.insrtDtm) }}</span>
                                <p>{{ comment.cmntCntnt }}</p>
                            </div>
                            <BaseNestedComment :cmntSq="comment.cmntSq" v-if="comment.replying" />
                            <div class="comment-block" v-for="(nesItem, nesIdx) in comment.nestedCommentEntity" :key="nesIdx">
                                <img class="avatar" alt src="img/avatars/avatar-3.jpg" />
                                <span class="comment-by">
                                <strong>{{ nesItem.insrtMbrSq.mbrId }}</strong>
                                    <span class="float-end">
                                        <button
                                        class="btn delete"
                                        @click="deleteNestedComment(nesItem.nstdcSq)"
                                        v-if="
                                            isLoginMember &&
                                            nesItem.insrtMbrSq.mbrId == store.getters.getMember.mbrId
                                        "
                                        >
                                        <i class="fas fa-trash-alt"></i>
                                        </button>
                                    </span>
                                </span>
                                <span class="float-end">{{ formatDate(nesItem.insrtDtm) }}</span>
                                <p>{{ nesItem.nstdcCntnt }}</p>
                            </div>
                        </div>
                    </div>
                    <BaseComment :typeBoard = "false" />
                </div>
            </div>
            <!-- 댓글 -->

        </div>
    </div>
</template>

<script setup>
import BaseComment from "@/components/common/BaseComment.vue";
import BaseNestedComment from "@/components/common/BaseNestedComment.vue";
import { useRoute, useRouter } from "vue-router";
import { ref, onMounted, computed } from "vue";
import axios from "axios";
import store from "@/store";

const board = ref({
    brdSq: 0,
    brdTl: "",
    brdViews: 0,
    brdHits: 0,
    brdCtgryCd: "",
    brdCntnt: "",
    insrtMbrSq: "",
    commentEntity: [],
    answrEntity: [],
    cmntSq: "",
});

const route = useRoute();

const answrCntnt = ref("");
const brdSq = route.params.brdSq;
const router = useRouter();
const isLoginMember = computed(() => store.getters.isLoginMember);

const showComments = ref(true); // 초기 상태를 false로 설정하여 댓글을 숨김

const toggleComments = () => {
  showComments.value = !showComments.value; // 상태를 토글하여 보이기/숨기기 처리
};

const formatDate = (dateString) => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    return `${year}-${month}-${day}`;
};

const fetchBoardDetail = () => {
    const brdSq = route.params.brdSq;

    axios
        .get(`/board/detail/${brdSq}`)
        .then((response) => {
        board.value = response.data;
    })
        .catch((error) => {
        console.error("Error fetching board detail:", error);
    });
};

const submitAnswer = () => {
    if (answrCntnt.value.trim() === "") {
        alert("내용을 확인하세요.");
        return;
    }

    if (!isLoginMember.value) {
        alert("로그인 후 이용해주세요");
        router.push("/member/login");
        return;
    }

    axios
        .post(`/answer/insert/${brdSq}`, {
            answrCntnt: answrCntnt.value,
            answrSlctnChck: "false",
            authorId: store.getters.getMember.mbrId,
            brdSq: brdSq.value,
            dltChck: "false",
            useChck: "true",
        })
            .then(() => {
            alert("답변이 성공적으로 작성되었습니다.");
            answrCntnt.value = "";
            router.go(0);
        })
            .catch((error) => {
            console.error("답변 작성에 실패하였습니다:", error);
            alert("답변 작성에 실패하였습니다.");
        });
};

onMounted(() => {
    fetchBoardDetail();
});

</script>

<style scoped>

</style>

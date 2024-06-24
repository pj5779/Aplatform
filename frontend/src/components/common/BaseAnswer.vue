<template>
    <!-- 댓글 -->
    <div class="comments">
        <button class="mb-3 btn btn-light" @click="toggleAnswerComments">댓글</button>
        <div v-if="showAnswerComments">
            <div class="comment">
                <div class="comment-block" v-for="(answerComment, idx) in answer.commentEntity" :key="idx">
                    <div class="comment-block">
                        <span class="comment-by">
                            <strong>{{ answerComment.insrtMbrSq.mbrId }}</strong>
                            <span class="float-end">
                                <button class="btn comment" @click="toggleReply(answerComment.cmntSq)">
                                    <i class="fas fa-reply" v-if="!answerComment.replying"></i>대댓글작성
                                </button>
                                <button class="btn delete" @click="deleteComment(answerComment.cmntSq)" v-if="isLoginMember && answerComment.insrtMbrSq.mbrId == store.getters.getMember.mbrId">
                                    <i class="fas fa-trash-alt"></i>
                                </button>
                            </span>
                        </span>
                        <span class="float-end">{{ formatDate(answerComment.insrtDtm) }}</span>
                        <p>{{ answerComment.cmntCntnt }}</p>
                    </div>
                    <BaseNestedComment :cmntSq="answerComment.cmntSq" v-if="answerComment.replying" />
                    <div class="comment-block nestedComment-block" v-for="(nesItem, nesIdx) in answerComment.nestedCommentEntity" :key="nesIdx">
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
            <BaseComment :typeBoard = "false" :answrSq="answer.answrSq" />
        </div>
    </div>

</template>

<script setup>
import BaseComment from "@/components/common/BaseComment.vue";
import BaseNestedComment from "@/components/common/BaseNestedComment.vue";
import { useRouter } from "vue-router";
import { ref, computed, defineProps, onMounted } from "vue";
import axios from "axios";
import store from "@/store";

const props = defineProps({
    answrSq: {
        type: Number,
        default: null,
        required: false,
    },
});

const router = useRouter();

const answer = ref({
    answrSq: 0,
    insrtMbrSq: "",
    commentEntity: [],
    cmntSq: "",
});

// const route = useRoute();

// const answrCntnt = ref("");
// const brdSq = route.params.brdSq;
// const router = useRouter();
const isLoginMember = computed(() => store.getters.isLoginMember);

const showAnswerComments = ref(false); // 초기 상태를 false로 설정하여 댓글을 숨김

const toggleAnswerComments = () => {
  showAnswerComments.value = !showAnswerComments.value; // 상태를 토글하여 보이기/숨기기 처리
};

const formatDate = (dateString) => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    return `${year}-${month}-${day}`;
};

const fetchAnswerDetail = () => {
    const answrSq = props.answrSq;

    axios
        .get(`/answer/detail/${answrSq}`)
        .then((response) => {
        answer.value = response.data;
    })
        .catch((error) => {
        console.error("Error fetching answer detail:", error);
    });
};

const deleteComment = (cmntSq) => {
    axios.delete(`/comment/delete/${cmntSq}`).then((response) => {
        alert("댓글이 삭제 되었습니다");
        console.log("댓글이 삭제 되었습니다", response.data);
        router.go(0);
    });
};

const deleteNestedComment = (nstdcSq) => {
    axios.delete(`/nestedComment/delete/${nstdcSq}`).then((response) => {
        alert("댓글이 삭제 되었습니다");
        console.log("댓글이 삭제 되었습니다", response.data);
        router.go(0);
    });
};

const toggleReply = (cmntSq) => {
    // 토글 로직: 해당 댓글의 replying 상태를 토글합니다.
    const comment = answer.value.commentEntity.find(
        (item) => item.cmntSq === cmntSq
    );
    if (comment) {
        comment.replying = !comment.replying;
    }
};

onMounted(() => {
    fetchAnswerDetail();
});

</script>

<style scoped>
.btn.comment {
    color: #007bff;
}
.btn.delete {
    color: red;
}
.nestedComment-block {
    padding-left: 30px;
}
</style>

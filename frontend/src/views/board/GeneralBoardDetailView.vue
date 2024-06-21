<template>
  <section id="examples" class="section section-default">
    <div class="container">
      <section class="card card-admin">
        <header class="card-header">
          <div class="card-actions">
            <a href="#" class="card-action card-action-toggle" data-card-toggle=""></a>
            <a href="#" class="card-action card-action-dismiss" data-card-dismiss=""></a>
          </div>
          <h2 class="card-title">상세페이지</h2>
        </header>
      </section>

      <div class="row">
        <div class="col-md-8">
          <div class="mb-3">
            <label for="brdTl" class="form-label">제목</label>
            <input type="text" v-model="board.brdTl" class="form-control" id="brdTl" placeholder="제목" readonly/>
          </div>
        </div>
        <div class="col-md-4">
          <div class="col d-flex justify-content-end">
            <div class="me-3">
              <label class="form-label">작성자: {{ board.insrtMbrSq.mbrId }}</label>
            </div>
            <div class="me-3">
              <label class="form-label">등록일: {{ formatDate(board.insrtDtm) }}</label>
            </div>
            <div class="me-3">
              <label class="form-label">조회수: {{ board.brdHits }} </label>
            </div>
          </div>
        </div>
      </div>

      <div class="mb-3">
        <label for="brdCntnt" class="form-label">내용</label>
        <textarea
          class="form-control"
          v-model="board.brdCntnt"
          id="brdCntnt"
          rows="3"
          placeholder="내용"
          readonly
        ></textarea>
      </div>

      <div class="mb-3" v-if="board.fileName">
        <label class="form-label">첨부 파일: <a :href="downloadUrl" target="_blank">{{ board.fileName }}</a></label>
      </div>

      <div class="row">
        <div class="col d-flex justify-content-end">
          <button class="btn btn-success" @click="goUpdatePage" v-if="isAuthor(board.insrtMbrSq.mbrId)">수정</button>
          <button class="btn btn-danger" @click="confirmDelete" v-if="isAuthor(board.insrtMbrSq.mbrId)">삭제</button>
        </div>
      </div>

      <!-- 댓글 -->
      <div class="comments">
        <button class="mb-3" @click="toggleComments">
          댓글
        </button>
        <div v-if="showComments">
          <div class="comment">
            <div
              class="comment-block"
              v-for="(item, idx) in board.commentEntity"
              :key="idx"
            >
              <div class="comment-block">
                <span class="comment-by">
                  <strong>{{ item.insrtMbrSq.mbrId }}</strong>
                  <span class="float-end">
                    <button class="btn comment" @click="toggleReply(item.cmntSq)">
                      <i class="fas fa-reply" v-if="!item.replying"></i>
                      대댓글작성
                    </button>
                    <button
                      class="btn delete"
                      @click="deleteComment(item.cmntSq)"
                      v-if="
                        isLoginMember &&
                        item.insrtMbrSq.mbrId == store.getters.getMember.mbrId
                      "
                    >
                      <i class="fas fa-trash-alt"></i>
                    </button>
                  </span>
                </span>
                <span class="float-end">{{ formatDate(item.insrtDtm) }}</span>
                <p>{{ item.cmntCntnt }}</p>
              </div>
              <BaseNestedComment :cmntSq="item.cmntSq" v-if="item.replying" />
              <div
                class="comment-block"
                v-for="(nesItem, nesIdx) in item.nestedCommentEntity"
                :key="nesIdx"
              >
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
          <BaseComment />
        </div>
      </div>
      <!-- 댓글 -->

      <!-- 답변 -->
      <div v-if="board.brdCtgryCd === 'qna'">
        <div class="answers">
          <h4 class="mb-3">답변</h4>
          <div class="answer">
            <div class="answer-block" v-for="(anItem, anIdx) in board.answerEntity" :key="anIdx">
              <button
                class="btn answer"
                @click="selectionAnswer(anItem.answrSq)"
                v-if="
                  isLoginMember &&
                  anItem.insrtMbrSq.mbrId == store.getters.getMember.mbrId &&
                  board.brdAnswrSlctnChck == false
                "
              >
                채택하기
              </button>
              <span class="answer-by">
                <div
                  class="answerSelection"
                  v-if="anItem.answrSlctnChck == true"
                >
                  <span class="text-primary text-8 position-relative top-3 mt-3"
                    >채택완료 아이콘</span>
                  <span
                    class="font-weight-bold text-uppercase text-1 negative-ls-1 d-block text-dark pt-2"
                    >채택완료</span
                  >
                </div>
                <strong>{{ anItem.insrtMbrSq.mbrId }}</strong>
                <span class="float-end">
                  <button class="btn delete" @click="deleteAnswer(anItem.answrSq)" v-if="isLoginMember && anItem.insrtMbrSq.mbrId == store.getters.getMember.mbrId"><i class="fas fa-trash-alt"></i></button>
                </span>
              </span>
              <span class="float-end">{{ formatDate(anItem.insrtDtm) }}</span>
              <p>{{ anItem.answrCntnt }}</p>
            </div>
          </div>
          <BaseAnswer />
        </div>
      </div>
      <!-- 답변 -->

    </div>
  </section>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import axios from "axios";
import store from "@/store";
import { useRoute, useRouter } from "vue-router";
import BaseComment from "@/components/common/BaseComment.vue";
import BaseAnswer from "@/components/common/BaseAnswer.vue";
import BaseNestedComment from "@/components/common/BaseNestedComment.vue";

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
  fileName: '',
  filePath: '',
});
const router = useRouter();
const route = useRoute();

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

const deleteBoard = () => {
  const brdSq = board.value.brdSq;

  axios
    .delete(`/board/delete/${brdSq}`)
    .then((response) => {
      console.log("게시글이 삭제 되었습니다:", response.data);
      router.push("/board/list/general");
    })
    .catch((error) => {
      console.error("게시글 삭제가 실패 되었습니다:", error);
    });
};

const confirmDelete = () => {
  if (window.confirm("게시글을 삭제 하시겠습니까?")) {
    deleteBoard();
    alert("게시글이 삭제되었습니다.");
  }
};

const incrementHits = () => {
  const brdSq = board.value.brdSq;

  axios
    .post(`/board/detail/${brdSq}/hits`)
    .then((response) => {
      console.log("조회수가 증가되었습니다.");
      board.value.brdHits++;
      console.log(response.data);
    })
    .catch((error) => {
      console.error("조회수 증가에 실패하였습니다:", error);
    });
};

const goUpdatePage = () => {
  router.push(`/board/update/${board.value.brdSq}`);
};

const isAuthor = (authorId) => {
  const currentUser = JSON.parse(sessionStorage.getItem("member"));
  return currentUser && currentUser.mbrId === authorId;
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
  const comment = board.value.commentEntity.find(
    (item) => item.cmntSq === cmntSq
  );
  if (comment) {
    comment.replying = !comment.replying;
  }
};

const isLoginMember = computed(() => store.getters.isLoginMember);

const downloadUrl = computed(() => {
  return `/board/download/${board.value.brdSq}`;
});

const deleteAnswer = (answrSq) => {
  axios.delete(`/answer/delete/${answrSq}`).then((response) => {
    alert("답변이 삭제 되었습니다");
    console.log("답변이 삭제 되었습니다", response.data);
    router.go(0);
  });
};

const selectionAnswer = (answrSq) => {
  axios
    .post(`/answer/selection/${answrSq}`)
    .then((response) => {
      alert("채택이 완료되었습니다.");
      console.log("채택이 완료되었습니다.", response.data);
      router.go(0);
    })
    .catch((error) => {
      alert("이미 채택된 답변이 있습니다.");
      console.log("이미 채택된 답변이 있습니다.", error);
    });
};

onMounted(() => {
  fetchBoardDetail();
  incrementHits();
});

</script>

<style scoped>
.btn.comment {
  color: #007bff;
}

.answers {
  border: 3px dashed grey;
  padding: 50px;
  margin: 30px;
  border-radius: 40px;
}

.btn.delete {
  color: red;
}
</style>

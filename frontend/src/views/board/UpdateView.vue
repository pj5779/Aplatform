<template>
  <section id="examples" class="section section-default">
    <div class="container">
      <section class="card card-admin">
        <header class="card-header">
          <div class="card-actions">
            <a href="#" class="card-action card-action-toggle" data-card-toggle=""></a>
            <a href="#" class="card-action card-action-dismiss" data-card-dismiss=""></a>
          </div>
          <h2 class="card-title">일반게시판 글 수정</h2>
        </header>
      </section>

      <div class="mb-3">
        <label for="brdTl" class="form-label">제목</label>
        <input type="text" v-model="brdTl" class="form-control" id="brdTl" placeholder="제목을 입력하세요">
      </div>
      <div class="mb-3">
        <label for="brdCntnt" class="form-label">내용</label>
        <textarea class="form-control" v-model="brdCntnt" id="brdCntnt" rows="3" placeholder="내용을 입력하세요"></textarea>
      </div>
      <div>
        <button class="btn btn-success" @click="updateBoard">수정</button>
      </div>
    </div>
  </section>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router'; 


const brdTl = ref('');
const brdCntnt = ref('');
const brdCtgryCd = ref('');

const route = useRoute();
const router = useRouter(); 
const brdSq = route.params.brdSq;


const fetchBoardDetail = () => {
  axios.get(`/board/detail/${brdSq}`)
    .then(response => {
      brdTl.value = response.data.brdTl;
      brdCntnt.value = response.data.brdCntnt;
      brdCtgryCd.value = response.data.brdCtgryCd;
    })
    .catch(error => {
      console.error('게시글 상세 정보를 불러오는 중 오류가 발생했습니다:', error);
    });
};

onMounted(() => {
  fetchBoardDetail();
});

const updateBoard = () => {
  const updatedData = {
    brdTl: brdTl.value,
    brdCntnt: brdCntnt.value,
    brdSq: brdSq
  };

  axios.post(`/board/update/${brdSq}`, updatedData)
    .then(response => {
      alert('게시글이 수정되었습니다.');

      router.push(`/board/list/${brdCtgryCd.value}`);

      console.log(response.data);
    })
    .catch(error => {
      console.error('게시글 수정이 실패하였습니다:', error);
    });
};
</script>

<style>

</style>

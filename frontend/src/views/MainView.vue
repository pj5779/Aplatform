<template>
  
    <div class="container-fluid d-flex">
      <div class="w-50 main-column">
        <div class="mainGnrl p-2">
          <router-link to="/board/list/general"><h4>일반게시판</h4></router-link>
          <ul class="list list-icons list-primary list-borders">
            <GeneralBoardList :showCategory="false" :showDate="true" :showSearch="false" :showPagination="false"  formatType="relative"/>
          </ul>
        </div>
      </div>
      <div class="w-50 d-flex flex-column">
        <div class="mainFdb p-2 flex-grow-1">
          <router-link to="/board/list/feedback"><h4>피드백게시판</h4></router-link>
          <ul class="list list-icons list-primary list-borders">
            <FeedbackBoardList :showCategory="false" :showDate="true" :showSearch="false" :showPagination="false"  formatType="relative"/>
          </ul>
        </div>
        <div class="mainQna p-2 flex-grow-1">
          <router-link to="/board/list/qna"><h4>Q&A게시판</h4></router-link>
          <ul class="list list-icons list-primary list-borders">
            <QnABoardList :showCategory="false" :showDate="true" :showSearch="false" :showPagination="false"  formatType="relative" />
          </ul>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import GeneralBoardList from '../components/board/GeneralBoardList.vue';
  import FeedbackBoardList from '../components/board/FeedbackBoardList.vue';
  import QnABoardList from '../components/board/QnABoardList.vue';
  </script>
  
  <style scoped>
  .container-fluid {
    width: 80%;
    min-height: 100vh;
    max-height: 100vh;
  }
  
  .mainGnrl .mainFdb .mainQna {
    overflow: hidden;
  }

  </style>
  
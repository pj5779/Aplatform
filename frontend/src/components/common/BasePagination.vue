<template>
    <ul class="pagination float-end">
      <li class="page-item" v-for="page in totalPages" :key="page" :class="{ active: page === currentPage }"> 
        <!--totlaPages 만큼의 페이지 번호를 반복하고 각 페이지 번호를 클릭할때 goToPage 함수 호출
        현재 페이지인 경우에는 active클래스를 부여해 현재 페이지임을 표시-->
        <a class="page-link" href="#" @click="goToPage(page)">{{ page }}</a>
      </li>
    </ul>
  </template>
  
  <script>
  
  export default {
    props: {
      currentPage: Number, //현재 페이지 번호
      totalPages: Number, //전체 페이지 수
      goToPage: Function //페이지 이동 함수
    }
  };
  </script>
  
  <style scoped>
  
  </style>
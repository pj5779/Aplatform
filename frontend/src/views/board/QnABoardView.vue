<template>
   <section class="page-header page-header-modern bg-color-grey page-header-md">
      <div class="container">
         <div class="row">
            <div class="col-md-12 align-self-center p-static order-2 text-center">
               <h1 class="text-dark font-weight-bold text-8">Q&A게시판</h1>
               <span class="sub-title text-dark">무엇이든 물어보세요!</span>
            </div>
            <div class="col-md-12 align-self-center order-1">
               <ul class="breadcrumb d-block text-center">
                  <li><a href="/">Home</a></li>
                  <li class="active">Q&A게시판</li>
               </ul>
            </div>
         </div>
      </div>
   </section>
       <section class="card card-admin">
         <div class="container py-4">
            <div class="d-flex justify-content-end">
               <router-link
                  to="/board/insert/qna"
                  class="btn btn-primary"
                  v-if="isLoginMember"
                  >등록</router-link
               >
               <router-link
                  to="/member/login"
                  class="btn btn-primary"
                  @click="checkLogin()"
                  v-else
                  >등록</router-link
               >
            </div>
            <div class="row justify-content-center">
               <QnABoardList />
               <BasePagination :currentPage="currentPage" :totalPages="totalPages" :goToPage="goToPage" />
          </div>
          </div>
       </section>
   </template>
   
   <script setup>
   import QnABoardList from '../../components/board/QnABoardList.vue';
   import BasePagination from "../../components/common/BasePagination.vue";
   import { computed } from 'vue';
   import store from '@/store';

   const isLoginMember =  computed( () => (store.getters.isLoginMember)); // 로그인 상태여부 확인
   
   const checkLogin = () => {
      alert("로그인 후 이용해주세요");
   };
   </script>
   
   <style scoped>
   
   </style>
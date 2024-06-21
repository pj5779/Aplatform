<template>
  <header id="header" class="header-effect-shrink" data-plugin-options="{'stickyEnabled': true, 'stickyEffect': 'shrink', 'stickyEnableOnBoxed': true, 'stickyEnableOnMobile': false, 'stickyChangeLogo': true, 'stickyStartAt': 30, 'stickyHeaderContainerHeight': 70}" style="height: 101px;">
                  <div class="header-body header-body-bottom-border border-top-0" style="height: auto;">
                      <div class="header-container container" style="height: 100px;">
                          <div class="header-row">
                              <div class="header-column">
                                  <div class="header-row">
                                      <div class="header-logo" style="width: 100px; height: 48px;">
                                          <a href="/">
                                              <img alt="Porto" width="100" height="48" data-sticky-width="82" data-sticky-height="40" src="img/logo-default-slim.png">
                                          </a>
                                      </div>
                                  </div>
                              </div>
                              <div class="header-column justify-content-end">
                                  <div class="header-row">
                                      <div class="header-nav header-nav-line header-nav-bottom-line">
                                          <div class="header-nav-main header-nav-main-square header-nav-main-dropdown-no-borders header-nav-main-effect-2 header-nav-main-sub-effect-1">
                                              <nav class="closed collapse" style="">
                                                  <ul class="nav nav-pills" id="mainNav">
                                                      <li class="dropdown">
                                                        <!-- 이 부분 수정해야함 커뮤니티 링크로 -->
                                                          <a class="dropdown-item dropdown-toggle" href="/">
                                                              커뮤니티
                                                          <i class="fas fa-chevron-down"></i></a>
                                                          <ul class="dropdown-menu">
                                                              <li>
                                                                  
                                                                  <router-link to="/board/list/general" a class="dropdown-item">
                                                                      일반게시판
                                                                  </router-link>
                                                              
                                                              </li>
                                                              <li>
                                                                  <router-link to="/board/list/feedback" a class="dropdown-item">
                                                                      피드백게시판
                                                                  </router-link>
                                                              
                                                              </li>
                                                              <li>
                                                                  <router-link to="/board/list/qna" a class="dropdown-item">
                                                                      Q&A게시판
                                                                  </router-link>
                                                              
                                                              </li>
                                                          </ul>
                                                      </li>

                                                      <li class="dropdown">
                                                          <a v-if="isLoginMember">
                                                            {{store.state.member.mbrName}}
                                                          </a>
                                                     
                                                          <ul class="dropdown-menu">
                                                              <li>
                                                                  
                                                                  <router-link to="/member/mypage" a class="dropdown-item">
                                                                      마이페이지
                                                                  </router-link>
                                                              
                                                              </li>
                                                          </ul>
                                                      </li>
                                                      <li class="dropdown">
                                                          
                                                          <router-link to="/member/login" class="dropdown-item dropdown-toggle" v-if="!isLoginMember">
                                                              로그인</router-link>
                                                          <router-link to="/" class="dropdown-item dropdown-toggle" @click="logout()" v-else>
                                                            로그아웃</router-link>
                                                      </li>
                                                     
                                                      <li class="dropdown">
                                                          <router-link to="/member/register" a class="dropdown-item dropdown-toggle" v-if="!isLoginMember">
                                                              회원가입
                                                          <i class="fas fa-chevron-down"></i></router-link>
                                                      </li>
                                  
                                                      
                                                  </ul>
                                              </nav>
                                          </div>
                                          <button class="btn header-btn-collapse-nav collapsed" data-bs-toggle="collapse" data-bs-target=".header-nav-main nav" aria-expanded="false">
                                              <i class="fas fa-bars"></i>
                                          </button>
                                      </div>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
              </header>
  </template>
  
  <script setup>
  
 import { router } from '@/router';
 import { computed } from 'vue';
 import store from '@/store';
 import axios from "axios";


  const isLoginMember =  computed( () => (store.getters.isLoginMember)); // 로그인 상태여부 확인

  const logout = () => {
    axios.post("/member/logout")
        .then(res => {
            console.log('로그아웃 성공:', res.data);
            store.commit('clearMember');
            sessionStorage.removeItem("member");
            alert(res.data); // 백엔드에서 온 메시지를 alert로 표시
            router.push("/");
        })
        .catch(error => {
            console.error('로그아웃 실패:', error);
            alert("로그아웃 중 문제가 발생했습니다.");
        });
        
    

  }

  
  
  </script>
  
  <style>
  
  </style>
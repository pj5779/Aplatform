<template>
    <div role="main" class="main">
      <section class="page-header page-header-modern bg-color-grey page-header-lg">
        <!-- 페이지 헤더 -->
        <div class="container">
          <div class="row">
            <div class="col-md-12 align-self-center p-static order-2 text-center">
              <h1 class="font-weight-bold text-dark">마이페이지</h1>
            </div>
          </div>
        </div>
      </section>
  
      <!-- 사용자 정보 표시 및 수정 폼 -->
      <div class="container py-4">
        <div class="row justify-content-center">
          <div class="col-md-6 col-lg-5">
            
            <!-- 이름 출력 -->
            <div class="row">
              <div class="form-group col">
                <label class="text-color-dark text-3"> 이름</label>
                <input type="text" v-model="editMember.mbrName" class="form-control form-control-lg text-4" required :placeholder="editMember.mbrName" disabled>
              </div>
            </div>
  
            <!-- 아이디 출력 -->
            <div class="row">
              <div class="form-group col">
                <label class="text-color-dark text-3"> 아이디</label>
                <input type="text" v-model="editMember.mbrId" class="form-control form-control-lg text-4" required :placeholder="editMember.mbrId" disabled>
              </div>
            </div>
  
            <!-- 이메일 출력 -->
            <div class="row">
              <div class="form-group col">
                <label class="text-color-dark text-3"> 이메일</label>
                <input type="email" v-model="editMember.mbrEmlAdrs" class="form-control form-control-lg text-4" required :placeholder="editMember.mbrEmlAdrs" :disabled="!editMode" @input="validateEmail">
                <div v-if="errorMbrEmlAdrs" class="error">{{ errorMbrEmlAdrs }}</div>
              </div>
            </div>
  
            <!-- 성별 출력 -->
            <div class="row">
              <div class="form-group col">
                <label class="text-color-dark text-3"> 성별</label>
                <input type="text" v-model="editMember.gndrCtgryCd" class="form-control form-control-lg text-4" required :placeholder="editMember.gndrCtgryCd" disabled>
              </div>
            </div>
  
            <!-- 생년월일 출력 -->
            <div class="row">
              <div class="form-group col">
                <label class="text-color-dark text-3"> 생년월일</label>
                <input type="text" v-model="editMember.mbrBd" class="form-control form-control-lg text-4" required :placeholder="editMember.mbrBd" disabled>
              </div>
            </div>
  
            <!-- 번호 출력 -->
            <div class="row">
              <div class="form-group col">
                <label class="text-color-dark text-3"> 번호</label>
                <input type="text" v-model="editMember.mbrMp" class="form-control form-control-lg text-4" required :placeholder="editMember.mbrMp" :disabled="!editMode" @input="validateMp">
                <div v-if="errorMbrMp" class="error">{{ errorMbrMp }}</div>
              </div>
            </div>
  
            <!-- 수정 모드 전환 및 저장 버튼 -->
            <div class="row">
              <div class="form-group col">
                <button class="btn btn-primary w-100 mb-3" @click="enableEditMode" v-if="!editMode">수정하기</button>
                <button class="btn btn-primary w-100 mb-3" @click="updateMember" v-if="editMode">저장하기</button>
              </div>
            </div>
  
            <!-- 회원 탈퇴 버튼 -->
            <div class="row">
              <div class="form-group col">
                <button class="btn btn-primary w-100 mb-3" @click="deleteMember">탈퇴하기</button>
              </div>
            </div>
          
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, computed } from 'vue';
  import axios from 'axios';
  import store from '@/store';
  import { router } from '@/router';
  
  const editMode = ref(false);
  const storeMember = computed(() => store.state.member);
  const editMember = ref({ ...storeMember.value });
  
  const errorMbrEmlAdrs = ref('');
  const errorMbrMp = ref('');
  
  const enableEditMode = () => {
    console.log("수정시도");
    editMode.value = true;
  };
  
  const validateEmail = () => {
    errorMbrEmlAdrs.value = '';
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!emailPattern.test(editMember.value.mbrEmlAdrs)) {
      errorMbrEmlAdrs.value = '이메일 형식이 올바르지 않습니다.';
    }
  };
  
  const validateMp = () => {
    errorMbrMp.value = '';
    const mpPattern = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;
    const mpLengthPattern = /^01\d{8,9}$/;
  
    if (!mpPattern.test(editMember.value.mbrMp) || !mpLengthPattern.test(editMember.value.mbrMp)) {
      errorMbrMp.value = '휴대폰 번호 형식이 올바르지 않습니다.';
    }
  };
  
  const updateMember = async () => {
    validateEmail();
    validateMp();
  
    if (errorMbrEmlAdrs.value) {
      alert("이메일 형식을 확인해주세요.");
      return;
    }
    if (errorMbrMp.value) {
      alert("휴대폰 번호 형식을 확인해주세요.");
      return;
    }
  
    try {
      const res = await axios.put("/member/update", editMember.value);
      console.log("사용자 정보 업데이트 성공", res.data);
      store.commit('updateMemberInfo', res.data);
      sessionStorage.setItem('member', JSON.stringify(res.data));
      alert("사용자 정보 업데이트 완료");
      editMode.value = false;
    } catch (error) {
      console.error("사용자 정보 업데이트 실패:", error);
      alert("사용자 정보를 업데이트하는 중에 오류가 발생했습니다.");
    }
  };
  
  const deleteMember = async () => {
    try {
      const res = await axios.put("/member/delete", editMember.value);
      console.log("회원 탈퇴 완료", res.data);
      alert("회원 탈퇴가 완료되었습니다. 로그아웃 됩니다.");
   
      editMember.value = {};
      logout();
      
      // await logout();
      
    } catch (error) {
      console.error("회원 탈퇴 실패:", error);
      if (error.response) {
        alert(error.response.data);
      } else {
        alert("회원 탈퇴 중에 오류가 발생했습니다.");
      }
    }
  };
  
  const logout = async () => {
    await store.commit('clearMember');
    sessionStorage.removeItem("member");
    router.push("/");
  };
  
  </script>
  
  <style scoped>
  
  </style>
  
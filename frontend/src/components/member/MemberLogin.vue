<template>
    <section class="page-header page-header-modern bg-color-grey page-header-lg">
        <div class="container">
            <div class="row">
                <div class="col-md-12 align-self-center p-static order-2 text-center">
                    <h1 class="font-weight-bold text-dark">로그인</h1>
                </div>
            </div>
        </div>
    </section>

    <section class="loginSection">
        <div class="card mb-4">
            <div class="card-body">
                <div class="row">
                    <div class="col">
                        <div class="contact-form form-style-3">
                            <div class="contact-form-success alert alert-success d-none mt-4">
                                <strong>Success!</strong> Your message has been sent to us.</div>
                                <div class="contact-form-error alert alert-danger d-none mt-4">
                                    <strong>Error!</strong>There was an error sending your message.<span class="mail-error-message text-1 d-block"></span>
                                </div>

                                <!-- 로그인 폼 시작-->
                                <form @submit.prevent="submit">
                                    <div class="row">
                                        <div class="form-group col">
                                        <label class="form-label mb-1 text-2">아이디</label>
                                            <input type="text" value="" data-msg-required="Please enter the subject." maxlength="100" 
                                        class="form-control text-3 h-auto py-2" name="id" v-model="state.form.mbrId" placeholder="아이디를 입력해주세요" autoComplete="off" @focus="handleFocus" @blur="handleBlur">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col">
                                        <label class="form-label mb-1 text-2">비밀번호</label>
                                            <input type="password" value="" data-msg-required="Please enter the subject." maxlength="100" 
                                        class="form-control text-3 h-auto py-2" name="pwd" placeholder="비밀번호를 입력해주세요"  autoComplete="off" v-model="state.form.mbrPswrd" @focus="handleFocus" @blur="handleBlur">
                                        </div>
                                    </div>
                                    <div class="d-flex justify-content-center">
                                        <input type="submit" value="로그인" class="btn btn-primary w-100 mb-3" data-loading-text="Loading...">
                                    </div>
                                </form>
                                <!-- 로그인 폼 끝-->


                                <ul class="d-flex justify-content-between nav nav-pills" id="mainNav">
                                    <li class="dropdown pe-5"><router-link to="/member/register" class="dropdown-item "> 회원가입</router-link></li>
                                    <li><router-link to="/member/find" class="dropdown-item">아이디, 비밀번호 찾기</router-link></li>
                                </ul>
                                <hr/>
                            <div class="align-self-center">
                                <ul class="d-flex justify-content-center nav nav-pills" id="mainNav">
                                    <li class="dropdown"><a href="#" @click="naverLogin" class="dropdown-item"><img :src="require('@/assets/naverlogo/btnG_아이콘원형.png')" class="socialLogo"></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
<!-- <MemberLoginNCallback /> -->
</template>

<script setup>

import axios from "axios";
import { reactive } from "vue";
import { router } from "@/router";
import store from "@/store";
// import MemberLoginNCallback from "./MemberLoginNCallback.vue";

const state = reactive({
    form :{
        mbrId:"",  
        mbrPswrd:"",
    }}
)

const handleFocus = (event) => {
    event.target.placeholder = '';
};

const handleBlur = (event) => {
    event.target.placeholder = event.target.name === 'id' ? '아이디를 입력해주세요' : '비밀번호를 입력해주세요';
};

const submit = () => {
    axios.post("/member/login", state.form).then(async (res) => {
        
        console.log("통신완료");
        store.commit("setMember", res.data);    // 스토어에 사용자 정보 저장
        sessionStorage.setItem("member", JSON.stringify(res.data)); // 세션에 유저 정보 저장
        await router.push("/");
        alert("로그인 성공");

    }).catch((error) => {

        // 서버가 실패 응답코드 반환한 경우
        if(error.response && error.response.data){
            alert("로그인 실패:"+ error.response.data.message)
        } else {
        // 서버가 응답을 반환하지 않은 경우
        alert("로그인 실패: 서버와 통신할 수 없습니다.");
        }
    });
};

// https://henniee.tistory.com/m/238 링크 참조
const client_id = 'TIwA7WnbAvnjEwnbPGZm';   // 본인 또는 회사 아이디로 교체
const redirect_uri = encodeURIComponent('http://localhost:8080/member/loginNCallback');

// state 난수 생성
const generateRandomState = () => {
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let state = '';
    for (let i = 0; i < 16; i++) {
        const randomIndex = Math.floor(Math.random() * characters.length);
        state += characters.charAt(randomIndex);
    }
    return state;
};

const naverLogin = async () => {
    const state = generateRandomState();
    const apiURL = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${client_id}&redirect_uri=${redirect_uri}&state=${state}`;
    window.location.href = apiURL;
};

</script>

<style>
.loginSection{
    display: flex;
    justify-content: center;
    align-items: center;
}
.socialLogo {
    max-width: 45px;
    height: auto;
}
</style>
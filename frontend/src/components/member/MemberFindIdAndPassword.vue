<template>
    <div role="main" class="main">

        <section class="page-header page-header-modern bg-color-grey page-header-lg">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 align-self-center p-static order-2 text-center">
                        <h1 class="font-weight-bold text-dark">아이디, 비밀번호 찾기</h1>
                    </div>
                </div>
            </div>
        </section>

        <div id="examples" class="container py-2">
            <div class="row justify-content-center">
                <div class="col-lg-6">
                    <div class="tabs">
                        <ul class="nav nav-tabs nav-justified flex-column flex-md-row">
                            <li class="nav-item">
                                <a class="nav-link" :class="{ 'active': currentTab === 'findId' }" @click="switchTab('findId')">아이디 찾기</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" :class="{ 'active': currentTab === 'findPw' }" @click="switchTab('findPw')">비밀번호 찾기</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <!-- 아이디 찾기 -->
                            <div id="popular10" class="tab-pane active" v-show="currentTab === 'findId'">
                                <form @submit.prevent="submitForm('findId')" id="findId">
                                    <div class="row">
                                        <div class="form-group col">
                                            <label class="form-label text-color-dark text-3">이름 <span class="text-color-danger">*</span></label>
                                            <input type="text" v-model="mbrName" @blur="touched.mbrName = true" class="form-control form-control-lg text-4" required>
                                            <div v-if="touched.mbrName && errorMbrName" class="error">{{ errorMbrName }}</div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-9">
                                            <label class="form-label text-color-dark text-3">이메일 <span class="text-color-danger">*</span></label>
                                            <input type="email" v-model="mbrEmlAdrs" @blur="touched.mbrEmlAdrs = true" class="form-control form-control-lg text-4" required>
                                            <div v-if="touched.mbrEmlAdrs && errorMbrEmlAdrs" class="error">{{ errorMbrEmlAdrs }}</div>
                                        </div>
                                        <div class="form-group col-3">
                                            <div class="form-label text-color-dark text-3">&nbsp;</div>
                                            <button type="button" v-on:click="emlSend" class="btn btn-quaternary mb-2">인증요청</button>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-9">
                                            <label class="form-label text-color-dark text-3">인증 코드 <span class="text-color-danger">*</span></label>
                                            <input type="text" v-model="emailKey" @input="updateEmailCheck" @blur="touched.emailKey = true" class="form-control form-control-lg text-4" required>
                                        </div>
                                        <div class="form-group col-3">
                                            <div class="form-label text-color-dark text-3">&nbsp;</div>
                                            <button type="button" v-on:click="emlChck" class="btn btn-quaternary mb-2">확인</button>
                                        </div>
                                        <div v-if="touched.emailKey && errorMbrEmlChck" class="error">{{ errorMbrEmlChck }}</div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col">
                                            <button type="submit" class="btn btn-dark btn-modern w-100 text-uppercase rounded-0 font-weight-bold text-3 py-3" data-loading-text="Loading...">아이디 조회</button>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <!-- 비밀번호 찾기 -->
                            <div id="recent10" class="tab-pane active" v-show="currentTab === 'findPw'">
                                <form @submit.prevent="submitForm('findPw')" id="findPw">
                                    <div class="row">
                                        <div class="form-group col">
                                            <label class="form-label text-color-dark text-3">아이디 <span class="text-color-danger">*</span></label>
                                            <input type="text" v-model="mbrId" @blur="touched.mbrId = true" class="form-control form-control-lg text-4" required placeholder="영문, 숫자를 포함한 4자 이상 20자 이내">
                                            <div v-if="touched.mbrId && errorMbrId" class="error">{{ errorMbrId }}</div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col">
                                            <label class="form-label text-color-dark text-3">이름 <span class="text-color-danger">*</span></label>
                                            <input type="text" v-model="mbrName" @blur="touched.mbrName = true" class="form-control form-control-lg text-4" required>
                                            <div v-if="touched.mbrName && errorMbrName" class="error">{{ errorMbrName }}</div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-9">
                                            <label class="form-label text-color-dark text-3">이메일 <span class="text-color-danger">*</span></label>
                                            <input type="email" v-model="mbrEmlAdrs" @blur="touched.mbrEmlAdrs = true" class="form-control form-control-lg text-4" required>
                                            <div v-if="touched.mbrEmlAdrs && errorMbrEmlAdrs" class="error">{{ errorMbrEmlAdrs }}</div>
                                        </div>
                                        <div class="form-group col-3">
                                            <div class="form-label text-color-dark text-3">&nbsp;</div>
                                            <button type="button" v-on:click="emlSend" class="btn btn-quaternary mb-2">인증요청</button>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-9">
                                            <label class="form-label text-color-dark text-3">인증 코드 <span class="text-color-danger">*</span></label>
                                            <input type="text" v-model="emailKey" @input="updateEmailCheck" @blur="touched.emailKey = true" class="form-control form-control-lg text-4" required>
                                        </div>
                                        <div class="form-group col-3">
                                            <div class="form-label text-color-dark text-3">&nbsp;</div>
                                            <button type="button" v-on:click="emlChck" class="btn btn-quaternary mb-2">확인</button>
                                        </div>
                                        <div v-if="touched.emailKey && errorMbrEmlChck" class="error">{{ errorMbrEmlChck }}</div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col">
                                            <button type="submit" class="btn btn-dark btn-modern w-100 text-uppercase rounded-0 font-weight-bold text-3 py-3" data-loading-text="Loading...">비밀번호 조회</button>
                                        </div>
                                    </div>
                                </form>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</template>

<script setup>
import { ref, reactive, watchEffect } from 'vue';
import { useRouter } from 'vue-router';
import { sendEmlFind, findMbrId, findMbrPswrd } from '@/api/member';
import axios from "axios";
import store from "@/store";

const mbrId = ref('');
const mbrName = ref('');
const mbrEmlAdrs = ref('');

const emailKey = ref('');
const emailCode = ref('');

const errorMbrId = ref('');
const errorMbrName = ref('');
const errorMbrEmlAdrs = ref('');
const errorMbrEmlChck = ref('');

const router = useRouter();

const touched = reactive({
    mbrId: false,
    mbrName: false,
    mbrEmlAdrs: false,
    emailKey: false,
});

// 현재 활성화된 탭을 저장하는 상태 변수
const currentTab = ref('findId');

// touched 상태를 초기화하는 함수
const resetTouched = () => {
    mbrId.value = '';
    mbrName.value = '';
    mbrEmlAdrs.value = '';
    emailKey.value = '';
    emailCode.value = '';
    errorMbrId.value = '';
    errorMbrName.value = '';
    errorMbrEmlAdrs.value = '';
    errorMbrEmlChck.value = '';

    // touched 객체의 모든 필드를 false로 초기화
    Object.keys(touched).forEach(key => touched[key] = false);
};

// 탭 전환 함수
const switchTab = (tabName) => {
    currentTab.value = tabName;
    resetTouched(); // 탭을 전환할 때 폼 입력 값 초기화
};

const emlSend = async () => {
    emailCode.value = '';

    try {
        const response = await sendEmlFind(mbrEmlAdrs.value);
        if (!/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/.test(mbrEmlAdrs.value)) {
            errorMbrEmlAdrs.value = '이메일 형식이 올바르지 않습니다.';
        } else if (response.data.exist) {
            alert(response.data.exist);
        } else if (response.status === 200) {
            alert('인증코드가 발송되었습니다.');
            const key = response.data.key;
            alert(key);
            emailCode.value = key;
        } else {
            alert('잘못된 이메일입니다');
        }
    } catch (error) {
        console.error('이메일 전송 중 오류 발생:', error);
        alert('이메일 전송 중 오류가 발생했습니다.');
    }
};

const updateEmailCheck = (event) => {
    emailKey.value = event.target.value;
};

const emlChck = () => {
    if (emailCode.value === emailKey.value) {
        alert('인증완료');
    } else {
        alert('인증코드가 일치하지 않습니다.');
    }
};

watchEffect(() => {
    if (currentTab.value === 'findId') {
        errorMbrId.value = '';
    } else {
        if (!/^[A-Za-z0-9]{4,20}$/.test(mbrId.value)) {
            errorMbrId.value = '아이디 형식이 올바르지 않습니다. (영문, 숫자를 포함한 4자 이상 20자 이내)';
        } else {
            errorMbrId.value = '';
        }
    }

    if (!/^[A-Za-z가-힣]{1,6}$/.test(mbrName.value)) {
        errorMbrName.value = '이름 형식이 올바르지 않습니다.';
    } else {
        errorMbrName.value = '';
    }

    if (!/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/.test(mbrEmlAdrs.value)) {
        errorMbrEmlAdrs.value = '이메일 형식이 올바르지 않습니다.';
    } else {
        errorMbrEmlAdrs.value = '';
    }

    if (emailCode.value && emailKey.value !== emailCode.value) {
        errorMbrEmlChck.value = '인증코드가 일치하지 않습니다.';
    } else {
        errorMbrEmlChck.value = '';
    }
});

const submitForm = async (formId) => {
    if (Object.values(touched).some(field => field === false)) {    // touched된 값들을 배열로 반환하여 false일 경우
        Object.keys(touched).forEach(key => touched[key] = true);   // 사용자가 필드를 터치(포커스)하면 해당 필드의 touched 상태를 true로 설정
    }

    if (
        !errorMbrId.value &&
        !errorMbrName.value &&
        !errorMbrEmlAdrs.value &&
        !errorMbrEmlChck.value
    ) {
        const memberData = {
            mbrName: mbrName.value,
            mbrEmlAdrs: mbrEmlAdrs.value,
        };
        if (formId === 'findPw') {
            memberData.mbrId = mbrId.value;
        }

        try {
            if (formId === 'findId') {
                const response = await findMbrId(memberData);
                if (response.status === 200) {
                    alert(response.data);
                    router.push('/member/login');
                } else {
                    alert(response.data);
                }
            } else if (formId === 'findPw') {
                const response = await findMbrPswrd(memberData);
                if (response.status === 200) {
                    // alert(response.data);
                    // router.push('/member/login');
                    
                    const mbrId = memberData.mbrId;
                    const mbrPswrd = response.data;
                    axios.post("/member/login", { mbrId, mbrPswrd }, {
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }).then(async (res) => {
                        console.log("통신완료");
                        store.commit("setMember", res.data);    // 스토어에 사용자 정보 저장
                        sessionStorage.setItem("member", JSON.stringify(res.data)); // 세션에 유저 정보 저장
                        await router.push("/member/mypage");
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

                } else {
                    alert(response.data);
                }


            }
        } catch (error) {
            alert('Failed to find member: ' + error.message);
        }
    }
};

</script>

<style scoped>
    .error {
        color: red;
    }
</style>
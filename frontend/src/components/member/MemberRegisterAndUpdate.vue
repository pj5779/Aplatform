<template>
    <div role="main" class="main">

        <section class="page-header page-header-modern bg-color-grey page-header-lg">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 align-self-center p-static order-2 text-center">
                        <h1 class="font-weight-bold text-dark">회원가입</h1>
                    </div>
                </div>
            </div>
        </section>

        <div class="container py-4">

            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-5">
                    <!-- <h2 class="font-weight-bold text-5 mb-0">회원가입</h2> -->
                    <form @submit.prevent="submitForm" id="frmSignUp">
                        <div class="row">
                            <div class="form-group col">
                                <label class="form-label text-color-dark text-3">아이디 <span class="text-color-danger">*</span></label>
                                <input type="text" v-model="mbrId" @blur="touched.mbrId = true" class="form-control form-control-lg text-4" required placeholder="영문, 숫자를 포함한 4자 이상 20자 이내">
                                <div v-if="touched.mbrId && errorMbrId" class="error">{{ errorMbrId }}</div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col">
                                <label class="form-label text-color-dark text-3">비밀번호 <span class="text-color-danger">*</span></label>
                                <input type="password" v-model="mbrPswrd" @blur="touched.mbrPswrd = true" class="form-control form-control-lg text-4" required placeholder="영문, 숫자를 포함한 4자 이상 20자 이내">
                                <div v-if="touched.mbrPswrd && errorPswrd" class="error">{{ errorPswrd }}</div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col">
                                <label class="form-label text-color-dark text-3">비밀번호 확인 <span class="text-color-danger">*</span></label>
                                <input type="password" v-model="pswrdConfirm" @blur="touched.pswrdConfirm = true" class="form-control form-control-lg text-4" required>
                                <div v-if="touched.pswrdConfirm && errorPswrdConfirm" class="error">{{ errorPswrdConfirm }}</div>
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
                            <div class="form-group col">
                                <label class="form-label text-color-dark text-3">생년월일 <span class="text-color-danger">*</span></label>
                                <input type="text" v-model="mbrBd" @blur="touched.mbrBd = true" class="form-control form-control-lg text-4" required placeholder="8자리">
                                <div v-if="touched.mbrBd && errorMbrBd" class="error">{{ errorMbrBd }}</div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col">
                                <label class="form-label text-color-dark text-3">휴대폰 <span class="text-color-danger">*</span></label>
                                <input type="text" v-model="mbrMp" @blur="touched.mbrMp = true" class="form-control form-control-lg text-4" required placeholder="-(다시)는 제외하고 입력">
                                <div v-if="touched.mbrMp && errorMbrMp" class="error">{{ errorMbrMp }}</div>
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
                                <label class="form-label text-color-dark text-3">이메일 수신 체크 <span class="text-color-danger">*</span></label>
                                <div class="form-group col form-control form-control-lg text-4">
                                    <input type="radio" value="Y" v-model="mbrEmlRcvChck" required class="form-check-input"> 예
                                    <input type="radio" value="N" v-model="mbrEmlRcvChck" required class="form-check-input"> 아니오
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col">
                                <label class="form-label text-color-dark text-3">성별 <span class="text-color-danger">*</span></label>
                                <div class="form-group col form-control form-control-lg text-4">
                                    <input type="radio" value="M" v-model="gndrCtgryCd" required class="form-check-input"> 남
                                    <input type="radio" value="F" v-model="gndrCtgryCd" required class="form-check-input"> 여
                                </div>
                            </div>
                        </div>
                        <!-- 약관 동의 모달 팝업-->
                        <div>
                            <div v-if="errorMbrPrvcyTrmsChck" class="error errorMbrPrvcyTrmsChck">{{ errorMbrPrvcyTrmsChck }}</div>
                        </div>
                        <div class="d-flex mb-3 justify-content-center">
                            <div class="row justify-content-between">
                                <div class="col-auto">
                                    <button type="button" class="col btn btn-dark rounded-0 text-3" data-bs-toggle="modal" data-bs-target="#largeModal" @click="fetchTerms">
                                        회원정보 약관
                                    </button>
                                </div>
                                <div class="col-auto d-flex align-items-center text-4">
                                    <input type="checkbox" true-value="Y" false-value="N" v-model="mbrPrvcyTrmsChck" required class="form-check-input">동의
                                </div>
                            </div>
                            
                            <div class="modal fade" id="largeModal" tabindex="-1" aria-labelledby="largeModalLabel" style="display: none;" aria-hidden="true">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title" id="largeModalLabel"> 회원정보 약관</h4>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-hidden="true">×</button>
                                        </div>
                                        <div class = "modal-body">
                                            <div v-if="state.terms.length">
                                                <div v-for="(term, index) in state.terms" :key="index">
                                                    <p> {{ term.trmsCntnt }}</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-light" data-bs-dismiss="modal">확인</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 약관 동의 모달 팝업-->
                        <div class="row">
                            <div class="form-group col">
                                <button type="submit" class="btn btn-dark btn-modern w-100 text-uppercase rounded-0 font-weight-bold text-3 py-3" data-loading-text="Loading...">회원가입 완료</button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col">
                                <a href="#" class="btn btn-dark btn-modern w-100 text-transform-none rounded-0 font-weight-bold align-items-center d-inline-flex justify-content-center text-3 py-3" data-loading-text="Loading..."><i class="fab fa-facebook text-5 me-2"></i> 소셜 회원가입</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

        </div>

    </div>
</template>

<script setup>
import { ref, reactive, watchEffect } from 'vue';
import { useRouter } from 'vue-router';
import { registerMember, sendEmlRegister, getTermsContents } from '@/api/member';

const router = useRouter();

const mbrId = ref('');
const mbrPswrd = ref('');
const pswrdConfirm = ref('');
const mbrName = ref('');
const mbrBd = ref('');
const mbrMp = ref('');
const mbrEmlAdrs = ref('');
const mbrEmlRcvChck = ref('');
const mbrPrvcyTrmsChck = ref('');
const gndrCtgryCd = ref('');

const emailKey = ref('');
const emailCode = ref('');

const errorMbrId = ref('');
const errorPswrd = ref('');
const errorPswrdConfirm = ref('');
const errorMbrName = ref('');
const errorMbrBd = ref('');
const errorMbrEmlAdrs = ref('');
const errorMbrMp = ref('');
const errorMbrEmlChck = ref('');
const errorMbrPrvcyTrmsChck = ref('');

const touched = reactive({
    mbrId: false,
    mbrPswrd: false,
    pswrdConfirm: false,
    mbrName: false,
    mbrBd: false,
    mbrMp: false,
    mbrEmlAdrs: false,
    emailKey: false,
    mbrPrvcyTrmsChck: false,
});

const state = reactive({
    terms: []
});

const fetchTerms = async () => {
    try {
        const { data } = await getTermsContents();
        console.log("데이터받음", data);
        state.terms.length = 0;  // 기존 배열 비우기
        state.terms.push(...data);  // 새로운 데이터 추가
    } catch (error) {
        console.error("데이터를 가져오는 중 오류가 발생했습니다.", error);
    }
};

const emlSend = async () => {
    emailCode.value = '';

    try {
        const response = await sendEmlRegister(mbrEmlAdrs.value);
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
    if (!/^[A-Za-z0-9]{4,20}$/.test(mbrId.value)) {
        errorMbrId.value = '아이디 형식이 올바르지 않습니다. (영문, 숫자를 포함한 4자 이상 20자 이내)';
    } else {
        errorMbrId.value = '';
    }

    if (!/^[A-Za-z0-9]{4,20}$/.test(mbrPswrd.value)) {
        errorPswrd.value = '비밀번호 형식이 올바르지 않습니다. (영문, 숫자를 포함한 4자 이상 20자 이내)';
    } else {
        errorPswrd.value = '';
    }

    if (pswrdConfirm.value !== mbrPswrd.value) {
        errorPswrdConfirm.value = '비밀번호가 일치하지 않습니다.';
    } else {
        errorPswrdConfirm.value = '';
    }

    if (!/^[A-Za-z가-힣]{1,6}$/.test(mbrName.value)) {
        errorMbrName.value = '이름 형식이 올바르지 않습니다.';
    } else {
        errorMbrName.value = '';
    }

    if (!/^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/.test(mbrBd.value)) {
        errorMbrBd.value = '생년월일 형식이 올바르지 않습니다. (8자리)';
    } else {
        errorMbrBd.value = '';
    }

    if (!/^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/.test(mbrMp.value)) {
        errorMbrMp.value = '휴대폰 번호 형식이 올바르지 않습니다. (10자리 또는 11자리 숫자)';
    } else {
        errorMbrMp.value = '';
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

    if (mbrPrvcyTrmsChck.value !== 'Y') {
        errorMbrPrvcyTrmsChck.value = '개인정보 처리방침에 동의해 주세요.';
    } else {
        errorMbrPrvcyTrmsChck.value = '';
    }
});

const submitForm = async () => {
    if (Object.values(touched).some(field => field === false)) {    // touched된 값들을 배열로 반환하여 false일 경우
        Object.keys(touched).forEach(key => touched[key] = true);   // 사용자가 필드를 터치(포커스)하면 해당 필드의 touched 상태를 true로 설정
    }

    if (
        !errorMbrId.value &&
        !errorPswrd.value &&
        !errorPswrdConfirm.value &&
        !errorMbrName.value &&
        !errorMbrBd.value &&
        !errorMbrMp.value &&
        !errorMbrEmlAdrs.value &&
        !errorMbrEmlChck.value &&
        !errorMbrPrvcyTrmsChck.value
    ) {
        try {
            const memberData = {
                mbrId: mbrId.value,
                mbrPswrd: mbrPswrd.value,
                mbrName: mbrName.value,
                mbrBd: mbrBd.value,
                mbrMp: mbrMp.value,
                mbrEmlAdrs: mbrEmlAdrs.value,
                mbrEmlRcvChck: mbrEmlRcvChck.value,
                mbrPrvcyTrmsChck: mbrPrvcyTrmsChck.value,
                gndrCtgryCd: gndrCtgryCd.value,
            };

            await registerMember(memberData);

            alert('회원가입이 완료되었습니다.');
            router.push('/member/login');
        } catch (error) {
            console.error('회원가입 중 오류 발생:', error);
            alert('회원가입 중 오류가 발생했습니다.');
        }
    } else {
        alert('입력한 정보를 확인해주세요.');
    }
};

fetchTerms();
</script>

<style scoped>
    .error {
        color: red;
    }
    .form-group input[type="radio"]:nth-child(2) {
        margin-left: 10px;
    }
    .errorMbrPrvcyTrmsChck {
        text-align: center;
        padding-bottom: 15px;
    }
</style>
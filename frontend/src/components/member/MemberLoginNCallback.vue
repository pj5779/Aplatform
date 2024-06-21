<template>
    <div>
        <p v-if="error">{{ error }}</p>
        <p v-else-if="profile">로그인 처리 중...</p>
    </div>
</template>

<script setup>
import { onMounted, reactive } from 'vue';
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router';
import { useStore } from 'vuex';

const state = reactive({
    naverClientId: 'TIwA7WnbAvnjEwnbPGZm',
    clientSecret: 'Qbh0YK_yrf',
    code: '',
    state: '',          // csrf 공격을 방지하기 위해 애플리케이션에서 생성한 상태 토큰값으로 URL 인코딩을 적용한 값을 사용
    accessToken: '',    // 발급받은 access_token 저장을 위한 변수
    refreshToken: '',   // 발급받은 refresh_token 저장을 위한 변수

    birthYear: '',
    birthDate: '',

    mbrId: '',
    mbrPswrd: '',
    mbrName: '',
    mbrEmlAdrs: '',
    mbrBd: '',
    mbrMp: '',
    gndrCtgryCd: ''
});

const route = useRoute();
const router = useRouter();
const store = useStore();

const naverCallback = async () => {
    state.code = route.query.code;
    state.state = route.query.state;

    const url = `https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&client_id=${state.naverClientId}&client_secret=${state.clientSecret}&code=${state.code}&state=${state.state}`;
    const headers = { "X-Naver-Client-Id": state.naverClientId, "X-Naver-Client-Secret": state.clientSecret };

    try {
        const { data } = await axios.get(url, { headers });
        state.accessToken = data.access_token;
        state.refreshToken = data.refresh_token;

        await naverMbrInfo();
    } catch (error) {
        console.error('Failed to fetch token:', error.message);
        alert('네이버 로그인 처리에 실패했습니다.');
    }
};

const naverMbrInfo = async () => {
    const url = `https://openapi.naver.com/v1/nid/me`;
    const headers = { "Authorization": `Bearer ${state.accessToken}` };

    try {
        const { data } = await axios.get(url, { headers });
        parseUserInfo(data.response);
    } catch (error) {
        console.error('Failed to fetch user info:', error.message);
        alert('네이버 사용자 정보 조회에 실패했습니다.');
    }
};

const parseUserInfo = (response) => {
    state.mbrId = response.email;
    state.mbrName = response.name;
    state.mbrEmlAdrs = response.email;
    state.gndrCtgryCd = response.gender;

    let birthArr = response.birthday.split('-');
    state.birthDate = birthArr.join('');
    state.birthYear = response.birthyear;
    state.mbrBd = state.birthYear + state.birthDate;

    let phoneArr = response.mobile.split('-');
    state.mbrMp = phoneArr.join('');

    checkNaverMbrId(response);
};

const checkNaverMbrId = async (response) => {
    const url = `/member/idCheckNaver?mbrId=${response.email}`;
    const headers = { "Content-Type": "application/json" };

    try {
        const { data } = await axios.get(url, { headers });

        if (data.result === true) {
            await naverMbrLogin();
        } else {
            await naverMbrJoin();
        }
    } catch (error) {
        console.error('Failed to check member ID:', error.message);
        alert('네이버 회원 ID 중복 확인에 실패했습니다.');
    }
};

const naverMbrJoin = async () => {
    const url = `/member/registerNaver`;
    const headers = { "Content-Type": "application/json" };
    const body = {
        mbrId: state.mbrId,
        mbrPswrd: state.mbrPswrd,
        mbrEmlAdrs: state.mbrEmlAdrs,
        mbrBd: state.mbrBd,
        mbrName: state.mbrName,
        mbrMp: state.mbrMp,
        gndrCtgryCd: state.gndrCtgryCd,
    };

    try {
        const { data } = await axios.post(url, body, { headers });

        if (data.status === 200) {
            sessionStorage.setItem("token", data.token);
            store.commit('setMember', data.member);
            alert('환영합니다.');
            router.push('/');
        } else if (data.status === 400) {
            alert(data.message); // 이메일 중복 경고 메시지 표시
        } else {
            alert(data.message); // 기타 오류 메시지 표시
        }
    } catch (error) {
        console.error('Failed to register member:', error.message);
        alert('네이버 회원 가입에 실패했습니다.');
    }
};

const naverMbrLogin = async () => {
    const url = `/member/loginNaver`;
    const headers = { "Content-Type": "application/json" };
    const body = {
        mbrId: state.mbrId,
        mbrPswrd: state.mbrPswrd
    };

    try {
        const { data } = await axios.post(url, body, { headers });

        if (data.status === 200) {
            sessionStorage.setItem("token", data.token);
            store.commit('setMember', data.member);
            router.push('/');
        } else {
            alert(data.message);
        }
    } catch (error) {
        console.error('Failed to log in:', error.message);
        alert('네이버 로그인에 실패했습니다.');
    }
};

onMounted(() => {
    naverCallback();
});

</script>
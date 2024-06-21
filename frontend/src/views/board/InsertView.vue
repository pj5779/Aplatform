<template>
    <section id="examples" class="section section-default">
        <div class="container">
            <section class="card card-admin">
                <header class="card-header">
                    <h2 class="card-title">글 작성</h2>
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
            <div class="mb-3">
                <label for="file" class="form-label" v-if="brdCtgryCd === 'feedback'">파일</label>
                <input type="file" @change="handleFileUpload" class="form-control" id="file"> <!-- 추가 -->
            </div>
            <div>
                <button @click="submitPost()">작성</button>
            </div>
        </div>
        
    </section>
    </template>
    
    <script setup>
    import { ref } from 'vue';
    import axios from 'axios';
    import { useRouter } from 'vue-router';

    const brdTl = ref('');
    const brdCntnt = ref('');
    const dltChck = ref('');
    const useChck = ref('');
    const file = ref(null); // 추가
    const router = useRouter();
    const brdCtgryCd = router.currentRoute.value.params.brdCtgryCd;

    const handleFileUpload = (event) => {
        file.value = event.target.files[0];
    };

    const submitPost = () => {
        dltChck.value = 'false';
        useChck.value = 'true';
    
    if (brdTl.value.trim() === '' || brdCntnt.value.trim() === '') {
        alert('제목과 내용을 확인하세요.');
        return;
    }

    const formData = new FormData();  // 추가
        formData.append('board', new Blob([JSON.stringify({
            brdCtgryCd: brdCtgryCd,
            brdTl: brdTl.value,
            brdCntnt: brdCntnt.value,
            dltChck: dltChck.value,
            useChck: useChck.value,

        })], { type: 'application/json' }));

        if (file.value) {
            formData.append('file', file.value);
        }

        axios.post('/board/insert', formData, {  // 추가 
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })

        .then(response => {
            console.log(response);
            alert('게시물이 성공적으로 작성되었습니다.');
            brdTl.value = '';
            brdCntnt.value = '';
            file.value = null;

            router.push({ path: `/board/list/${brdCtgryCd}` });
        })
        .catch(error => {
            console.error(error);
            alert('게시물 작성에 실패하였습니다.');
        });
    };
    </script>
    
    <style scoped>
    
    </style>
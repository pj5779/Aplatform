<template>
    <div id="examples" class="container py-2">
        <div class="col pb-3 pt-3">
            <div class="row">
                <h1 class="form-group col-md-12">
                    <strong class="font-weight-extra-bold"> 이력서 목록 </strong>
                </h1>
                <hr class="gradient" />
            </div>

            <div class="row">
                <div class="form-group col-md-10"></div>
                <!-- 코드화 안되어있어서 하드코딩 -->
                <div class="form-group col-md-2">
                    <select class="form-select form-control h-auto py-2" @change="changeSort($event)">
                        <option value="asc">올림차순</option>
                        <option value="desc">내림차순</option>
                    </select>
                </div>
            </div>

            <div class="row">
                <!-- 자료없을때 예외 -->
                <div v-if="resumeListData.resumeDatas.length == 0">
                    <strong class="font-weight-extra-bold"> 자료가 없습니다. </strong>
                </div>
                <!-- 자료있을때 for -->
                <div v-else>
                    <div v-for="resumeData in resumeListData.resumeDatas" :key="resumeData.rsm_sq">
                        <ResumeDatas :resumeData="resumeData" />
                    </div>
                </div>
            </div>
            <hr class="gradient" />
            <div class="row">
                <div
                    v-if="resumeListData.paginationData.totalDataCount != undefined && resumeListData.paginationData.totalDataCount != 0">
                    <PaginationData :paginationData="resumeListData.paginationData" @change-page-no="changePageNo" />
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>

import PaginationData from "@/components/fo/enterprise/common/PaginationData.vue";
import ResumeDatas from "@/components/fo/enterprise/resume/ResumeDatas.vue";
import { useAxios } from "@/use/useAxios";
import { onMounted, ref } from "vue";

const resumeListData = ref({
    resumeDatas: [],
    paginationData: {},
    searchListData: {},
});

onMounted(() => {
    // 첫페이지 입장시 정보 받아오기
    console.log("온마운트");
    console.log(resumeListData.value.paginationData.totalDataCount);
    // applyListData.value.searchListData = { rsm_sq: 1, division: "all", condition: 0, sort: "asc", pageNo: 1 };
    // callAxios();
});

// axios 함수
const callAxios = async () => {
    await useAxios(
        "get",
        "/resumes/resume-list/" + resumeListData.value.searchListData.rsm_sq + "/" + resumeListData.value.searchListData.sort + "/" + resumeListData.value.searchListData.pageNo,
        null
    )
        .then((data) => {
            resumeListData.value = data.success.value;
            console.log(resumeListData.value.paginationData.totalDataCount);
        })
        .catch((data) => {
            console.log(data.error.value);
        });
};

// 이벤트 함수
// 페이지네이션 페이지 변경 클릭
const changePageNo = (event) => {
    console.log(event);
    resumeListData.value.searchListData.pageNo = event;
    callAxios();
};
// 정렬 select 변경
const changeSort = (value) => {
    resumeListData.value.searchListData.sort = value.target.value;
    resumeListData.value.searchListData.pageNo = 1;
    callAxios();
};
</script>

<style scoped></style>
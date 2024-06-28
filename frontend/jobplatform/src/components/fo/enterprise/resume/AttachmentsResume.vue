<template>
    <div class="col pb-3 pt-1">
        <div class="col-lg-4">
            <h4 class="mb-0">
                첨부파일 &nbsp;
                <a href="#" class="btn btn-primary btn-circle mb-2" @click="addComponents()">
                    <i class="fa fa-plus"></i>
                </a>
            </h4>
            <hr class="mt-1 mb-2" />
            <div>
                <div v-for="(attachmentsData, index) in attachmentsDatas" :key="index">
                    <AttachmentsDatas :id="attachmentsData.id" @remove-components="removeComponents" />
                </div>
            </div>
        </div>
        <div class="col-lg-12">
            <div class="float-end">
                <a href="#" class="btn btn-outline btn-xl btn-primary mb-2">저장</a>
                <a href="#" class="btn btn-outline btn-xl btn-light mb-2">취소</a>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from "vue";
import AttachmentsDatas from "./AttachmentsDatas.vue";


const attachmentsDatas = ref([]);
let count = 1;

const addComponents = () => {
    attachmentsDatas.value.push({ id: count });
    count++;
};

const removeComponents = (event) => {
    let index = -1;

    for (let i = 0; i < attachmentsDatas.value.length; i++) {
        console.log(attachmentsDatas.value[i].id);
        if (attachmentsDatas.value[i].id == event) {
            index = i;
        }
    }

    attachmentsDatas.value.splice(index, 1);
};

</script>

<style scoped></style>
<template>
    <div>
        <a class="btn btn-outline btn-rounded btn-light" @click="$emit('removeComponents', props.id)">
            컴포먼트 {{ props.id }}
        </a>
    </div>
</template>

<script setup>
import { defineProps } from "vue";

const props = defineProps(["id"]); 
</script>

<style scoped></style>

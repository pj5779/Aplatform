
<template>
    <div class="container py-4">
        <br><br><br><br><br><br><br><br>
        <h1 style="text-align: center;">이 페이지는 메인 페이지입니다.</h1>
        <br><br><br><br><br><br><br><br>
    </div>
</template>

<script setup>
import store from '@/store'
import { computed,onMounted } from 'vue'

const test = computed(() => { 
    
 return  store.getters.enterMember;
  })

onMounted(() => { 

    alert(test.value.pk)
    console.log(test.value.entrprsId);
    
})
</script>

<style>

</style>
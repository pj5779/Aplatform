<template>
  <div>
    <BaseHeader/>
    <router-view></router-view>
    <BaseFooter></BaseFooter>
  </div>
</template>

<script setup>
  import BaseHeader from "./components/common/BaseHeader.vue";
  import BaseFooter from "./components/common/BaseFooter.vue";
  import store from "@/store"
  const id = sessionStorage.getItem("id");

  if(id){
    store.commit("setId",id);
  }
</script>

<style>

</style>
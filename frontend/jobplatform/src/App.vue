<template>
  <div>
    <BaseHeader />
    <router-view />
    <BaseFooter />
  </div>
</template>

<script setup>
import BaseHeader from "./components/user/common/BaseHeader.vue";
import BaseFooter from "./components/user/common/BaseFooter.vue";
</script>

<style></style>

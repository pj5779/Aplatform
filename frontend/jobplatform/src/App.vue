<template>
  <div>
    <BaseHeader />
    <router-view />
    <BaseFooter />
  </div>
</template>

<script setup>
import BaseHeader from "./components/fo/user/common/BaseHeader.vue";
import BaseFooter from "./components/fo/user/common/BaseFooter.vue";
</script>

<style></style>

<script setup>
const msg = "hello vue3";
const age = 20;
const msg2 = `你好我今年${age}岁了`;
</script>

<template>
  <div>
    <h2 v-text="msg"></h2>
    <h2 v-text="msg2"></h2>
    <h2 v-text="`我是时荣宝,我今年${age}岁`"></h2>
    <h2>{{ msg2 }}</h2>
    <h2>{{ `我是时荣宝,我今年${age}岁了` }}</h2>
  </div>
</template>

<style scoped>
</style>

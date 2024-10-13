<script setup>
// 接收父组件传来的参数
import { defineProps } from 'vue';
defineProps({
  message:String
})

</script>

<template>
  <div>
    <h1>这里展示主要内容</h1>
    <h2>{{ message }}</h2>
  </div>
</template>

<style scoped>

</style>

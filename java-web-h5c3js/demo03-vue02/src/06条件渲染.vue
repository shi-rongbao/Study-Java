<script setup>
import { ref } from "vue";
let flag = ref(true);

</script>

<template>
  <div>
    <h1 v-if="flag">Java is the best</h1>
    <h1 v-else>fuck you</h1>
    <h1 v-show="flag">hhhhhhhhhhhhhhhhhhhhh</h1>  <!-- 与 v-if 不同的是, 好v-show的值如果为false,还会存在在dom树中-->
    <button @click="flag = !flag">点我</button>
  </div>
</template>

<style scoped>
</style>

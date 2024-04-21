<script setup>
import { ref, reactive } from "vue";

let count = ref(1);

// 写出让count自增自减的函数
function countIncr() {
  count.value++;
}

function countDecr() {
  count.value--;
}
</script>

<template>
  <div>
    <button @click="countIncr()">+</button>
    <span v-text="count"></span>
    <button @click="countDecr()">-</button>
  </div>
</template>

<style scoped>
</style>

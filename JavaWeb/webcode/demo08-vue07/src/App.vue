<script setup>
import request from "./axios";
import { ref, reactive } from "vue";

// let message = ref("");
let message = reactive({
  code: 1,
  content: "",
});
async function change() {
  let { data } = await getLoveWords();
  Object.assign(message, data);
}

function getLoveWords() {
  return request.post("https://api.uomg.com/api/rand.qinghua?format=json");
}
</script>

<template>
  <div>
    <h1 v-text="message.content"></h1>
    <button @click="change()">change</button>
  </div>
</template>

<style scoped>
</style>

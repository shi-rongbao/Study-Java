<script setup>
import axios from "axios";
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
  return axios.get(
    "https://api.uomg.com/api/rand.qinghua",
    {
      params:{
        format:'json',
        username:'zhangsan',
        userPwd:'123456'
      }
    }
    );
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

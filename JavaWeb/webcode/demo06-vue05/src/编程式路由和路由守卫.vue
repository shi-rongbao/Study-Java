<script setup>
import { useRouter } from "vue-router";
import { ref } from "vue";
const router = useRouter();
// function showList() {
//   // router.push("/list");
//   router.push({ path: "/list" });
// }
let myPath = ref("");
function showMyPage() {
  router.push(myPath.value);
}
</script>

<template>
  <div>
    <router-link to="/home">home</router-link> <br>
    <router-link to="/add">add</router-link> <br>
    <router-link to="/list">list</router-link> <br>
    <router-link to="/update">update</router-link> <br>
    <button @click="showList()">list</button>
    <button @click="showMyPage()">Go</button>
    <input type="text" v-model="myPath" />

    <router-view></router-view>
  </div>
</template>

<style scoped>
</style>

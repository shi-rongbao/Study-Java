<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
const router = useRouter();
let username = window.sessionStorage.getItem("username");
function logout() {
  // 清除session 中保存的信息
  window.sessionStorage.removeItem("username");
  // 跳转到login路由
  router.push("/login");
}
</script>

<template>
  <div>
    <h1>home</h1>
    <h3>欢迎{{ username }}登录</h3>
    <button @click="logout()">退出登录</button>
  </div>
</template>

<style scoped>
</style>

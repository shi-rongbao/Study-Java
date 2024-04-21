<script setup>
import { ref } from "vue";
import {useRouter} from 'vue-router'
let router = useRouter()
let username = ref("");
let password = ref("");

function login() {
  if (username.value === "root" && password.value === "123456") {
    // 如果账号密码正确则进行路由跳转
    router.push('/home')

    //将用户名保存在浏览器上
    window.sessionStorage.setItem("username",username.value)
  } else {
    alert("用户名或者密码有误");
  }
}
</script>

<template>
  <div>
    <h1>请输入账号</h1>
    账号<input type="text" v-model="username" /> <br />
    密码<input type="password" v-model="password" /> <br />
    <button @click="login()">登录</button>
  </div>
</template>

<style scoped>
</style>

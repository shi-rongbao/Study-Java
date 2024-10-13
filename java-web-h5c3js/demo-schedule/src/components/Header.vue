<script setup>
// 导入pinia数据
import { defineUser } from "../store/userStore";
import { defineSchedule } from "../store/scheduleStore";
import {useRouter} from 'vue-router'
const router = useRouter()
const sysUser = defineUser();
const schedule = defineSchedule();

function logout(){
  // 清除登录数据
  sysUser.$reset()
  schedule.$reset()
  // 跳转到登录页
  router.push('/login')
}
</script>

<template>
  <div>
    <h1 class="ht">欢迎使用日程管理系统</h1>
    <div>
      <div class="optionDiv" v-if="sysUser.username === ''">
        <router-link to="/login">
          <button class="b1s">登录</button>
        </router-link>
        <router-link to="/regist">
          <button class="b1s">注册</button>
        </router-link>
      </div>

      <div class="optionDiv" v-else>
        欢迎{{ sysUser.username }}
        <button class="b1b" @click="logout()">退出登录</button>
        <router-link to="/showschedule">
          <button class="b1b">查看我的日程</button>
        </router-link>
      </div>

      <br />
    </div>
  </div>
</template>

<style scoped>
.ht {
  text-align: center;
  color: cadetblue;
  font-family: 幼圆;
}
.b1s {
  border: 2px solid powderblue;
  border-radius: 4px;
  width: 60px;
  background-color: antiquewhite;
}

.b1b {
  border: 2px solid powderblue;
  border-radius: 4px;
  width: 100px;
  background-color: antiquewhite;
}
.optionDiv {
  width: 400px;
  float: right;
}
</style>
<script setup>
// 向父组件发送参数
// defineMits用于定义父组件提交数据的事件以及正式的提交数据
import { defineEmits } from "vue";
// 定义一个向父组件提交数据的事件 事件名称自定义
const emits = defineEmits(["sendMenu"]);
// 提交数据的方法
function send(data) {
  emits("sendMenu", data);
}
</script>

<template>
  <div>
    <ul>
      <li @click="send('学员管理')">学员管理</li>
      <li @click="send('图书管理')">图书管理</li>
      <li @click="send('请假管理')">请假管理</li>
      <li @click="send('考试管理')">考试管理</li>
      <li @click="send('班级管理')">班级管理</li>
      <li @click="send('教师管理')">教师管理</li>
    </ul>
  </div>
</template>

<style scoped>
</style>

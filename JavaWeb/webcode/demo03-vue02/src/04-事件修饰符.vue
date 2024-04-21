<script setup>
import { ref } from "vue";
function fun1() {
  alert("fuck you");
}
let count = ref(1);
function fun2() {
  count.value++;
}
function fun3() {
  const flag = confirm("确认要访问链接吗?");
  if (!flag) {
    // 原生js编码方式阻止组件的默认行为
    event.preventDefault();
  }
}

function fun4() {
  alert("超链接被点击了");
}
</script>

<template>
  <div>
    <button v-on:click="fun1()">fuck me</button>
    <h2 v-html="count"></h2>
    <button v-on:click="count++">+</button>
    <!-- 事件修饰符, .once 代表这次绑定只会执行一次 -->
    <!-- .prevent 组织组件的默认行为 -->
    <button v-on:click.once="count++">+</button>
    <a href="http://www.atguigu.com" v-on:click="fun3()">尚硅谷</a>
    <a href="http://www.atguigu.com" v-on:click.prevent="fun4()">尚硅谷</a>
  </div>
</template>

<style scoped>
</style>

<script setup>
import {onBeforeMount, onBeforeUpdate, onMounted, onUpdated, ref} from "vue";

const count = ref(1);

function countAdd() {
  count.value++;
}

// 生命周期函数
onBeforeMount(() => {
  // 挂载之前
  console.log("挂载前：count的值:", count.value)
  console.log("挂载前：能不能拿到这个button：", document.getElementById("btn01")) // 这个在挂载之前还没有初始化,拿不到
})

onMounted(() => {
  // 挂载完成
  console.log("挂载完成：count的值:", count.value)
  console.log("挂载完成：能不能拿到这个button：", document.getElementById("btn01")) // 挂载完成后就可以拿到这个值了
})

onBeforeUpdate(() => {
  // 更新之前
  console.log("更新前：count的值:", count.value)
  console.log("更新前：文本的值：", document.getElementById("btn01").innerHTML)
})

onUpdated(() => {
  // 更新之后
  console.log("更新后：count的值:", count.value)
  console.log("更新后：文本的值：", document.getElementById("btn01").innerHTML)
})
</script>

<template>
  <button id="btn01" @click="countAdd">点了{{ count }}次</button>
</template>

<style scoped>

</style>
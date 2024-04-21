<script setup>
const data = {
  logo: "http://www.atguigu.com/images/index_new/logo.png",
  name: "尚硅谷",
  url: "http://www.atguigu.com",
};
</script>

<template>
  <div>
    <!-- v-bind可以省略不写,只留下一个 : 就可以 -->
    <a v-bind:href="data.url"><img :src="data.logo" :title="data.name" /></a>
  </div>
</template>

<style scoped>
</style>

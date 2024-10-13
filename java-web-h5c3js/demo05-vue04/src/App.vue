<script setup>
</script>

<template>
  <div>
    <h2>fuck</h2>
    <router-link to="/home">home</router-link> <br />
    <router-link to="/add">add</router-link> <br />
    <router-link to="/update">update</router-link> <br />
    <router-link to="/list">list</router-link> <br />
    <hr />
    <!-- 改标签会被替换成具体的.vue -->
    <router-view></router-view>
    <hr />
    <h2>fuck</h2>
  </div>
</template>

<style scoped>
</style>

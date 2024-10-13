<script setup>
import { ref, reactive } from "vue";
let user = reactive({
  username:"",
  userPwd:""
})

let hbs = ref([])
</script>

<template>
  <div>
    <input type="text" v-model="user.username"><br>
    <input type="password" v-model="user.userPwd"><br> 
    爱好:
      唱 <input type="checkbox" v-model="hbs" value="sing">
      跳 <input type="checkbox" v-model="hbs" value="dance">
      rap <input type="checkbox" v-model="hbs" value="rap">
    <h2>{{ user }}</h2>
    <h2>{{ hbs }}</h2>
  </div>
</template>

<style scoped>
</style>

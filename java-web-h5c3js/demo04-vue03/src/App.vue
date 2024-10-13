<script setup>
import { ref } from "vue";
import Header from "./components/Header.vue";
import Content from "./components/Content.vue";
import Navigator from "./components/Navigator.vue";
let menu = ref("");
function receiver(data) {
  menu.value = data;
}
</script>

<template>
  <div>
    <Header class="header"></Header>
    <Navigator @sendMenu="receiver" class="navigator"></Navigator>
    <Content class="content" :message="menu"></Content>
  </div>
</template>

<style scoped>
.header {
  height: 80px;
  border: 1px solid black;
}
.navigator {
  border: 1px solid green;
  width: 15%;
  height: 600px;
  float: left;
}
.content {
  width: 83%;
  height: 600px;
  border: 1px solid orange;
  float: right;
}
</style>

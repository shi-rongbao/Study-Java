<script setup>
// 接收传递过来的路径参数
import { useRoute } from "vue-router";
import { ref, onUpdated } from "vue";
const route = useRoute();
let languageId = ref(0);
let languageName = ref("");

languageId.value = route.params.id;
languageName.value = route.params.language;

onUpdated(() => {
  languageId.value = route.params.id;
  languageName.value = route.params.language;
});
</script>

<template>
  <div>
    {{ languageId }}
    {{ languageName }}
    <h1>showDetail接收路径参数</h1>
  </div>
</template>

<style scoped>
</style>

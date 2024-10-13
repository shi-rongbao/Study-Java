<script setup>
import { ref, reactive } from "vue";

let count = ref(1);

const person = reactive({
  name: "张三",
  age: 20,
});

function incr() {
  count.value++;
}
function incrAge() {
  person.age++;
}
</script>

<template>
  <div>
    <h2 v-text="count"></h2>
    <button @click="incr()">+</button>
    <button @click="count++">+</button>
    <hr />
    <h2 v-text="person.age"></h2>
    <button @click="incrAge()">+</button>
    <button @click="person.age++">+</button>
  </div>
</template>

<style scoped>
</style>

<script setup>
import { ref, reactive, watch, watchEffect } from "vue";

let fullName = ref("");
let firstName = ref("李");
let lastName = reactive({
  name: "瓜皮",
});

watchEffect(() => {
  fullName.value = firstName.value + lastName.name
});

// watch(firstName, (newValue, oldValue) => {
//   fullName.value = newValue + lastName.name;
// });
// // watch(
// //   () => lastName.name,
// //   (newValue, oldValue) => {
// //     fullName.value = firstName.value + newValue;
// //   }
// // );
// watch(
//   () => lastName,
//   () => {
//     fullName.value = firstName.value + lastName.name;
//   },
//   { deep: true, immediate: true }
// );
</script>

<template>
  <div>
    姓氏:<input type="text" v-model="firstName" /> <br />

    名字:<input type="text" v-model="lastName.name" /> <br />

    全名 {{ fullName }}
  </div>
</template>

<style scoped>
</style>

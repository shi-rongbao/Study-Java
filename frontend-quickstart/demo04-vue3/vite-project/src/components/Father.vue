<script setup>

import Son from "./Son.vue";
import {reactive, ref} from "vue";

/*const money = ref(100)
const books = ref(['金瓶梅', '黄色小说'])*/

const data = reactive({
  money: 100,
  books: ['金瓶梅', '黄色小说']
})

function moneyMinis(money) {
  alert(money)
  data.money += money
}

function addMoney() {
  data.money += 10
}
</script>

<template>
  <div style="background-color: skyblue">
    <h2>Father</h2>
    <button @click="addMoney">充值</button>
    <!--使用v-bind绑定给子传值，单向数据流-->
    <!--<Son :money="data.money" :books="data.books"/>-->
    <Son v-bind="data" @buy="moneyMinis">
      <template v-slot:title>
        <button>这是一个按钮，哈哈Son</button>
      </template>
    </Son>
  </div>
</template>

<style scoped>

</style>
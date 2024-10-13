<script setup>
import {computed, reactive, ref, watch, watchEffect} from "vue";

let name = "时荣宝"

let car = reactive({
  carType: "新能源",
  carPrice: 2999
})

const number = ref(1)

let totalPrice = computed(() => car.carPrice * number.value)

let msg = "<p style='color: red'> 你好 </p>"

let fruits = ["苹果", "香蕉", "菠萝", "芒果", "橘子", "橙子", "桃子"]

function buy() {
  alert("fuckYou")
}

let url = ref("https://www.baidu.com")

function changeUrl() {
  console.log(url)
  url.value = "https://www.4399.com"
}

function addPrice() {
  car.carPrice += 100
}

/*function addNumber(){
  car.number += 1
}*/
/*watch(number, (value, oldValue, onCleanup) => {
  console.log("value = " + value)
  console.log("oldValue = " + oldValue)
  if (value > 3) {
    number.value = 3
    alert("超出限购")
  }
})*/

watchEffect(() => {
  if (number.value > 3) {
    number.value = 3
    alert("超出限购")
  }
  if (totalPrice.value > 10000) {
    alert("没钱啦")
  }
})
</script>

<template>
  <a v-bind:href="url"> Go !!!</a>
  <br>
  <a :href="url"> Go !!!</a>
  <br>
  <button @click="changeUrl">改变地址</button>
  <h2>姓名：{{ name }}</h2>
  <h2>汽车类型：{{ car.carType }}</h2>
  <h2>汽车价格：{{ car.carPrice }}</h2>
  <h2>汽车数量：{{ number }}</h2>
  <h2>总价：{{ totalPrice }}</h2>
  <button @click="addPrice">加价</button>
  <button @click=number++>加量</button>

  <span style="color: green" v-if="car.carPrice < 3000">很便宜</span>
  <span style="color: red" v-if="car.carPrice >= 3000">太贵了</span>

  <div v-html="msg"></div>
  <button @click.once="buy">购买</button>
  <!--下面两个等效-->
  <div v-text="msg"></div>
  <div>{{ msg }}</div>

  <li v-for="(fruit, index) in fruits"> {{ fruit }} === {{ index + 1 }}</li>
</template>

<style scoped>

</style>
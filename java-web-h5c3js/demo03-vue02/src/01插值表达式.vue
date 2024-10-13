<script setup>
const msg = "hello vue3";
const getMsg = () => {
  return "hello vue3 message";
};
const age = 20;
const bee = "蜜 蜂";
const cars = [
  { name: "可乐", price: 3, number: 10 },
  { name: "薯片", price: 5, number: 6 },
];
function compute() {
  let count = 0;
  for (let i in cars) {
    count += cars[i].price * cars[i].number;
  }
  return count;
}
</script>

<template>
  <div>
    <h1>{{ msg }}</h1>
    msg的值为: {{ msg }} <br>
    <!-- 插值表达式中可以调用函数,将函数的返回值渲染到指定的位置 -->
    <h2>{{ getMsg() }}</h2>  <!-- 这里应该是 "hello vue3 message" -->
    <h2>年龄是:{{ age }}, 是否成年? {{ age > 18 ? "是" : "否" }}</h2>
    <!-- 插值表达式也支持对象调用一些API -->
    <h2>{{ bee.split(" ").reverse().join("") }}</h2>
    <h2>{{ cars[0].price * cars[0].number + cars[1].price * cars[1].number }}</h2>
    <!-- 调用函数是将函数的返回值数据写入,如果没有返回值那就不可以 -->
    <h2>{{ compute() }}</h2>
  </div>
</template>

<style scoped>
</style>

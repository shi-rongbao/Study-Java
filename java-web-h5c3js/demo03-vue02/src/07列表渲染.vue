<script setup>
import { ref, reactive } from "vue";

let pro = ref("产品");

let items = reactive([
  {
    id: "item1",
    message: "可乐",
  },
  {
    id: "item2",
    message: "薯片",
  },
  {
    id: "item1",
    message: "炸鸡",
  },
]);

let cars = reactive([
  {
    name: "可乐",
    price: 3,
    number: 10,
  },
  {
    name: "薯片",
    price: 5,
    number: 7,
  },
  {
    name: "炸鸡",
    price: 20,
    number: 3,
  },
]);

// 计算购物车总金额的函数
function compute() {
  let total = 0;
  for (let index in cars) {
    total += cars[index].price * cars[index].number;
  }
  return total;
}
function removeCart(index) {
  cars.splice(index, 1);
}
function clearAll() {
  // 调用API
  cars.splice(0, cars.length);
}
</script>

<template>
  <div>
    <h1>您的购物车如下</h1>
    <table border="1px">
      <thead>
        <tr>
          <th>序号</th>
          <th>名称</th>
          <th>价格</th>
          <th>数量</th>
          <th>小计</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody v-if="cars.length > 0">
        <tr v-for="(item, index) in cars" :key="index">
          <td>{{ index + 1 }}</td>
          <td>{{ item.name }}</td>
          <td>{{ item.price }}</td>
          <td>{{ item.number }}</td>
          <td>{{ item.number * item.price }}</td>
          <td><button @click="removeCart(index)">删除</button></td>
        </tr>
      </tbody>
      <tbody v-else>
        <tr>
          <td style="text-align: center" colspan="6">购物车是空的~</td>
        </tr>
      </tbody>
      <tfoot>
        <tr>
          <td>总金额</td>
          <td style="text-align: center" colspan="5">{{ compute() }}</td>
        </tr>
      </tfoot>
    </table>
    <button @click="clearAll()">一键清空购物车</button>

    <hr />
    <ul>
      <li v-for="(item, index) in items" :key="item.id">
        {{ pro }} {{ index + 1 }} {{ item.message }}
      </li>
    </ul>
  </div>
</template>

<style scoped>
</style>

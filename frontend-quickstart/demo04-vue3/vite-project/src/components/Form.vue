<script setup>

import {reactive} from "vue";

const data = reactive({
  username: "张三",
  agree: true,
  hobby: [],
  gender: "",
  degree: "",
  subject: []
})

</script>

<template>
  <div style="display: flex;">
    <div style="border: 1px solid black;width: 300px">
      <form>
        <h1>表单绑定</h1>
        <p style="background-color: azure"><label>姓名(文本框)：</label><input v-model="data.username"/></p>
        <p style="background-color: azure"><label>同意协议(checkbox)：</label>
          <input v-model="data.agree" type="checkbox"/>
        </p>
        <p style="background-color: azure">
          <label>兴趣(多选框)：</label><br/>
          <label><input v-model="data.hobby" type="checkbox" value="足球"/>足球</label>
          <label><input v-model="data.hobby" type="checkbox" value="篮球"/>篮球</label>
          <label><input v-model="data.hobby" type="checkbox" value="羽毛球"/>羽毛球</label>
          <label><input v-model="data.hobby" type="checkbox" value="乒乓球"/>乒乓球</label>
        </p>
        <p style="background-color: azure">
          <label>性别(单选框)：</label>
          <label><input v-model="data.gender" type="radio" name="sex" value="男">男</label>
          <label><input v-model="data.gender" type="radio" name="sex" value="女">女</label>
        </p>
        <p style="background-color: azure">
          <label>学历(单选下拉列表)：</label>
          <select v-model="data.degree">
            <option disabled value="">选择学历</option>
            <option>小学</option>
            <option>初中</option>
            <option>高中</option>
            <option>大学</option>
          </select>
        </p>
        <p style="background-color: azure">
          <label>课程(多选下拉列表)：</label>
          <br/>
          <select v-model="data.subject" multiple>
            <option disabled value="">选择课程</option>
            <option>语文</option>
            <option>数学</option>
            <option>英语</option>
            <option>道法</option>
          </select>
        </p>
      </form>
    </div>
    <div style="border: 1px solid blue;width: 200px">
      <h1>结果预览</h1>
      <p style="background-color: azure"><label>姓名：{{ data.username }}</label></p>
      <p style="background-color: azure"><label>同意协议：{{ data.agree }}</label>
      </p>
      <p style="background-color: azure">
        <label>
          兴趣：
          <li v-for="(hobby, index) in data.hobby">{{ hobby }}</li>
        </label>
      </p>
      <p style="background-color: azure">
        <label>性别：{{ data.gender }}</label>
      </p>
      <p style="background-color: azure">
        <label>学历：{{data.degree}}</label>
      </p>
      <p style="background-color: azure">
        <label>
          课程：
          <li v-for="sub in data.subject">{{sub}}</li>
        </label>
      </p>
    </div>
  </div>
</template>

<style scoped>

</style>
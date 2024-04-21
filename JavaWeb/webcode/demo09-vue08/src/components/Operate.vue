<script setup>
// 导入pinia定义的数据
import { definedPerson } from "../store/store";

const person = definedPerson();
</script>

<template>
  <div>
    <h1>用于修改pinia的数据</h1>
    请输入名称: <input type="text" v-model="person.username" /> <br />
    请输入年龄: <input type="text" v-model="person.age" /> <br />
    <h2>{{ person.getAge }}</h2>
    爱好 : 吃饭<input
      type="checkbox"
      value="姬霓太美"
      v-model="person.hobbies"
    />
    睡觉<input type="checkbox" value="基尼太美" v-model="person.hobbies" />
    曹丕<input type="checkbox" value="寄你泰美" v-model="person.hobbies" />
    篮球<input type="checkbox" value="篮球" v-model="person.hobbies" /> 唱<input
      type="checkbox"
      value="唱"
      v-model="person.hobbies"
    />
    跳<input type="checkbox" value="跳" v-model="person.hobbies" /> rap<input
      type="checkbox"
      value="rap"
      v-model="person.hobbies"
    />

    <button @click="person.$reset()">恢复默认</button>
    <button @click="person.doubleAge()">年龄加倍</button>
    <button
      @click="
        person.$patch({
          username: '李四',
          age: 19,
          hobbies: ['曹丕', '道馆'],
        })
      "
    >
      换一个对象
    </button>
    <hr />

    展示一下person中的数据
    {{ person }}
  </div>
</template>

<style scoped>
</style>

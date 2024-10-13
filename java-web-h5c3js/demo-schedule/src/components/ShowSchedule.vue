<script setup>
// 导入pinia数据
import { defineUser } from "../store/userStore";
import { defineSchedule } from "../store/scheduleStore";
const sysUser = defineUser();
const schedule = defineSchedule();

// 挂在完毕后,查询当前用户的日志信息,赋值给pinia
import { ref, reactive, onUpdated, onMounted } from "vue";
import request from "../utils/request";
onMounted(async () => {
  showSchedule();
});

// 查询数据库中的数据并展示到视图中的方法
async function showSchedule() {
  // 发送异步请求,获取当前用户的所有日程记录
  const { data } = await request.get("schedule/findAllSchedule", {
    params: { uid: sysUser.uid },
  });
  schedule.itemList = data.data.itemList;
}

// 为当前用户增加一个空的日程记录
async function addItem() {
  const { data } = await request.get("schedule/addDefaultSchedule", {
    params: { uid: sysUser.uid },
  });
  if (data.code === 200) {
    // 如果增加成功,那么刷新页面
    showSchedule();
  } else {
    // 如果增加失败,弹窗
    alert("增加失败");
  }
}

async function updateItem(index) {
  // 找到需要修改的数据,发送给服务端,更新到数据库即可
  const { data } = await request.post(
    "schedule/updateSchedule",
    schedule.itemList[index]
  );
  if (data.code === 200) {
    // 如果更新成功刷新页面
    alert("保存成功");
    showSchedule();
  } else {
    // 如果更新失败弹窗
    alert("保存失败");
  }
}

async function removeItem(index) {
  const flag = window.confirm("确认删除吗?");
  let sid = schedule.itemList[index].sid;
  const { data } = await request.get(`schedule/removeSchedule?sid=${sid}`);
  if (data.code === 200 && flag) {
    // 删除页面
    showSchedule();
    alert("删除成功");
  } else {
    alert("删除失败");
  }
}
</script>

<template>
  <div>
    <h3 class="ht">您的日程如下</h3>
    <table class="tab" cellspacing="0px">
      <tr class="ltr">
        <th>编号</th>
        <th>内容</th>
        <th>进度</th>
        <th>操作</th>
      </tr>
      <tr class="ltr" v-for="(item, index) in schedule.itemList" :key="index">
        <td v-text="index + 1"></td>
        <td>
          <input type="text" v-model="item.title" />
        </td>
        <td>
          <input type="radio" value="1" v-model="item.completed" /> 已完成
          <input type="radio" value="0" v-model="item.completed" /> 未完成
        </td>
        <td class="buttonContainer">
          <button class="btn1" @click="removeItem(index)">删除</button>
          <button class="btn1" @click="updateItem(index)">保存修改</button>
        </td>
      </tr>
      <tr class="ltr buttonContainer">
        <td colspan="4">
          <button class="btn1" @click="addItem()">新增日程</button>
        </td>
      </tr>
    </table>
  </div>
</template>

<style scoped>
.ht {
  text-align: center;
  color: cadetblue;
  font-family: 幼圆;
}
.tab {
  width: 80%;
  border: 5px solid cadetblue;
  margin: 0px auto;
  border-radius: 5px;
  font-family: 幼圆;
}
.ltr td {
  border: 1px solid powderblue;
}
.ipt {
  border: 0px;
  width: 50%;
}
.btn1 {
  border: 2px solid powderblue;
  border-radius: 4px;
  width: 100px;
  background-color: antiquewhite;
}
#usernameMsg,
#userPwdMsg {
  color: gold;
}

.buttonContainer {
  text-align: center;
}
</style>

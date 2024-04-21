<script setup>
import { ref, reactive } from "vue";
import request from "../utils/request";
import { useRouter } from "vue-router";
import { defineUser } from "../store/userStore";
const sysUser = defineUser();
const router = useRouter();
let loginUser = reactive({
  username: "",
  userPwd: "",
});
let usernameMsg = ref("");
let userPwdMsg = ref("");

function checkUsername() {
  // 定义正则表达式
  const usernameReg = /^[A-Za-z0-9]{5,10}$/;
  if (!usernameReg.test(loginUser.username)) {
    usernameMsg.value = "格式有误";
    return false;
  } else {
    usernameMsg.value = "OK";
    return true;
  }
}
function checkUserPwd() {
  const userPwdReg = /^[0-9]{6}$/;
  if (!userPwdReg.test(loginUser.userPwd)) {
    userPwdMsg.value = "格式有误";
    return false;
  } else {
    userPwdMsg.value = "OK";
    return true;
  }
}

async function login() {
  const flag1 = checkUsername();
  const flag2 = checkUserPwd();
  if (!(flag1 && flag2)) {
    return;
  }
  let { data } = await request.post("user/login", loginUser);
  if (data.code === 200) {
    sysUser.uid = data.data.loginUser.uid;
    sysUser.username = data.data.loginUser.username;
    alert("登录成功");
    router.push("/showSchedule");
  } else if (data.code === 503) {
    alert("密码错误");
  } else if (data.code === 501) {
    alert("用户名有误");
  } else {
    alert("未知错误,请重试");
  }
}
</script>

<template>
  <div>
    <h3 class="ht">请登录</h3>
    <table class="tab" cellspacing="0px">
      <tr class="ltr">
        <td>请输入账号</td>
        <td>
          <input
            class="ipt"
            type="text"
            v-model="loginUser.username"
            @blur="checkUsername()"
          />
          <span id="usernameMsg" v-text="usernameMsg"></span>
        </td>
      </tr>
      <tr class="ltr">
        <td>请输入密码</td>
        <td>
          <input
            class="ipt"
            type="password"
            v-model="loginUser.userPwd"
            @blur="checkUserPwd()"
          />
          <span id="userPwdMsg" v-text="userPwdMsg"></span>
        </td>
      </tr>
      <tr class="ltr">
        <td colspan="2" class="buttonContainer">
          <input class="btn1" type="button" @click="login()" value="登录" />
          <input class="btn1" type="button" value="重置" />
          <router-link to="/regist">
            <button class="btn1">去注册</button>
          </router-link>
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
  width: 500px;
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
  width: 60px;
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

import {
    createRouter,
    createWebHashHistory
} from "vue-router"

import Add from "../components/Add.vue"
import Home from "../components/Home.vue"
import showDetail from "../components/showDetail.vue"
import showDetail2 from "../components/showDetail2.vue"
import List from "../components/List.vue"
import Update from "../components/Update.vue"

const router = createRouter({
    history: createWebHashHistory(),
    routes: [{
            path: "/",
            component: Home
        },
        {
            path: "/home",
            component: Home
        },
        {
            path: "/showDetail/:id/:language",
            component: showDetail
        },
        {
            path: "/showDetail2",
            component: showDetail2
        },
        {
            path: "/add",
            component: Add
        },
        {
            path: "/update",
            component: Update
        },
        {
            path: "/list",
            component: List
        }
    ]
})
// 设置全局前置守卫
// 每次路由切换页面前,都会执行beforeEach中的回调函数
router.beforeEach((to, from, next) => {
    /*
        from 上一个页面,从哪里来
        to   下一个页面,到哪里去
        next 放行的方法 只有执行了该方法    才回放行路由
            next()  放行
            next("/路径")   路由的重定向
    */
   console.log("beforeEach方法执行了");
   console.log(from.path);
   console.log(to.path);
   next()
})
// 设置全局后置守卫
// 每次路由切换页面后,都会执行afterEach中的回调函数
router.afterEach((to, from) => {
    console.log("afterEach方法执行了");
})
export default router
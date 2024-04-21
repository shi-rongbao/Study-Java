// 导入创建路由对象需要使用的函数
import {
    createRouter,
    createWebHashHistory
} from "vue-router"
// 导入.vue组件
import Home from "../components/Home.vue"
import Add from "../components/Add.vue"
import List from "../components/List.vue"
import Update from "../components/Update.vue"
// 创建一个路由对象
const router = createRouter({
    // history属性用于记录路由的历史
    history: createWebHashHistory(),
    routes: [{
            path: "/home",
            component: Home
        },
        {
            path: "/add",
            component: Add
        },
        {
            path: "/list",
            component: List
        },
        {
            path: "/update",
            component: Update
        },
        {
            path: "/",
            component: Home
        },
        {
            path: "/showAll",
            redirect: "/list"
        }
    ]
})

// 向外暴露这个router对象
export default router
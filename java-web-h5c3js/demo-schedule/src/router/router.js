import {
    createRouter,
    createWebHashHistory
} from 'vue-router'
import Login from '../components/Login.vue'
import Regist from '../components/Regist.vue'
import ShowSchedule from '../components/ShowSchedule.vue'
import pinia from '../pinia.js'
import {
    defineUser
} from '../store/userStore'

const sysUser = defineUser(pinia)

const router = createRouter({
    history: createWebHashHistory(),
    routes: [{
            path: "/",
            redirect:'/showSchedule'
        },
        {
            path: "/login",
            component: Login
        },
        {
            path: "/regist",
            component: Regist
        },
        {
            path: "/showschedule",
            component: ShowSchedule
        }
    ]
})

// 通过路由的全局前置守卫 是否可以访问showSchedule
router.beforeEach((to, from, next) => {
    if (to.path === '/showSchedule') {
        // 没登录,重定向到login页
        if (sysUser.username === '') {
            next('/login')
        } else {
            // 登录了,放行
            next()
        }
    } else {
        next()
    }
})

export default router
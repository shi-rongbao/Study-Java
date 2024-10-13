import {
    createRouter,
    createWebHashHistory
} from 'vue-router'

import Home from '../components/Home.vue'
import Login from '../components/Login.vue'

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
            path: "/login",
            component: Login
        } 
    ]
})

// 通过控制路由的前置守卫来控制到home.vue

router.beforeEach((to, from, next) => {
    if (to.path === '/login') {
        next()
    } else {
        const username = sessionStorage.getItem("username")
        if (null != username) {
            next()
        } else {
            next("/login")
        }
    }
})

export default router
import {createRouter, createWebHashHistory} from 'vue-router'
import List from '../components/List.vue'
import Operate from '../components/Operate.vue'

const router = createRouter({
    history:createWebHashHistory(),
    routes:[
        {
            path:'/list',
            component:List
        },
        {
            path:'/operate',
            component:Operate
        },
        {
            path:'/',
            component:Operate
        }
    ]
})

export default router
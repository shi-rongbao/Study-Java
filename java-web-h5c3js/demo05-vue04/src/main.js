import { createApp } from 'vue'

import App from './App.vue'

// 在整个App.vue中可以使用路由
import router from "./routers/router.js"
const app = createApp(App)
app.use(router)
app.mount('#app')

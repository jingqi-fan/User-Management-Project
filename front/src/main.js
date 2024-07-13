import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import axios from 'axios' // 使用 ES6 导入语法
import * as ElIconModules from '@element-plus/icons'

const app = createApp(App)

app.use(router)

for(let iconName in ElIconModules){
    app.component(iconName,ElIconModules[iconName])
}

// 全局注册 Axios
app.config.globalProperties.$axios = axios;
app.use(ElementPlus)
app.mount('#app')

// axios.defaults.baseURL = 'http://localhost:8080/'
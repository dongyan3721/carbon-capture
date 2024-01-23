import { createApp } from 'vue';
import App from './App.vue';
import 'normalize.css';
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from './router/index'
import axios from 'axios'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'


const app = createApp(App)
// 全局挂载element-plus图标组件
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

// 全局配置axios，后续会封一个专门用于网络访问的类
app.config.globalProperties.$axios = axios
// 集成element-plus
app.use(ElementPlus)
// 集成vue-router
app.use(router)
app.mount('#app')

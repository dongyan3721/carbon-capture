/**
 * @author Santa Antilles
 * @description 路由配置文件
 * @date 2024/1/14-14:12:04
 */
const routes = [

];

import {createRouter} from 'vue-router'
import {createWebHistory} from "vue-router";

const router = createRouter({
    history: createWebHistory(),
    // history: createWebHashHistory(),
    routes: routes
})

export default router
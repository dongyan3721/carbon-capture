/**
 * @author Santa Antilles
 * @description 路由配置
 * @date 2024/1/23-21:45:43
 */

import {createRouter, createWebHistory} from "vue-router/dist/vue-router";

const routes = [
    {
        path: '/',
        redirect: '/login'
    },
    {
        name: 'index',
        path: '/index',
        component: () => import('../view/Dashboard.vue')
    },
    {
        name: 'province',
        path: '/province',
        component: () => import('../view/ProvinceMethod.vue')
    },
    {
        path: '/self-center',
        redirect: '/self-center/index',
        meta: {title: '个人中心'},
        children: [
            {
                name: 'center-index',
                path: 'index',
                component: () => import('@/view/Center.vue'),
                meta: {title: '首页'}
            },
            {
                name: 'center-solution',
                path: 'my-solution',
                component: () => import('@/view/Solution.vue'),
                meta: {title: '解决方案'}
            },
            {
                name: 'center-industry',
                path: 'my-industry',
                component: () => import('@/view/MyIndustry.vue'),
                meta: {title: '我的行业'}
            }
        ]
    },
    {
        name: 'login',
        path: '/login',
        component: () => import('../view/Login')
    },
    {
        name: 'register',
        path: '/register',
        component: () => import('../view/Register')
    },
    {
        name: '404',
        path: '/404',
        component: () => import('../view/common/HttpStatus404')
    },
    {
        name: '403',
        path: '/403',
        component: () => import('../view/common/HttpStatus403')
    }
]

const router = createRouter({
    history: createWebHistory(),
    // history: createWebHashHistory(),
    routes
})

export default router
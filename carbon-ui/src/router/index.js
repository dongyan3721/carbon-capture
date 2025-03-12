/**
 * @author Santa Antilles
 * @description 路由配置
 * @date 2024/1/23-21:45:43
 */

import {createRouter, createWebHistory} from "vue-router/dist/vue-router";
import {chatterWithBackendVerifyWhetherTokenIsValid} from "@/utils/routerProperties";
import {setToken} from "@/utils/auth";
import CustomHttpStatus from "@/utils/CustomHttpStatus";
import HttpStatus from "@/utils/HttpStatus";
import {KEY_AVATAR, KEY_EMAIL, KEY_NICKNAME, KEY_ROLE, KEY_USER_ID, setLocalStorage} from "@/utils/localStorge";

const routes = [
    {
        path: '/',
        redirect: '/cmdv'
    },
    {
        path: '/cmdv',
        name: 'cmdv',
        component: ()=>import('@/view/v2/ChinaMapDataVisualization.vue')
    },
    {
        path: '/cpdv',
        name: 'cpdv',
        component: ()=>import('@/view/v2/CarbonPriceDataVisualization.vue')
    },
    {
        path: '/report',
        component: ()=>import('@/view/v2/IntelligentReport.vue'),
        name: 'report'
    },
    {
      path: '/standard-example',
        component: ()=>import('@/view/v2/StandardExample.vue'),
        name: 'example'
    },
    {

        path: '/index',
        redirect: '/cmdv'
    },
    {
        name: 'province',
        path: '/province/:provinceId',
        component: () => import('@/view/ProvinceMethod.vue')
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
                component: () => import('@/view/v2/MyIndustry.vue'),
                meta: {title: '我的数据'}
            }
        ]
    },
    {
        name: 'login',
        path: '/login',
        component: () => import('@/view/Login.vue')
    },
    {
        name: 'register',
        path: '/register',
        component: () => import('@/view/Register.vue')
    },
    {
        name: '404',
        path: '/404',
        component: () => import('@/view/common/HttpStatus404.vue')
    },
    {
        name: '403',
        path: '/403',
        component: () => import('@/view/common/HttpStatus403.vue')
    }
]

const router = createRouter({
    history: createWebHistory(),
    // history: createWebHashHistory(),
    routes
})

router.beforeEach((to, from , next)=>{
    if(to.name==='index'||to.name==='register'||to.name==='login'||to.name==='403'||to.name==='404'||to.name==='province'
    ||to.name==='cmdv'||to.name==='cpdv'||to.name==='example') {
        next();
        return
    }
    chatterWithBackendVerifyWhetherTokenIsValid().then(res=>{
        if(res.code===CustomHttpStatus.NO_NEED_TO_FLUSH_TOKEN){
            next();
            return;
        }
        if(res.code===HttpStatus.SUCCESS){
            setToken(res.token);
            next();
        }
    }).catch(err=>{
        clearLocalStorage();
        next("/index")
    })
})

function clearLocalStorage(){
    setLocalStorage(KEY_USER_ID, null);
    setLocalStorage(KEY_ROLE, null);
    setLocalStorage(KEY_NICKNAME, null);
    setLocalStorage(KEY_AVATAR, null);
    setLocalStorage(KEY_EMAIL, null);
}

export default router
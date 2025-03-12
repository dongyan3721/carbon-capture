<!--
 * @author Santa Antilles
 * @description
 * @date 2024/2/17-19:39:34
 * -->
<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="200px" style="min-height: 100vh; background-color: rgb(22,46,86)">
        <div class="aside-head-info"
             style="width: 200px; height: 80px; align-items: center; justify-items: center; display: flex">
          <span
              style="color: #f9f9f9; display: flex; align-items: center; justify-content: center; width: 200px">个人中心</span>
        </div>
        <el-menu style="border: none" :default-active="currentUrl" router background-color="#162E56" text-color="#f9f9f9">
          <el-menu-item index="/self-center/index">
            <template #title>
              <el-icon>
                <User/>
              </el-icon>
              个人信息
            </template>
          </el-menu-item>
          <el-menu-item index="/self-center/my-industry">
            <template #title>
              <el-icon>
                <Edit/>
              </el-icon>
              我的数据
            </template>
          </el-menu-item>
          <el-menu-item index="/self-center/my-solution">
            <template #title>
              <el-icon>
                <Finished/>
              </el-icon>
              解决方案
            </template>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header style="box-shadow: 2px 0 6px rgba(0, 21, 41, .36); display: flex; align-items: center; justify-content: space-between">
          <el-breadcrumb :separator-icon="ArrowRight">
            <el-breadcrumb-item v-for="item in route.matched" :to="{path: item.path}">{{ item.meta.title }}</el-breadcrumb-item>
          </el-breadcrumb>
          <div class="avatar-container" style="align-items: center; display: flex; justify-content: flex-end">
            <el-dropdown style="display: flex; align-items: center; justify-content: flex-end" placement="bottom">
              <span class="el-dropdown-link" style="display: flex; align-items: center; cursor: pointer">
                <el-avatar :size="24" :src="avatarSrc" style="margin: 0 5px"/>
                {{ '欢迎您，' + nickname }}
              </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item>返回首页</el-dropdown-item>
                  <el-dropdown-item>退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </el-header>
        <el-main>

        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import {useRoute} from "vue-router/dist/vue-router";
import {ArrowRight, User, Edit, Finished} from "@element-plus/icons-vue";
import {ref} from "vue";
import {getLocalStorage, KEY_AVATAR, KEY_NICKNAME} from "@/utils/localStorge";
import {baseStaticRecourseAPI, NOW_ENVIRONMENT} from "@/config/baseAPIConfig";

let route = useRoute();
let currentUrl = ref(route.path);
let nickname = getLocalStorage(KEY_NICKNAME);

// 数据库存的东西是相对路径，这里方便切换环境
let avatarSrc = baseStaticRecourseAPI[NOW_ENVIRONMENT]+getLocalStorage(KEY_AVATAR)
</script>

<style scoped>
.is-active {
  color: #f9f9f9 !important;
  background-color: #47739a;
  border-radius: 20px;
  margin: 0 10px;
}

.el-menu-item {
  margin: 5px 10px !important;
}

.el-menu-item:hover {
  border-radius: 20px;
}
</style>
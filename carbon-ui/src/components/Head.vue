<template>
  <el-header>
    <el-row>
      <el-col :span="4">
        <div class="grid-content ep-bg-purple"/>
      </el-col>
      <el-col :span="8">
        <div class="row-item restaurant-img-text-holder" style="display: flex; align-items: center; justify-content: center">
          <router-link to="/index" style="display: flex; align-items: center; justify-content: center; text-decoration: none; color: var(--el-index-normal)">
            <img src="@/assets/restaurant.svg" alt="食堂点评"/>
            <p>上海理工大学食堂点评系统</p>
          </router-link>
        </div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content ep-bg-purple-light"/>
      </el-col>
      <el-col :span="4">
        <div class="grid-content ep-bg-purple"/>
      </el-col>
      <el-col :span="4">
        <div class="avatar-login-logout row-item" style="display: flex; align-items: center;">
          <router-link to="/top-manager" v-if="showTop">系统后台</router-link>
          <router-link to="/restaurant-manager" v-if="showRest">食堂后台</router-link>
          <router-link to="/login" v-if="showLoginOrRegister">登录</router-link>
          <router-link to="/register" v-if="showLoginOrRegister">注册</router-link>

          <el-dropdown v-if="!showLoginOrRegister">
        <span class="el-dropdown-link"
              style="display: flex; align-items: center; justify-content: center;">
          <el-avatar :size="24" :src="src" style="margin: 0 5px"/>
          {{'欢迎您，'+ nickname }}
        </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>个人中心</el-dropdown-item>
                <el-dropdown-item @click.native="quitLogin">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-col>
    </el-row>
  </el-header>
</template>

<script>
import {
  getLocalStorage,
  KEY_AVATAR,
  KEY_NICKNAME,
  KEY_RESTAURANT_ID,
  KEY_ROLE, KEY_USER_ID, KEY_USER_NAME,
  setLocalStorage
} from "@/utils/localStorge";
import {getToken, removeToken} from "@/utils/auth";
import {baseStaticRecourseAPI, NOW_ENVIRONMENT} from "../config/baseAPIConfig.js";
import router from "../router/index.js";

export default {
  name: "Head",
  data(){
    return {
      showTop: false,
      showRest: false,
      src: null,
      nickname: null,
      showLoginOrRegister: null
    }
  },
  created() {
    this.showTop = getLocalStorage(KEY_ROLE)=="00";
    this.showRest = (getLocalStorage(KEY_ROLE)=="01")&&(getLocalStorage(KEY_RESTAURANT_ID)!=null)
    this.showLoginOrRegister = getToken()==null;
    this.src = baseStaticRecourseAPI[NOW_ENVIRONMENT] + getLocalStorage(KEY_AVATAR);
    this.nickname = getLocalStorage(KEY_NICKNAME)
  },
  methods:{
    quitLogin(){
      removeToken();
      setLocalStorage(KEY_NICKNAME, null);
      setLocalStorage(KEY_AVATAR, null);
      setLocalStorage(KEY_RESTAURANT_ID, null);
      setLocalStorage(KEY_ROLE, null);
      setLocalStorage(KEY_USER_ID, null);
      setLocalStorage(KEY_USER_NAME, null);
      router.push("/index")
    }
  }
}
</script>

<style scoped>
.el-dropdown-link {
  cursor: pointer;
  /*color: var(--el-color-primary);*/
  font-size: 16px;
}
router-link {
  text-underline: none;
}
::v-deep .avatar-login-logout a{
  text-decoration: none;
  color: #1E2023FF;
  margin: 0 10px;
  font-size: 14px;
}
.restaurant-img-text-holder img{
  width: 60px;
  height: 60px;
}
.restaurant-img-text-holder p{
  margin: 0 0 0 20px;
  font-family: 黑体;
  font-size: 24px;
}
.row-item{
  min-height: 70px;
}
</style>
<template>
  <div class="head-component-container">
    <el-header>
      <el-row>
        <el-col :span="4">
          <div class="grid-content ep-bg-purple"/>
        </el-col>
        <el-col :span="8">
          <div class="row-item restaurant-img-text-holder" style="display: flex; align-items: center; justify-content: center">
            <router-link to="/index" style="display: flex; align-items: center; justify-content: center; text-decoration: none; color: var(--el-index-normal)">
              <img src="@/assets/restaurant.svg" alt="碳补集"/>
              <p>一站式碳捕集服务平台</p>
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
            <a :href="`${context}/self-center`" v-if="showExtensionFunction" class="avatar-to-render-navigator">个人中心</a>
            <a :href="`${context}/login`" v-if="showLoginOrRegister" class="avatar-to-render-navigator">登录</a>
            <a :href="`${context}/register`" v-if="showLoginOrRegister" class="avatar-to-render-navigator">注册</a>

            <el-dropdown v-if="!showLoginOrRegister">
        <span class="el-dropdown-link"
              style="display: flex; align-items: center; justify-content: center;">
          <el-avatar :size="24" :src="src" style="margin: 0 5px"/>
          {{'欢迎您，'+ nickname }}
        </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item @click.native="gotoSelfCenter">个人中心</el-dropdown-item>
                  <el-dropdown-item @click.native="quitLogin">退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </el-col>
      </el-row>
    </el-header>
  </div>
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
import router from "../router/index.js";

export default {
  name: "Head",
  data(){
    return {
      showTop: false,
      showRest: false,
      src: null,
      nickname: null,
      showLoginOrRegister: null,
      showExtensionFunction: false
    }
  },
  props:{
    context: String
  },
  created() {
    this.showLoginOrRegister = getToken()==null;
    this.showExtensionFunction = !this.showLoginOrRegister;
    this.src = getLocalStorage(KEY_AVATAR);
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
      location.reload();
    },
    gotoSelfCenter(){
      router.push("/self-center")
      window.open(`${this.context}/self-center`, 'newTab')
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
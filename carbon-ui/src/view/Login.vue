<!--
 * @author Santa Antilles
 * @description 登录页
 * @date 2024/1/24-00:11:04
 * -->
<template>
  <div class="page-container">
    <Verify :show="showVerify" @close="closeVerifyWindow" @success="handleVerifySuccess"></Verify>
    <div class="plain-color-panel">
      <div class="input-title">
        <h2>欢迎回来</h2>
      </div>
      <div class="form-main">
        <div class="left-decorate-image">
          <img src="@/assets/login-decorate.png" alt="登录认证">
        </div>
        <div class="separator-vertical-line">
          <div class="line-separator"/>
        </div>
        <div class="main-input">
          <el-form :model="form" ref="formRef" :inline="false" size="large" :rules="formRules">
            <el-form-item prop="email">
              <template #label>
                <el-icon size="large"><User/></el-icon>
              </template>
              <el-input v-model="form.email" placeholder="邮箱" clearable/>
            </el-form-item>
            <el-form-item prop="password">
              <template #label>
                <el-icon size="large"><Lock/></el-icon>
              </template>
              <el-input v-model="form.password" placeholder="密码" clearable type="password"/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="verifyAndLogin" size="large">登录</el-button>
              <p style="margin: 0 0 0 15px">
                还没有账号？去
                <span class="indication-to-register" @click="gotoRegister">
                  注册
                </span>
                或
                <span class="indication-to-register" @click="gotoIndex">
                  返回首页
                </span>
              </p>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {reactive, ref} from "vue";
import {Lock, User} from "@element-plus/icons-vue";
import router from "@/router";
import Verify from 'vue3-puzzle-vcode'
import {generalEmailValidator, generalValidatorJudgeIfEmpty} from "@/utils/common";
import {ElMessage} from "element-plus";
import {userLogin} from "@/web-api/login";
import {KEY_AVATAR, KEY_EMAIL, KEY_NICKNAME, KEY_ROLE, KEY_USER_ID, setLocalStorage} from "@/utils/localStorge";
import {setToken} from "@/utils/auth";
import {baseStaticRecourseAPI, NOW_ENVIRONMENT} from "@/config/baseAPIConfig";

let form = reactive({
  email: null,
  password: null,
  verification: null
})
let formRef = ref()

const resetForm = ()=>{
  if(!formRef.value){
    return
  }
  formRef.value.resetFields();
}

let formRules = reactive({
  email: [
    {
      validator: generalEmailValidator('邮箱号'), trigger: 'blur'
    }
  ],
  password: [
    {
      validator: generalValidatorJudgeIfEmpty('密码'), trigger: 'blur'
    }
  ]
})

const verifyAndLogin = ()=>{
  showVerify.value = true
}

const gotoRegister = ()=>{
  router.push('/register')
}

const gotoIndex = () => {
  router.push('/index')
}

let showVerify = ref(false)

const closeVerifyWindow = ()=>{
  showVerify.value = false
}

// 验证成功后下一步操作
const handleVerifySuccess = ()=>{
  showVerify.value = false
  submitForm();
}

// 提交表单
const submitForm = ()=>{
  if(!formRef){
    ElMessage({
      message: '请填入用户名和密码！',
      type: 'warning'
    })
    return
  }
  formRef.value.validate(valid=>{
    if(valid){
      console.log(valid)
      userLogin(form).then(response=>{
        if(response.code===200){
          setProperties(response);
          router.push('/index')
        }else{
          ElMessage.error('用户名或密码错误！')
          resetForm()
        }
      })
    }
  })
}

// 写入必要信息
const setProperties = (response)=>{
  let data = response.data
  setLocalStorage(KEY_USER_ID, data.userId)
  setLocalStorage(KEY_AVATAR, baseStaticRecourseAPI[NOW_ENVIRONMENT]+data.avatar);
  setLocalStorage(KEY_ROLE, data.userType);
  setLocalStorage(KEY_NICKNAME, data.nickname);
  setLocalStorage(KEY_EMAIL, data.email);
  setToken(response.token)
}

</script>

<style scoped>
.page-container{
  background-image: url("../assets/login-register-background.jpg");
  background-repeat: no-repeat;
  height: 100vh;
  width: 100vw;
  display: flex;
  align-items: center;
  justify-content: center;
}
.plain-color-panel{
  height: 40%;
  width: 38%;
  min-width: 600px;
  min-height: 300px;
  background-color: rgba(255, 255, 255, 0.6);
  backdrop-filter: blur(10px);
  border-radius: 10px;
  position: relative;
}
.plain-color-panel .input-title{
  width: 90%;
  height: 10%;
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  left: 50%;
  top: 8%;
  transform: translate(-50%, -50%);
}
.plain-color-panel .form-main{
  width: 90%;
  height: 82%;
  position: absolute;
  left: 50%;
  top: 55%;
  transform: translate(-50%, -50%);
  display: flex;
  justify-content: space-around;
}
.form-main .left-decorate-image{
  width: 29%;
  height: 95%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.form-main .left-decorate-image img{
  width: 100%;
  height: 100%;
  margin: 3px;
  border-radius: 10px;
}
.form-main .separator-vertical-line{
  width: 10%;
  height: 95%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.form-main .separator-vertical-line .line-separator{
  height: 95%;
  width: 6px;
  background-color: #dedede70;
}
.form-main .main-input{
  width: 58%;
  height: 95%;
  display: flex;
  justify-content: flex-start;
  align-items: center;
}
.indication-to-register:hover{
  text-decoration: underline;
  color: #777777;
  cursor: pointer;
}
::v-deep .el-form-item__label{
  align-items: center !important;
}
</style>
<!--
 * @author Santa Antilles
 * @description 个人中心
 * @date 2024/1/24-01:08:35
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
              我的行业
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
                  <el-dropdown-item @click.native="gotoIndex">返回首页</el-dropdown-item>
                  <el-dropdown-item @click.native="quitLogin">退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </el-header>
        <el-main>
          <el-dialog v-model="cropVis" @close="handleCloseCropDialog" width="600px">
            <template #header>
              <el-icon color="#666666" size="large">
                <Scissor/>
              </el-icon>
              <span style="color: #666666">头像上传裁剪</span>
            </template>
            <el-row>
              <VueCropper style="min-width: 560px; min-height: 300px" ref="cropperRef" :img="cropOptions.img" :outputSize="cropOptions.outputSize"
                          :outputType="cropOptions.outputType" :info="cropOptions.info" :canScale="cropOptions.canScale" :autoCrop="cropOptions.autoCrop"
                          :autoCropWidth="cropOptions.autoCropWidth" :autoCropHeight="cropOptions.autoCropHeight" :fixedBox="cropOptions.fixedBox"
                          :fixed="cropOptions.fixed" :fixedNumber="cropOptions.fixedNumber" :canMove="cropOptions.canMove" :canMoveBox="cropOptions.canMoveBox"
                          :original="cropOptions.original" :centerBox="cropOptions.centerBox" :infoTrue="cropOptions.infoTrue" :full="cropOptions.full"
                          :enlarge="cropOptions.enlarge" :mode="cropOptions.mode" :key="cnt">
              </VueCropper>
            </el-row>
            <el-row style="width: 100%; height: 60px; margin-top: 10px;">
              <el-button type="primary" plain @click="rotateLeft">←向左旋转图片</el-button>
              <el-button type="primary" plain @click="rotateRight">向右旋转图片→</el-button>
              <el-button type="primary" @click="getCropDataBase64">获取截取的图片</el-button>
            </el-row>
            <hr>
            <div>
              头像预览
            </div>
            <div style="width: 600px; height: auto; display: flex; align-items: center; justify-content: center">
              <!-- 若图片只设置宽度，可以保持等比例展示图片 -->
              <img :src="previewUrl" style="width: 50px; height: 50px" alt="preview" v-show="showPreview">
              <div v-show="!showPreview">
                <img src="@/assets/observe.png" alt="observe" height="44" width="88"/>
                <span>还没有选择图片哦</span>
              </div>
            </div>
            <template #footer>
              <el-button type="primary" @click="transferUploadedToBuffer" :disabled="!showPreview">确定</el-button>
            </template>
          </el-dialog>
          <!--主页面内头像预览对话框-->
          <el-dialog v-model="bodyPreviewDialogVis" @close="handleBodyPreviewDialogClose" :width="bodyPreviewDialogWidth" append-to-body>
            <img :src="dialogUploadFileList[0].url" alt="preview" :height="dialogImageHeight" :width="dialogImageWidth"/>
            <template #header><span style="color: #666666">头像预览</span></template>
          </el-dialog>
          <el-form :model="modifyForm" ref="modifyFormRef" :inline="false" size="large" :rules="modifyFormRules">
            <el-form-item>
              <el-upload
                  v-model:file-list="dialogUploadFileList"
                  ref="bodyUploadRef"
                  list-type="picture-card"
                  :on-preview="handlePictureCardPreview"
                  :on-exceed="handleExceed"
                  :on-remove="handleAvatarRemove"
                  :on-change="handleAvatarInitialUpload"
                  :before-remove="handleRejectRemove"
                  :auto-upload="false"
                  :limit="1">
                <template #default>
                  <el-icon><Plus/></el-icon>
                </template>
              </el-upload>
              <template #label>
                <el-icon size="large" color="#666666"><Avatar/></el-icon>
                <span>我的头像</span>
              </template>
            </el-form-item>
            <el-form-item prop="nickname">
              <template #label>
                <el-icon size="large" color="#666666"><User/></el-icon>
                <span>我的昵称</span>
              </template>
              <el-input v-model="modifyForm.nickname" clearable/>
            </el-form-item>
            <el-form-item prop="email">
              <template #label>
                <el-icon size="large" color="#666666"><Message/></el-icon>
                <span>绑定邮箱</span>
              </template>
              <el-input v-model="modifyForm.email" clearable/>
            </el-form-item>
            <el-form-item>
              <el-button type="danger" @click="submitModify(modifyFormRef)" size="large">确认修改</el-button>
              <el-button type="primary" @click="resetModifyForm" size="large">重置</el-button>
              <el-button type="warning" @click="openPasswordModifyDialog" size="large">修改密码</el-button>
            </el-form-item>
          </el-form>
          <el-dialog width="400px" append-to-body v-model="modifyPasswordDialogVis" @close="closePasswordModifyDialog">
            <template #header>
              <span style="color:#666666;">修改密码</span>
            </template>
            <el-form :rules="modifyPasswordFormRules" :model="modifyPasswordFrom" :inline="false" size="default" ref="modifyPasswordFromRef">
              <el-form-item label="原密码" prop="originalPassword">
                <el-input type="password" v-model="modifyPasswordFrom.originalPassword" clearable/>
              </el-form-item>
              <el-form-item label="新密码" prop="newPassword">
                <el-input type="password" v-model="modifyPasswordFrom.newPassword" clearable/>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="submitModifyPassword(modifyPasswordFromRef)">确认修改</el-button>
              </el-form-item>
            </el-form>
          </el-dialog>
<!--          <el-button type="primary" @click="openCropDialog" size="large">触发</el-button>-->
<!--          <el-button type="primary" @click="enforceChangePicture" size="large">变图片</el-button>-->
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import {useRoute} from "vue-router/dist/vue-router";
import {ArrowRight, User, Edit, Finished, Scissor, Plus, Avatar, Message} from "@element-plus/icons-vue";
import {reactive, ref} from "vue";
import {VueCropper} from "vue-cropper";
import {getLocalStorage, KEY_AVATAR, KEY_EMAIL, KEY_NICKNAME, KEY_USER_ID, setLocalStorage} from "@/utils/localStorge";
import {baseStaticRecourseAPI, NOW_ENVIRONMENT} from "@/config/baseAPIConfig";
import {base64ToFile, compressImage, handleDataSubmitWithBlob} from "@/utils/dongyan";
import {ElMessage, genFileId} from "element-plus";
import {generalEmailValidator, nicknameLengthValidator} from "@/utils/common";
import {modifyAvatar, modifyPassword, modifyUser} from "@/web-api/center";

// 测试改变图片 pass
/**const enforceChangePicture = ()=>{
  dialogUploadFileList.value = [
    {
      name: 'bbdc.png',
      url: 'https://linuxidc.loloya.cn/linuxidc/202306/ed272a84549e01f2c9efcc5154222641.png'
    },
    {
      name: 'bbdc.png',
      url: 'https://linuxidc.loloya.cn/linuxidc/202306/ed272a84549e01f2c9efcc5154222641.png'
    }
  ]
}*/

const submitModifyPassword = (formRef)=>{
  formRef.validate(valid=>{
    if(valid){
      modifyPassword(modifyPasswordFrom).then(res=>{
        if(res.code===200){
          ElMessage.success('修改成功！');
          closePasswordModifyDialog();
        }
      })
    }
  })
}
const modifyPasswordFormRules = reactive({
  originalPassword: [{validator: nicknameLengthValidator('原密码', 16, 6), trigger: 'blur'}],
  newPassword: [{validator: nicknameLengthValidator('新密码', 16, 6), trigger: 'blur'}]
})
// 修改密码表单
let modifyPasswordFrom = reactive({
  userId: getLocalStorage(KEY_USER_ID),
  originalPassword: '',
  newPassword: ''
})
// 重置密码表单引用
let modifyPasswordFromRef = ref();
// 重置密码对话框表单重置
const resetModifyPasswordFrom = ()=>{
  if(!modifyPasswordFromRef.value){
    return
  }
  modifyPasswordFromRef.value.resetFields();
  modifyPasswordFrom.userId = getLocalStorage(KEY_USER_ID);
  modifyPasswordFrom.originalPassword = null
  modifyPasswordFrom.newPassword = null
}
// 修改密码对话框打开
const openPasswordModifyDialog = ()=>{
  modifyPasswordDialogVis.value = true;
}
// 修改密码对话框关闭
const closePasswordModifyDialog = ()=>{
  modifyPasswordDialogVis.value = false;
  resetModifyPasswordFrom();
}
// 控制修改密码对话框可见性
let modifyPasswordDialogVis = ref(false);
// 最终提交修改结果
const submitModify = (formRef)=>{
  formRef.validate(valid=>{
    if(valid){
      if(recordWhetherAvatarHadBeenModified.value){
        // 头像也被修改了，调用头像修改
        let formData = new FormData()
        formData.append('user', handleDataSubmitWithBlob(JSON.stringify(modifyForm)));
        formData.append('avatar', base64ToFile(previewUrl.value, 'avatar.png'));
        modifyAvatar(formData).then(res=>{
          if(res.code===1000){
            ElMessage.error('修改失败！邮箱已被绑定！')
            return
          }
          setLocalStorage(KEY_NICKNAME, modifyForm.nickname);
          setLocalStorage(KEY_EMAIL, modifyForm.email);
          setLocalStorage(KEY_AVATAR, previewUrl.value);
          resetModifyForm();
          ElMessage.success('修改成功！')
        })
      }else{
        // 头像没有被修改，只做了用户名和绑定邮箱号的修改
        modifyUser(modifyForm).then(res=>{
          if(res.code===1000){
            ElMessage.error('修改失败！邮箱已被绑定！')
            return
          }
          setLocalStorage(KEY_NICKNAME, modifyForm.nickname);
          setLocalStorage(KEY_EMAIL, modifyForm.email);
          resetModifyForm();
          ElMessage.success('修改成功！')
        })
      }
    }
  })
}

let recordWhetherAvatarHadBeenModified = ref(false)
let dialogImageHeight = ref('50px')
let dialogImageWidth = ref()
let bodyPreviewDialogWidth = ref()

// 动态调整图像预览框的大小
function adjustDialogImageWidthAndHeight() {
  // console.log(fileList.value[0])
  let imageUrl = dialogUploadFileList.value[0].url;
  let image = new Image();
  image.src = imageUrl;
  dialogImageWidth.value = `${50 * image.width / image.height}px`;
  bodyPreviewDialogWidth.value = `${200 * image.width / image.height + 50}px`
}
// 控制是否需要替换
let replaceOriginalAvatarFlag = ref(false)
// 控制页面内预览对话框可见性
let bodyPreviewDialogVis = ref(false);
// 关闭主页面预览对话框
const handleBodyPreviewDialogClose = ()=>{
  bodyPreviewDialogVis.value = false
}
// body上传框引用
let bodyUploadRef = ref()
// 图片预览
const handlePictureCardPreview = () => {
  // 固定对话框高度为50，宽度成比例
  adjustDialogImageWidthAndHeight()
  bodyPreviewDialogVis.value = true
}
// 选取图片超出限制时触发，自动去掉最前一个上传的图片，保留最新上传的图
// 此处做特殊处理，因为这边需要对图像进行裁剪，所以不需要触发on-change钩子，在on-exceed这里处理即可
// files为上传的文件的列表，就算只上传一个文件也是一个列表
const handleExceed = (files) => {
  if(!replaceOriginalAvatarFlag.value){
    cropOptions.img = URL.createObjectURL(files[0]);
    cropVis.value = true
    return
  }
  bodyUploadRef.value.clearFiles()
  const file = files[0]
  file.uid = genFileId()
  bodyUploadRef.value.handleStart(file)
}
const handleAvatarInitialUpload = (ev)=>{
  cropOptions.img = URL.createObjectURL(ev.raw);
  cropVis.value = true
  dialogUploadFileList.value = []
}
// 拒绝删除
const handleRejectRemove = file=>{
  return false
}
// 由于只留一张，所以删除的时候认为头像没有被修改
const handleAvatarRemove = (file)=>{
  recordWhetherAvatarHadBeenModified.value = false
}



// 定义修改表单
let modifyForm = reactive({
  userId: getLocalStorage(KEY_USER_ID), // 不绑定任何表单填写项
  nickname: getLocalStorage(KEY_NICKNAME),
  // userType: 本人不可见
  // avatar: 显示在FileUpload框里面
  email: getLocalStorage(KEY_EMAIL)
})
// 表单引用
let modifyFormRef = ref()
// 表单重置
let resetModifyForm = () =>{
  if(!modifyFormRef.value){
    return
  }
  modifyFormRef.value.resetFields();
  modifyForm.email = getLocalStorage(KEY_EMAIL);
  modifyForm.nickname = getLocalStorage(KEY_NICKNAME);
  modifyForm.userId = getLocalStorage(KEY_USER_ID);
  dialogUploadFileList.value = [{
    name: 'bbdc.png',
    url: getLocalStorage(KEY_AVATAR)
  }]
}
// 表单验证规则
const modifyFormRules = reactive({
  nickname: [{validator: nicknameLengthValidator('用户名', 32, 1), trigger: 'blur'}],
  email: [{validator: generalEmailValidator('绑定邮箱'), trigger: 'blur'}]
})



// 裁剪框的配置
let cropOptions = reactive({
  img: '', // 裁剪图片的地址 url 地址, base64, blob
  outputSize: 1, // 裁剪生成图片的质量
  outputType: 'png', // 裁剪生成图片的格式 jpeg, png, webp
  info: false, // 裁剪框的大小信息
  canScale: true, // 图片是否允许滚轮缩放
  autoCrop: true, // 是否默认生成截图框
  autoCropWidth: 50, // 默认生成截图框宽度
  autoCropHeight: 50, // 默认生成截图框高度
  fixedBox: false, // 固定截图框大小 不允许改变
  fixed: true, // 是否开启截图框宽高固定比例，这个如果设置为true，截图框会是固定比例缩放的，如果设置为false，则截图框的狂宽高比例就不固定了
  fixedNumber: [1, 1], // 截图框的宽高比例 [ 宽度 , 高度 ]
  canMove: true, // 上传图片是否可以移动
  canMoveBox: true, // 截图框能否拖动
  original: false, // 上传图片按照原始比例渲染
  centerBox: true, // 截图框是否被限制在图片里面
  infoTrue: true, // true 为展示真实输出图片宽高 false 展示看到的截图框宽高
  full: true, // 是否输出原图比例的截图
  enlarge: '1', // 图片根据截图框输出比例倍数
  mode: 'contain' // 图片默认渲染方式 contain , cover, 100px, 100% auto
})
let cnt = ref(0)
// 打开头像裁剪对话框
const openCropDialog = ()=>{
  cropVis.value = true
}
// 关闭头像裁剪对话框
const handleCloseCropDialog = ()=>{
  cropVis.value = false
  cnt = ref(++cnt.value)
}
// 裁剪框可见性
let cropVis = ref(false);
// 裁剪框引用
let cropperRef = ref()
// 裁剪框上传部分图片列表，限一张
let dialogUploadFileList = ref([{
  name: 'test.png',
  url: getLocalStorage(KEY_AVATAR)
}])
const rotateLeft = () => {
  // 这个默认是旋转90度的，官网有说明
  // 后面会完善不要让这个一下子旋转90度的代码
  cropperRef.value.rotateLeft()
}
// 预览框内图像src
let previewUrl = ref();
// 是否预览
let showPreview = ref(false)
// 向右旋转
const rotateRight = () => {
  // 这个默认也是旋转90度的，官网有说明
  // 后面会完善不要让这个一下子旋转90度的代码
  cropperRef.value.rotateRight()
}
// 获取图片，在预览框内加载
const getCropDataBase64 = () => {
  // 这个是获取base64的图片
  cropperRef.value.getCropData((data) => {
    // do something
    // 如这里，可以获取上传base64位的图片给服务器
    // 也可以将base64位的图片转化为file文件，然后通过form表单的形式提交给后端，让后端返回一个图片的访问地址等
    // 这里就简单把截取到的图片展示一下吧，这里就暂时不上传给后端了
    compressImage(data).then(b64=>{
      previewUrl.value = b64;
      console.log(b64);
      showPreview.value = true
    }).catch(err=>{
      console.log(err)
    })
  })
}
// 将确认上传的新头像放在缓冲区
const transferUploadedToBuffer = ()=>{
  recordWhetherAvatarHadBeenModified.value = true
  dialogUploadFileList.value = [
    {
      name: 'newAvatar.png',
      url: previewUrl.value
    }
  ]
  cropVis.value = false;
}


let route = useRoute();
let currentUrl = ref(route.path);
let nickname = getLocalStorage(KEY_NICKNAME);

// 数据库存的东西是相对路径，这里方便切换环境
let avatarSrc = baseStaticRecourseAPI[NOW_ENVIRONMENT]+getLocalStorage(KEY_AVATAR);

const gotoIndex = ()=>{

}

const quitLogin = ()=>{

}
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

::v-deep .el-dialog__header{
  display: flex;
  justify-content: flex-start;
  align-items: center;
}

::v-deep .el-form-item__label{
  display: flex;
  align-items: center;
}
</style>
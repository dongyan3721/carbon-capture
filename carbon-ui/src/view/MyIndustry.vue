<!--
 * @author Santa Antilles
 * @description
 * @date 2024/2/17-19:39:52
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
                  <el-dropdown-item>返回首页</el-dropdown-item>
                  <el-dropdown-item>退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </el-header>
        <el-main>
          <el-form :model="myBelongForm" :inline="false" ref="myBelongFormRef" :rules="myBelongFormRules">
            <el-form-item label="我的行业" prop="industry">
              <el-cascader :options="dict_industry_belong" placeholder="请选择行业与行业环节" v-model="industryAndIndustryProcess" @change="currentlyChangeFormData" clearable/>
            </el-form-item>
            <el-form-item label="我的地理位置" prop="provinceId">
              <el-select v-model="myBelongForm.provinceId" clearable placeholder="请选择省份">
                <el-option v-for="province in provinces"
                           :value="province.value"
                           :key="province.value"
                           :label="province.label"/>
              </el-select>
            </el-form-item>
            <el-form-item label="年均大致碳排放量" prop="blurCarbonOutput">
              <el-input v-model="myBelongForm.blurCarbonOutput" clearable placeholder="单位：吨"/>
            </el-form-item>
            <el-form-item>
              <el-button type="danger" @click="submitMyBelongForm(myBelongFormRef)">提交</el-button>
              <el-button type="primary" @click="cancelMyBelongForm">重置</el-button>
            </el-form-item>
          </el-form>
          <hr/>
          <p class="recommend-hint">根据您的行业，为您推荐以下可行捕集方案</p>
          <el-table :data="solutionTableData" v-loading="solutionTableDataLoading" :border="true">
            <template #empty>
              <p class="no-data-hint">请选择行业后再行查看</p>
            </template>
            <el-table-column prop="method" label="捕集方法"/>
            <el-table-column prop="gasOutput" label="烟气排量"/>
            <el-table-column prop="captureEnergyExpenditure" label="捕集能耗"/>
            <el-table-column prop="referenceEssay" label="下载查看">
              <template #default="scope">
                <el-button text @click="downloadEssay(scope.row)" size="small">下载</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import {useRoute} from "vue-router/dist/vue-router";
import {ArrowRight, User, Edit, Finished} from "@element-plus/icons-vue";
import {onBeforeMount, reactive, ref} from "vue";
import {getLocalStorage, KEY_AVATAR, KEY_NICKNAME, KEY_USER_ID} from "@/utils/localStorge";
import {baseStaticRecourseAPI, NOW_ENVIRONMENT} from "@/config/baseAPIConfig";
import {
  addUserBelong,
  modifyUserBelong,
  requestAllProvinces,
  requestRecommendedCarbonSolution,
  requestUserBelongDetail
} from "@/web-api/center-extend";
import {ElMessage} from "element-plus";
import {dict_industry_belong, generalNumericValidator, generalValidatorJudgeIfEmpty} from "@/utils/common";

const userId = getLocalStorage(KEY_USER_ID);


let industryAndIndustryProcess = ref([])

const submitMyBelongForm = (fromRef)=>{
  fromRef.validate(valid=>{
    if(valid){
      myBelongForm.provinceName = provinceReflect[myBelongForm.provinceId];
      if(myBelongForm.belongId){
        modifyUserBelong(myBelongForm).then(res=>{
          ElMessage.success('修改成功！')
          // solutionTableDataLoading.value = true;
          // requestSpecificSolution(userId);
        })
      }else{
        addUserBelong(myBelongForm).then(res=>{
          ElMessage.success('记录添加成功！');
          // solutionTableDataLoading.value = true
          requestUserBelong(false);
        })
      }
    }
  })
}

// 级联选择框内值发生改变时，修改表单数据
let currentlyChangeFormData = (value)=>{
  try{
    myBelongForm.industry = value[0]
    myBelongForm.industryProcess = value[1]
  }catch (e){
    myBelongForm.industry = null
    myBelongForm.industryProcess = null
  }
  console.log(value)
}

// 我的行业以及行业环节、地理位置
let myBelongForm = reactive({
  belongId: null,
  userId: getLocalStorage(KEY_USER_ID),
  userNickname: getLocalStorage(KEY_NICKNAME),
  provinceId: null,
  provinceName: null,
  industry: null,
  industryProcess: null,
  blurCarbonOutput: null
})
const cancelMyBelongForm = ()=>{
  resetMyBelongForm();
  requestUserBelong(false);
}
const resetMyBelongForm = ()=>{
  if(!myBelongFormRef.value){
    return
  }
  myBelongFormRef.value.resetFields()
  myBelongForm.belongId = null
  myBelongForm.userId = getLocalStorage(KEY_USER_ID)
  myBelongForm.userNickname = getLocalStorage(KEY_NICKNAME)
  myBelongForm.provinceName = null
  myBelongForm.industry = null
  myBelongForm.industryProcess = null
}
const myBelongFormRules = {
  industry: [{validator: generalValidatorJudgeIfEmpty('所属行业'), trigger: 'blur'}],
  blurCarbonOutput: [
      {
        validator: generalNumericValidator('碳排量'), trigger: 'blur'
      },
      {
        validator: generalValidatorJudgeIfEmpty('碳排量'), trigger: 'blur'
      }
    ],
  provinceId: [{validator: generalValidatorJudgeIfEmpty('所属地域'), trigger: 'blur'}]
}
// 我的行业表单引用
let myBelongFormRef = ref();
// 存储省份的选择
let provinces = ref([]);
// 省份名和id的映射
let provinceReflect = reactive({})


// 存储推荐的解决方案
let solutionTableData = ref([])
// python跑模型可能比较慢，遮罩一下解决方案的表格
let solutionTableDataLoading = ref(false);
// 下载参考方法
const downloadEssay = (row)=>{

}


// 页面加载时要干的事
function checkWhetherUserHadChosenHisBelong(){
  // solutionTableDataLoading.value = true
  requestAllProvinces().then(res=>{
    res.rows.map(province=>{
      provinces.value.push({
        label: province.regionName,
        value: province.id
      })
      provinceReflect[province.id] = province.regionName
    })
  })
  requestUserBelong(true);
}

// 请求解决方案
function requestSpecificSolution(userId){
  requestRecommendedCarbonSolution(userId).then(res=>{
    solutionTableData.value = res.rows;
    solutionTableDataLoading.value = false;
  }).catch(err=>console.error('network error', err))
}

// 请求用户行业，参数是是否需要提醒用户选择
function requestUserBelong(requireRemind){
  requestUserBelongDetail(userId).then(res=>{
    if(res.data){
      myBelongForm.belongId = res.data.belongId
      myBelongForm.userId = res.data.userId
      myBelongForm.userNickname = res.data.userNickname
      myBelongForm.provinceId = res.data.provinceId
      myBelongForm.provinceName = res.data.provinceName
      myBelongForm.industry = res.data.industry
      myBelongForm.industryProcess = res.data.industryProcess
      myBelongForm.blurCarbonOutput = res.data.blurCarbonOutput
      industryAndIndustryProcess.value = []
      industryAndIndustryProcess.value.push(res.data.industry)
      industryAndIndustryProcess.value.push(res.data.industryProcess)
      // 填了行业信息，那么继续去请求对应的解决方案
      // requestSpecificSolution(userId);
    }else{
      if(requireRemind){
        ElMessage.warning('请先在本页面完成行业信息录入再使用本功能！')
        solutionTableDataLoading.value = false
      }
    }
  })
}


onBeforeMount( ()=>{
  checkWhetherUserHadChosenHisBelong();
})

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
.recommend-hint{
  font-family: "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, Arial, "Lucida Grande", sans-serif;
  font-weight: normal;
  color: #606266;
  font-size: 14px;
  cursor: default;
}
.no-data-hint{
  font-family: "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, Arial, "Lucida Grande", sans-serif;
  color: #a8abb2;
  font-size: 18px;
  user-select: none;
}
</style>
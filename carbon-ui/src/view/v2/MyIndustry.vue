<!--
  *@description      
  *@author          29145
  *@create          2025-03-12 21:10
-->
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
                  <el-dropdown-item @click.native="gotoIndex">返回首页</el-dropdown-item>
                  <el-dropdown-item @click.native="quitLogin">退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </el-header>
        <el-main>
          <el-dialog v-model="mustShow">
            <template #title>
              <span>必填项填写</span>
            </template>

            <el-form :model="form" label-width="240px">

              <universe-section title="基本信息"/>

              <el-form-item label="区位">
                <el-cascader
                    v-model="form.location"
                    :options="options"
                    placeholder="请选择省份和城市"
                />
              </el-form-item>

              <el-form-item label="企业性质">
                <el-select v-model="form.industryType" placeholder="请选择">
                  <el-option label="电" value="electricity"></el-option>
                  <el-option label="钢" value="steel"></el-option>
                  <el-option label="水泥" value="cement"></el-option>
                  <el-option label="化工" value="chemical"></el-option>
                </el-select>
              </el-form-item>

              <universe-section title="工质参数"/>
              <el-form-item label="烟气量">
                <el-input v-model="form.flueGasVolume"></el-input>
              </el-form-item>
              <el-form-item label="烟气温度">
                <el-input v-model="form.flueGasTemperature"></el-input>
              </el-form-item>
              <el-form-item label="烟气压力">
                <el-input v-model="form.flueGasPressure"></el-input>
              </el-form-item>

              <universe-section title="捕集要求"/>
              <el-form-item label="捕集效率">
                <el-input v-model="form.captureEfficiency"></el-input>
              </el-form-item>
              <el-form-item label="分离纯度">
                <el-input v-model="form.separationPurity"></el-input>
              </el-form-item>
              <el-form-item label="储存压力">
                <el-input v-model="form.storagePressure"></el-input>
              </el-form-item>
              <el-form-item label="储存温度">
                <el-input v-model="form.storageTemperature"></el-input>
              </el-form-item>
              <el-form-item label="年运行时长">
                <el-input v-model="form.annualOperationTime"></el-input>
              </el-form-item>

              <el-form-item>
                <el-button type="primary">提交</el-button>
                <el-button type="warning">取消</el-button>
              </el-form-item>

            </el-form>

          </el-dialog>

          <el-dialog v-model="factoryInShow">
            <template #title>
              <span>企业内资源整合</span>
            </template>
            <el-form :model="form" label-width="240px">
              <el-form-item label="电参数">
                <el-input v-model="form.electricityParameters"></el-input>
              </el-form-item>

              <el-form-item label="热参数">
                <el-input v-model="form.heatParameters"></el-input>
              </el-form-item>

              <el-form-item label="水参数">
                <el-input v-model="form.waterParameters"></el-input>
              </el-form-item>

              <el-form-item>
                <el-button type="primary">提交</el-button>
                <el-button type="warning">取消</el-button>
              </el-form-item>
            </el-form>

          </el-dialog>

          <el-dialog v-model="carbonEliminateShow">
            <template #title>
              <span>碳消纳渠道整合</span>
            </template>
            <el-form :model="form" label-width="240px">
              <el-form-item label="合作企业碳消纳能力(吨/年)">
                <el-input v-model="form.cooperatedCarbonEatingCount"/>
              </el-form-item>
              <el-form-item>
                <el-button type="primary">提交</el-button>
                <el-button type="warning">取消</el-button>
              </el-form-item>
            </el-form>

          </el-dialog>

          <el-dialog v-model="locationShow">
            <template #title>
              <span>区位资源整合</span>
            </template>

            <el-form :model="form" label-width="240px">
              <el-form-item label="热整合">
                <el-radio-group v-model="form.buyThermal">
                  <!-- works when >=2.6.0, recommended ✔️ not work when <2.6.0 ❌ -->
                  <el-radio :value="true">直接买热</el-radio>
                  <!-- works when <2.6.0, deprecated act as value when >=3.0.0 -->
                  <el-radio :value="false">区位资源发热</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="电整合">
                <el-radio-group v-model="form.buyElectricity">
                  <!-- works when >=2.6.0, recommended ✔️ not work when <2.6.0 ❌ -->
                  <el-radio :value="true">直接买电</el-radio>
                  <!-- works when <2.6.0, deprecated act as value when >=3.0.0 -->
                  <el-radio :value="false">区位资源发电</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item>
                <el-button type="primary">提交</el-button>
                <el-button type="warning">取消</el-button>
              </el-form-item>

            </el-form>


          </el-dialog>

          <el-row :gutter="30">
            <el-col :span="12">
              <div class="bg-cyan-300 rounded-xl flex flex-col cursor-pointer p-3
      justify-center items-center hover:shadow-lg
      hover:shadow-black/50 transition-shadow duration-300" @click="()=>{mustShow = true}">
                <el-icon :size="144" color="white">
                  <Plus/>
                </el-icon>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="bg-green-300 rounded-xl flex flex-col cursor-pointer p-3
      justify-center items-center hover:shadow-lg
      hover:shadow-black/50 transition-shadow duration-300" @click="()=>{factoryInShow = true}">
                <el-icon :size="144" color="white">
                  <Plus/>
                </el-icon>
              </div>
            </el-col>
          </el-row>


          <el-row :gutter="30">
            <el-col :span="12">
              <div class="w-100 flex p-3 items-center justify-center">
                <n-text class="text-xl text-gray-500">必填项内容</n-text>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="w-100 flex p-3 items-center justify-center">
                <n-text class="text-xl text-gray-500">企业内部资源整合</n-text>
              </div>
            </el-col>
          </el-row>



          <el-row :gutter="30">
            <el-col :span="12">
              <div class="bg-orange-400 rounded-xl flex flex-col cursor-pointer p-3
      justify-center items-center hover:shadow-lg
      hover:shadow-black/50 transition-shadow duration-300" @click="()=>{locationShow = true}">
                <el-icon :size="144" color="white">
                  <Plus/>
                </el-icon>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="bg-purple-400 rounded-xl flex flex-col cursor-pointer p-3
      justify-center items-center hover:shadow-lg
      hover:shadow-black/50 transition-shadow duration-300"  @click="()=>{carbonEliminateShow = true}">
                <el-icon :size="144" color="white">
                  <Plus/>
                </el-icon>
              </div>
            </el-col>
          </el-row>


          <el-row :gutter="30">
            <el-col :span="12">
              <div class="w-100 flex p-3 items-center justify-center">
                <n-text class="text-xl text-gray-500">区位资源整合</n-text>
              </div>
            </el-col>
            <el-col :span="12">
              <div class="w-100 flex p-3 items-center justify-center">
                <n-text class="text-xl text-gray-500">碳消纳方案整合</n-text>
              </div>
            </el-col>
          </el-row>

          <el-row :gutter="30">
            <el-col :span="6"/>

            <el-col :span="12">
              <div class="w-100 flex p-3 items-center justify-center bg-red-500 cursor-pointer rounded-2xl
                hover:shadow-lg hover:shadow-black/50 transition-shadow duration-300">
                <n-text class="text-xl text-white">生成最终报告</n-text>
              </div>
            </el-col>

            <el-col :span="6"/>
          </el-row>

<!--          <div style="width: 500px; padding: 15px">-->
<!--            -->
<!--          </div>-->
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import {useRoute} from "vue-router/dist/vue-router";
import {ArrowRight, User, Edit, Finished, Plus} from "@element-plus/icons-vue";
import {onBeforeMount, ref} from "vue";
import {
  getLocalStorage,
  KEY_AVATAR,
  KEY_NICKNAME,
  KEY_RESTAURANT_ID, KEY_ROLE,
  KEY_USER_ID, KEY_USER_NAME,
  setLocalStorage
} from "@/utils/localStorge";

import router from "@/router";
import {removeToken} from "@/utils/auth";
import UniverseSection from "@/components/universe-section.vue";



const form = ref({
  location: '',
  industryType: '',
  flueGasVolume: '',
  flueGasTemperature: '',
  flueGasPressure: '',
  captureEfficiency: '',
  separationPurity: '',
  storagePressure: '',
  storageTemperature: '',
  annualOperationTime: '',
  integrateResources: false,
  electricityParameters: '',
  heatParameters: '',
  waterParameters: '',
  buyElectricity: true,
  buyThermal: true,
  cooperatedCarbonEatingCount: ''
});

const onSubmit = () => {
  console.log('Form submitted:', form.value);
};


const options =  [
  {
    value: 'zhejiang',
    label: '浙江省',
    children: [
      {
        value: 'hangzhou',
        label: '杭州市',
      },
      {
        value: 'ningbo',
        label: '宁波市',
      },
    ],
  },
  {
    value: 'jiangsu',
    label: '江苏省',
    children: [
      {
        value: 'nanjing',
        label: '南京市',
      },
      {
        value: 'suzhou',
        label: '苏州市',
      },
    ],
  }
]


const mustShow = ref(false);
const factoryInShow = ref(false);
const locationShow = ref(false);
const carbonEliminateShow = ref(false);



onBeforeMount( ()=>{

})

let route = useRoute();
let currentUrl = ref(route.path);
let nickname = getLocalStorage(KEY_NICKNAME);

// 数据库存的东西是相对路径，这里方便切换环境（登录时已经修改了上下文路径，这里直接用）
let avatarSrc = getLocalStorage(KEY_AVATAR);

const gotoIndex = ()=>{
  router.push("/index")
}

const quitLogin = ()=>{
  removeToken();
  setLocalStorage(KEY_NICKNAME, null);
  setLocalStorage(KEY_AVATAR, null);
  setLocalStorage(KEY_RESTAURANT_ID, null);
  setLocalStorage(KEY_ROLE, null);
  setLocalStorage(KEY_USER_ID, null);
  setLocalStorage(KEY_USER_NAME, null);
  router.push("/index")
  location.reload();
}
</script>

<style scoped lang="scss">
.el-form {
  max-width: 600px;
  margin: auto;
}
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
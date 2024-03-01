<!--
 * @author Santa Antilles
 * @description 各省双碳策略
 * @date 2024/1/24-00:09:36
 * -->
<template>
  <Header :context="context"/>
  <div class="page-content-wrapper">
    <div class="province-map">
      <img src="https://img1.baidu.com/it/u=1012539018,2422192115&fm=253&fmt=auto&app=138&f=GIF?w=659&h=500" alt="dadas"/>
    </div>
    <el-row>
      <el-col :span="3"/>
      <el-col :span="6" class="column-header-decorate"/>
      <el-col :span="6" class="column-header-decorate"/>
      <el-col :span="6" class="column-header-decorate"/>
      <el-col :span="3"/>
    </el-row>
    <el-row>
      <el-col :span="3"></el-col>
      <el-col :span="6" class="column-header">
        <div class="column-header-content-wrapper">
          <span class="column-header-content">最新政策</span>
        </div>
      </el-col>
      <el-col :span="6" class="column-header">
        <div class="column-header-content-wrapper">
          <span class="column-header-content">行业动向</span>
        </div>
      </el-col>
      <el-col :span="6" class="column-header">
        <div class="column-header-content-wrapper">
          <span class="column-header-content">成功案例</span>
        </div>
      </el-col>
      <el-col :span="3"></el-col>
    </el-row>
    <el-row>
      <el-col :span="3"></el-col>
      <el-col :span="6" class="item-content">
        <div class="item-content-decorate-left"/>
        <div class="item-content-decorate-right"/>
<!--        <ColumnInfoItem :title="t" :url="herf" :width="columnWidth"/>-->
<!--        <ColumnInfoItem :title="t" :url="herf" :width="columnWidth"/>-->
<!--        <ColumnInfoItem :title="t" :url="herf" :width="columnWidth"/>-->
<!--        <ColumnInfoItem :title="t" :url="herf" :width="columnWidth"/>-->
<!--        <ColumnInfoItem :title="t" :url="herf" :width="columnWidth"/>-->
<!--        <ColumnInfoItem :title="t" :url="herf" :width="columnWidth"/>-->
        <ColumnInfoItem v-for="_case in provincePolicy" :title="_case.title" :url="_case.url"  :width="columnWidth" :key="_case.policyId"/>
      </el-col>
      <el-col :span="6" class="item-content">
        <div class="item-content-decorate-left"/>
        <div class="item-content-decorate-right"/>
        <ColumnInfoItem v-for="intent in provinceIntents" :url="intent.url" :width="columnWidth" :title="intent.title" :key="intent.intentId"/>
      </el-col>
      <el-col :span="6" class="item-content">
        <div class="item-content-decorate-left"/>
        <div class="item-content-decorate-right"/>
<!--        <ColumnInfoItem :title="t" :url="herf" :width="columnWidth"/>-->
<!--        <ColumnInfoItem :title="t" :url="herf" :width="columnWidth"/>-->
<!--        <ColumnInfoItem :title="t" :url="herf" :width="columnWidth"/>-->
        <ColumnInfoItem v-for="_case in provinceCases" :title="_case.title" :url="_case.url"  :width="columnWidth" :key="_case.caseId"/>
      </el-col>
      <el-col :span="3"></el-col>
    </el-row>
<!--    <ul v-infinite-scroll="load" class="infinite-list" style="overflow: auto">-->
<!--      <li v-for="i in count" :key="i" class="infinite-list-item">{{ i }}</li>-->
<!--    </ul>-->
    <el-row style="margin: 10px 0">
      <el-col :span="3"/>
      <el-col :span="12">
        <span class="column-header-content">省内新闻</span>
      </el-col>
      <el-col :span="9"/>
    </el-row>
    <el-row>
      <el-col :span="3"/>
      <el-col :span="12">
        <InfiniteScrollNews title="大海的一个个第一上车VB发给哦五我到家" content-url="https://sf3-cdn-tos.toutiaostatic.com/img/user-avatar/19f2ae22d440a0bda4e6da4dd4c15d4e~300x300.image" image-url="https://sf3-cdn-tos.toutiaostatic.com/img/user-avatar/19f2ae22d440a0bda4e6da4dd4c15d4e~300x300.image"/>
<!--        <InfiniteScrollNews title="大海的一个个第一上车VB发给哦五我到家" content-url="https://sf3-cdn-tos.toutiaostatic.com/img/user-avatar/19f2ae22d440a0bda4e6da4dd4c15d4e~300x300.image" image-url="https://sf3-cdn-tos.toutiaostatic.com/img/user-avatar/19f2ae22d440a0bda4e6da4dd4c15d4e~300x300.image"/>-->
<!--        <InfiniteScrollNews title="大海的一个个第一上车VB发给哦五我到家" content-url="https://sf3-cdn-tos.toutiaostatic.com/img/user-avatar/19f2ae22d440a0bda4e6da4dd4c15d4e~300x300.image" image-url="https://sf3-cdn-tos.toutiaostatic.com/img/user-avatar/19f2ae22d440a0bda4e6da4dd4c15d4e~300x300.image"/>-->
        <InfiniteScrollNews v-for="news in provinceNews" :title="news.title" :content-url="news.contentUrl" :image-url="news.imageUrl" :key="news.newsId"/>
      </el-col>
      <el-col :span="9"/>
    </el-row>
  </div>
</template>

<script setup>
import {onBeforeMount, onMounted, onUnmounted, ref} from "vue";
import Header from "@/components/Head";
import ColumnInfoItem from "@/components/ColumnInfoItem";
import InfiniteScrollNews from '@/components/InfiniteScrollNews';
import {
  queryProvinceCase,
  queryProvinceIntent,
  queryProvinceNews,
  queryProvincePolicy
} from "@/web-api/province-method";
import router from "@/router";
import {onBeforeRouteUpdate, useRoute} from "vue-router/dist/vue-router";

const count = ref(0)
const load = () => {
  count.value += 2
}

const t = ref("中中中电视剧送到活动卫东未发货女生打架dwkb纳斯0i0")
const herf = ref('https://www.bilibili.com/')


// 记录页面上下文路径
const fullUrl = window.location.href;
const path = useRoute();
const context = fullUrl.replace(path.fullPath, "");

let provinceId = ref(path.params.provinceId)
let provinceCases = ref()
let provinceIntents = ref()
let provincePolicy = ref()
// 无限滚动条下滚时候记录请求的页数
let scrollPageNumber = ref(1)
const handleScrollToBottom = function (){
  queryProvinceNews({pageNum: scrollPageNumber.value, pageSize: 2, provinceId: provinceId.value}).then(res=>{
    if(provinceNews.value.length<res.total){
      provinceNews.value = provinceNews.value.concat(res.rows);
      ++scrollPageNumber.value
    }
  })
}
let provinceNews = ref([])

function requestNewsIntentCasePolicy(){
  queryProvinceCase({pageNum: 1, pageSize: 6, provinceId: provinceId.value}).then(res=>{
    provinceCases.value = res.rows;
  })
  queryProvinceIntent({pageNum: 1, pageSize: 6, provinceId: provinceId.value}).then(res=>{
    provinceIntents.value = res.rows
  })
  queryProvincePolicy({pageNum: 1, pageSize: 6, provinceId: provinceId.value}).then(res=>{
    provincePolicy.value = res.rows
  })
  handleScrollToBottom();
}


onBeforeMount(()=>{
  requestNewsIntentCasePolicy();
})

let columnWidth = ref(0);
// 监听窗口大小变化事件
const columnWidthListener = function (ev){
  columnWidth.value = document.querySelector(".item-content").offsetWidth;
  console.log(columnWidth.value)
}
// 监听滚动事件
const scrollListener = function (ev){
  let scrollTop = document.body.scrollTop || document.documentElement.scrollTop
  // console.log('scrollTop=',scrollTop)
  let bottomDistance = document.body.scrollHeight - document.documentElement.clientHeight - scrollTop;
  if(bottomDistance<300){
    // 请求新数据，判断新闻有没有完
    handleScrollToBottom();
  }
}

onMounted(()=>{
  window.addEventListener('resize', columnWidthListener);
  window.addEventListener('scroll', scrollListener);
  columnWidth.value = document.querySelector(".item-content").offsetWidth;
  console.log(document.querySelector(".allow-touch-styles"))
  console.log(document.querySelector(".children"))
})
onUnmounted(()=>{
  window.removeEventListener('resize', columnWidthListener);
  window.removeEventListener('scroll', scrollListener);
})
</script>

<style scoped>
.item-content-decorate-left{
  position: absolute;
  height: 10px;
  width: 10px;
  border-left: 2px solid #0C4C77FF;
  border-bottom: 2px solid #0C4C77FF;
  bottom: 0;
  left: 0;
}
.item-content-decorate-right{
  position: absolute;
  height: 10px;
  width: 10px;
  border-right: 2px solid #0C4C77FF;
  border-bottom: 2px solid #0C4C77FF;
  bottom: 0;
  right: 0;
}
.item-content{
  margin: -3px 5px 3px 5px;
  background-color: #f2f2f2;
  min-height: 200px;
  position: relative;
  padding: 10px 0;
}
.province-map{
  display: flex;
  justify-content: center;
  margin: 20px;
  padding: 15px;
}
.column-header{
  min-height: 40px;
  background-color: #e9e9eb;
  display: flex;
  justify-content: flex-start;
  align-items: flex-end;
  margin: 3px 5px;
}
.column-header-content-wrapper{
  height: auto;
  width: auto;
  background-color: #f2f2f2;
  margin: 0 10px;
  padding: 5px 10px;
}
.column-header-content{
  user-select: none;
  font-size: 20px;
  color: rgb(23, 90, 136);
  font-weight: bolder;
}
.column-header-decorate{
  margin: 3px 5px -3px 5px;
  height: 2px;
  background-color: rgb(12, 76, 119);
}
</style>
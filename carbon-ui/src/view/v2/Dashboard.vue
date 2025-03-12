<!--
  *@description      
  *@author          29145
  *@create          2025-03-12 11:49
-->
<template>
  <Header :context="context"></Header>

  <el-row>

    <el-col :span="3"/>
    <el-col :span="3"/>

    <el-col :span="4">
      <router-link to="/cmdv">
        <div class="navigator-block">碳捕·数据</div>
      </router-link>
    </el-col>
    <el-col :span="4">
      <router-link to="/cpdv">
        <div class="navigator-block">碳捕·市场</div>
      </router-link>
    </el-col>
    <el-col :span="4">
      <router-link to="/standard-example">
        <div class="navigator-block">碳捕·案例</div>
      </router-link>
    </el-col>

    <el-col :span="3"/>
    <el-col :span="3"/>


  </el-row>





  <router-view/>
</template>

<script setup>


// 记录页面上下文路径
import {useRoute} from "vue-router/dist/vue-router";
import Header from '@/components/Head.vue'

const fullUrl = window.location.href;
const path = useRoute();
const context = fullUrl.replace(path.fullPath, "");


</script>
<style lang="scss">
.navigator-block{
  display: flex;
  align-items: center;
  justify-content: center;
  background: #004a8c;
  color: white;
  border: 2px solid black;
  height: 48px;
  font-size: 20px;

}



html,
body,
#app,
.is-full {
  width: 100%;
  height: 100%;
  overflow: hidden;
}

.map-32-label {
  font-size: 10px;
  color: #fff;
}

.tag-description {
  color: #fff;
  font-size: 10px;
}
.page-container{
  width: 100%;
  height: 100%;
  overflow: hidden;
}
#controller-change-output{
  /*background-color: #f0f0f0;*/
  /*padding: 10px;*/
  /*border: 1px solid #ccc;*/
  position: fixed;
  top: 200px;
  right: 20px;
  width: 225px;
  height: 120px;
  background-color: white;
}
.change-output-title{
  width: 100%;
  height: 30%;
  background-color: black;
  color: #f9f9f9;
  display: flex;
  align-items: center;
  justify-content: center;
}
.change-output-options{
  width: 100%;
  height: 70%;
  background-color: #3a3e3f;
  display: flex;
  align-items: center;
}
.change-output-options ul{
  margin: 0;
}
.button-like-span{
  color: #f9f9f9;

}
.button-like-span:hover{
  cursor: pointer;
  text-decoration: underline;
}

.head-component-container p{
  color: #f9f9f9;
}
.avatar-to-render-navigator{
  color: #32a9c4 !important;
}
.right-indication-year-area{
  display: flex;
  width: 65%;
  height: 100%;
  align-items: center;
  justify-content: center;
}

a{
  text-decoration: none;
}
</style>

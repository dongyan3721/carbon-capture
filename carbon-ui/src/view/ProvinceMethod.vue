<!--
 * @author Santa Antilles
 * @description 各省双碳策略
 * @date 2024/1/24-00:09:36
 * -->
<template>
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
        <ul>
          <li>4234234</li>
          <li>4234234</li>
          <li>4234234</li>
        </ul>
      </el-col>
      <el-col :span="6" class="item-content">
        <div class="item-content-decorate-left"/>
        <div class="item-content-decorate-right"/>
        <ul>
          <li>4234234</li>
          <li>4234234</li>
          <li>4234234</li>
        </ul>
      </el-col>
      <el-col :span="6" class="item-content">
        <div class="item-content-decorate-left"/>
        <div class="item-content-decorate-right"/>
        <ul>
          <li>4234234</li>
          <li>4234234</li>
          <li>4234234</li>
        </ul>
      </el-col>
      <el-col :span="3"></el-col>
    </el-row>
  </div>
</template>

<script setup>

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
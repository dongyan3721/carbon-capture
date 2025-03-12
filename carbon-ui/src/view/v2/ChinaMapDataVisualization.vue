<!--
  *@description      
  *@author          29145
  *@create          2025-03-12 12:22
-->
<script setup>
import echarts from 'echarts'
import china from 'echarts/map/json/china.json'
import {onMounted, ref} from "vue";
import Dashboard from "@/view/v2/Dashboard.vue";
import {requestIndustryCarbonOutputByYear} from '@/web-api/request-industry-carbon-output';
import {requestCarbonOutput} from "@/web-api/request-carbon-output";

const provinceYearCarbonOutputList = ref();
const industryYearCarbonOutputList = ref();

echarts.registerMap('china', china);


function mapChinaOptions ( data, formatter, title ) {
  let max = 0;
  let riskMaxPoint = data.map( ( item ) => {
    return item.value
  } )
  if ( data && data.length ) {
    max = Math.max( ...riskMaxPoint )
  }
  return {
    visualMap: {
      show: false,
      min: 0,
      max: max,
      // inRange: {
      //     color: ['#fae8e9', '#f7a8be', '#f1658b ', '#CB000C']
      // },
    },
    // 暂且不知道做啥子用的
    // geo: {
    //     map: 'china',
    //     // layoutCenter:['10%','5%'],
    //     // center: [104.114129, 35.950339],
    // },
    grid: {
      top: 10,
      bottom: 10,
      width: '1000px',
      height: '500px'
    },
    tooltip: {
      trigger: 'item',
      formatter: formatter ? formatter : '{b}<br/>{c}',
      borderColor: '#CB000C',
      borderWidth: '1'
    },
    title: {
      text: title,
      top: 'top',
      left: 'center',
      textStyle: {
        fontSize: 18,
        fontWeight: 'normal',
        color: '#606266'
      },
    },
    dataRange: { // 数值范围对应的 取色
      x: 'left',
      y: 'bottom',
      show: true,
      splitList: [
        { start: Math.floor((max/4*3)), color: '#CB000C' },
        { start: Math.floor(max/2), end: Math.floor((max/4*3)), color: '#f1658b' },
        { start: Math.floor(max/4), end: Math.floor(max/2), color: 'rgba(247,168,190,.8)' },
        { end: Math.floor(max/4), color: '#fae8e9' }
      ],
      color: ['#fae8e9', '#f7a8be', '#f1658b ', '#CB000C']
    },
    series: [
      {
        type: 'map',
        map: 'china',
        itemStyle: {
          areaColor: '#fff', //地图的颜色
          borderColor: 'rgba(60,70,88,.5)', //边界线颜色
          borderWidth: '.5', //边界线宽
          emphasis: { // 对应的鼠标悬浮效果
            color: '#FF0000',
            areaColor: '#CB000C',
            borderColor: '#FFF',
            borderWidth: 1,
          }
        },
        label: {
          fontSize: 12,
          normal: {
            show: true, // 默认 是否显示省份标签
            color: '#000',
          },
          emphasis: {
            show: true, // 鼠标 hover 时是否显示身份标签
            textStyle: {
              color: '#fff',
            },
          },
        },
        data: data
      }
    ]
  }
}

let map;

onMounted(()=>{

  requestCarbonOutput('2020').then(res=>{
    provinceYearCarbonOutputList.value = res.rows.map((item)=>{

      if(item.provinceName.includes("内蒙古"))
        return {
          name: '内蒙古',
          value: item.output
        }
      else if(item.provinceName.includes('黑龙江'))
        return {
          name: '黑龙江',
          value: item.output
        }
      else {
        return {
          value: item.output,
          name: item.provinceName.slice(0, 2)
        }
      }
    })

    map = echarts.init(document.querySelector('#draw-panel-china'))
    map.setOption(mapChinaOptions(provinceYearCarbonOutputList.value, undefined,
    '2020年全国各省碳排放量(单位：万吨)'));
  })
  // requestIndustryCarbonOutputByYear('2021').then(res=>{
  //   industryYearCarbonOutputList.value = res.rows
  // })
})

const openMoreProvinceCarbonDialog = ref(false)
const handleViewMoreYearProvinceCarbon = (year)=>{

  requestCarbonOutput(year).then(res=>{
    provinceYearCarbonOutputList.value = res.rows.map((item)=>{

      if(item.provinceName.includes("内蒙古"))
        return {
          name: '内蒙古',
          value: item.output
        }
      else if(item.provinceName.includes('黑龙江'))
        return {
          name: '黑龙江',
          value: item.output
        }
      else {
        return {
          value: item.output,
          name: item.provinceName.slice(0, 2)
        }
      }
    })
    map.clear()
    map.setOption(mapChinaOptions(provinceYearCarbonOutputList.value, undefined,
        `${year}年全国各省碳排放量(单位：万吨)`));
  })
  openMoreProvinceCarbonDialog.value = false
}

const handleShowIndustryCarbon = ()=>{

  //
  // requestIndustryCarbonOutputByYear('2021').then(res=>{
  //   industryYearCarbonOutputList.value = res.rows
  // })
  const year = 2021;
  requestIndustryCarbonOutputByYear(year).then(res=>{
    industryYearCarbonOutputList.value = res.rows.map((i)=>{
      return {
        name: i.inventory,
        value: i.output.toFixed(2)
      }
    })
    const rawData = industryYearCarbonOutputList.value;
    // **1. 数据排序**
    rawData.sort((a, b) => b.value - a.value);

// **2. 提取前 10 名作为主饼图的重要数据**
    const top10 = rawData.slice(0, 10);
    const sub10 = rawData.slice(10, 20);
    const otherSubSum = rawData.slice(20).reduce((sum, item) => sum + item.value, 0);
    if (otherSubSum > 0) {
      sub10.push({ name: "其他", value: otherSubSum });
    }

// **3. 剩余的数据作为“其他”**
    const otherData = rawData.slice(10);
    const otherSum = otherData.reduce((sum, item) => sum + item.value, 0);

// **4. 在主饼图中添加 "其他" 这一项**
    top10.push({ name: "其他", value: otherSum });

// **5. 绘制 ECharts**
    map.clear()
    map.setOption({
      title: [
        {  // 主标题
          text: `${year}年碳排量按行业细分(单位：万吨)`,
          left: 'center'
        },
        {  // 子饼图标题
          text: '其余部分详情',
          left: '75%',  // 让标题与子饼图对齐
          top: '55%',   // 适配子饼图的位置
          textAlign: 'center',
          textStyle: { fontSize: 14, fontWeight: 'bold' }
        }
      ],
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b}: {c} ({d}%)'
      },
      series: [
        {
          name: '主饼图',
          type: 'pie',
          radius: ['40%', '70%'],  // 主饼图半径
          label: { position: 'outside' },
          data: top10
        },
        {
          name: '子饼图（其他明细）',
          type: 'pie',
          radius: ['10%', '30%'],  // 子饼图半径
          center: ['75%', '75%'],  // 子饼图位置
          label: { position: 'inside', formatter: '{b}' },
          data: sub10  // 细分“其他”项
        }
      ]
    });
  })

}



</script>

<template>

  <el-dialog v-model="openMoreProvinceCarbonDialog">
    <template #header>
      <span>选择年份</span>
    </template>
    <div class="more-carbon-province-output">
      <el-button v-for="(item, index) in [2005, 2010, 2015, 2020]" @click="handleViewMoreYearProvinceCarbon(item)"
                 :key="index">{{item}}</el-button>
    </div>
  </el-dialog>

  <Dashboard/>
  <el-row>

    <el-col :span="3"/>
    <el-col :span="3"/>

    <el-col :span="12">

      <div id="draw-panel-china"/>

    </el-col>

    <el-col :span="3"/>
    <el-col :span="3"/>


  </el-row>
  <el-row>

    <el-col :span="3"/>
    <el-col :span="3"/>

    <el-col :span="2">
      <el-button type="primary" size="small" @click="()=>{openMoreProvinceCarbonDialog=true}">各省碳排量</el-button>
    </el-col>
    <el-col :span="2">
      <el-button type="primary" size="small" @click="handleShowIndustryCarbon">行业碳排量</el-button>
    </el-col>
    <el-col :span="2">
<!--      <el-button type="primary" size="small">碳市场行情</el-button>-->
    </el-col>
    <el-col :span="2">

    </el-col>
    <el-col :span="2">

    </el-col>
    <el-col :span="2">

    </el-col>

    <el-col :span="3"/>
    <el-col :span="3"/>


  </el-row>
</template>

<style scoped lang="scss">
#draw-panel-china{
  width: 100%;
  height: 500px;
  background: rgba(250, 235, 215, 0.5);
  //position: absolute;
}

</style>
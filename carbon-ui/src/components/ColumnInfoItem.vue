<!--
 * @author Santa Antilles
 * @description 省份信息页面三栏内的单个超链接子项
 * @date 2024/2/22-16:56:21
 * -->
<template>
  <div class="column-item-container">
    <div class="column-left-title">
      {{trimmedTitle}}
    </div>
    <div class="column-right-button">
      <span>点击查看>></span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ColumnInfoItem',
  data(){
    return{

    }
  },
  methods:{
    trimTitle(){
      let maxLength = this.width * 0.65 / 16;
      if(this.title.length > maxLength){
        return `${this.title.substring(0, maxLength)}……`;
      }
      return this.title
    }
  },
  computed:{
    trimmedTitle(){
      return this.trimTitle();
    },
    resize(){
      console.log(this.width)
      return this.width++
    }
  },
  props:{
    title: String,
    url: String,
    width: Number
  }
}

</script>

<style scoped>
.column-item-container{
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 30px;
  width: 100%;
}
.column-right-button{
  width: auto;
  font-size: 14px;
  color: #606266;
  min-width: 80px;
  height: auto;
}
.column-left-title{
  width: 70%;
  line-height: 1.5; /* 设置行高，以确保垂直居中 */
  display: inline-block; /* 使元素变成内联块，以让伪元素生效 */
  font-size: 16px;
  margin: 0 20px;
  /*padding: 0 0 0 10px;*/
  cursor: pointer;
  user-select: text;
  position: relative;
}
.column-left-title::before {
  content: '\25A0'; /* 使用Unicode字符表示方点 */
  position: absolute;
  left: -12px; /* 调整方点的位置，使其与文本中点对齐 */
  top: 50%; /* 使方点垂直居中 */
  transform: translateY(-62%);
  font-size: 12px;
  color: #535457;
}
</style>
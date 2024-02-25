<!--
 * @author Santa Antilles
 * @description 无限滚动单个新闻标签
 * @date 2024/2/24-16:06:57
 * -->
<template>
  <div class="item-wrapper" @click="handleIdCallback">
    <div class="item-line">
      <div class="title-avatar">
        <img class="sign-avatar" :src="imageUrl" alt="新闻照片"/>
        <h3>{{title}}</h3>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "InfiniteScrollNews",
  props:{
    contentUrl: String,
    imageUrl: String,
    title: String
  },
  data(){
    return{

    }
  },
  methods: {
    handleIdCallback(){
      window.open(this.contentUrl, 'newTab')
    }
  }
}
</script>

<style scoped>
.item-wrapper{
  background-color: #ffffff;
  box-shadow: 0 0 2px 0 rgba(81, 90, 96, 0.7);
  border-radius: 5px;
  height: auto;
  cursor: pointer;
  margin: 10px 0;
  /*max-width: 600px;*/
}
.item-line{
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.sign-avatar{
  width: 60px;
  height: 60px;
  border-radius: 10px;
  margin: 5px;
  color: #ffffff;
  display: flex;
  align-items: center;
  justify-content: center;
}
.title-avatar h3{
  margin: 0 0 0 20px;
  font-weight: normal;
  font-size: 18px;
}
.title-avatar{
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>

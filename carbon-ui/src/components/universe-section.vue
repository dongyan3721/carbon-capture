<!--
-- @author Santa Antilles
-- @description
-- @date 2024/5/17-19:30:53
-->

<script setup>
const props = defineProps({
  title: String
})
</script>

<template>
  <div class="w-100 my-3 flex items-center alibaba-font">
    <div class="h-4 w-1.5 rounded bg-blue-400 mr-1.5"/>
    <span class="text-xl">{{props.title}}</span>
    <!--    <div class="h-2 w-2 rounded bg-green-500"/>-->
  </div>
</template>

<style scoped>

</style>
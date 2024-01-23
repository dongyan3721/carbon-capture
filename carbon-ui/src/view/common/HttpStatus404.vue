<template>
  <div class="not_found in-center">
    <div class="not_found_404 cf">
      <span>4</span>
      <span>0</span>
      <span>4</span>
    </div>
    <div class="btn">
      <router-link to="/index" class="button button-rounded">返回首页</router-link>
<!--      <a href="#" class="button button-rounded cancle">返回首页</a>-->
      <div @click="pageHistory.go(-1)" class="button button-rounded cancle" style="cursor: pointer">返回上页</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HttpStatus404",
  data(){
    return{
      pageHistory: window.history
    }
  },
  methods: {
  }
}
</script>

<style scoped>
*{
  margin:0;
  padding:0;
}
body{
  font-family: 'Open Sans', sans-serif;
  font-size: 14px;
  color: #3c3c3c;
  background-color: #ECECEC;
}
.cf {display:block;zoom:1;}
* html .cf {height:1%;}
.cf:after {content:" ";display:block;height:0;clear:both;visibility:hidden;}

.not_found{
  float: right;
  width: auto;
  height: auto;
  margin-top: auto;
  position: relative;
}

.not_found_404 {
  text-align: center;
  font-family: cursive,sans-serif;
  font-size: 120px;
  font-weight: bold;
  line-height: 120px;
  letter-spacing: 25px;
  color: #fff;
  -webkit-transform: rotate(0deg);
  transform: rotate(6deg);
  -webkit-transform-origin: 12% 60%;
  transform-origin: 12% 60%;
}
.in-center{
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  -moz-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  -o-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}
.not_found_404 span {
  float: left;
  cursor: pointer;
  text-shadow: 0px 0px 2px #686868,
  0px 1px 1px #ddd,
  0px 2px 1px #d6d6d6,
  0px 3px 1px #ccc,
  0px 4px 1px #c5c5c5,
  0px 5px 1px #c1c1c1,
  0px 6px 1px #bbb,
  0px 7px 1px #777,
  0px 8px 3px rgba(100, 100, 100, 0.4),
  0px 9px 5px rgba(100, 100, 100, 0.1),
  0px 10px 7px rgba(100, 100, 100, 0.15),
  0px 11px 9px rgba(100, 100, 100, 0.2),
  0px 12px 11px rgba(100, 100, 100, 0.25),
  0px 13px 15px rgba(100, 100, 100, 0.3);
  -webkit-transition: all .1s linear;
  transition: all .1s linear;
}

.not_found_404 span:hover {
  text-shadow: 0px 0px 2px #686868,
  0px 1px 1px #fff,
  0px 2px 1px #fff,
  0px 3px 1px #fff,
  0px 4px 1px #fff,
  0px 5px 1px #fff,
  0px 6px 1px #fff,
  0px 7px 1px #777,
  0px 8px 3px #fff,
  0px 9px 5px #fff,
  0px 10px 7px #fff,
  0px 11px 9px #fff,
  0px 12px 11px #fff,
  0px 13px 15px #fff;
  -webkit-transition: all .1s linear;
  transition: all .1s linear;
}


@-webkit-keyframes 'not'{
  0%{

  }
  25%{
    -webkit-transform: rotate(0deg);
  }
  50%{
    -webkit-transform: rotate(6deg);
  }
  75%{
    -webkit-transform: rotate(0deg);
  }
  100%{
    -webkit-transform: rotate(6deg);
  }

}
.not_found_404{
  -webkit-animation-duration: 4s;
  -webkit-animation-name: not;
  -webkit-animation-iteration-count: infinite;
  -webkit-animation-timing-function: ease-in-out;
}
.btn{
  width: 100%;
  margin-top: 100px;
}

.button{
  -webkit-box-shadow: inset 0px 1px 0px rgba(255, 255, 255, 0.5), 0px 1px 2px rgba(0, 0, 0, 0.15);
  -moz-box-shadow: inset 0px 1px 0px rgba(255, 255, 255, 0.5), 0px 1px 2px rgba(0, 0, 0, 0.15);
  box-shadow: inset 0px 1px 0px rgba(255, 255, 255, 0.5), 0px 1px 2px rgba(0, 0, 0, 0.15);
  background-color: #ffffff;
  background: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, whitesmoke), color-stop(100%, #fff));
  background: -webkit-linear-gradient(top, whitesmoke, #fff);
  background: -moz-linear-gradient(top, whitesmoke, #fff);
  background: -o-linear-gradient(top, whitesmoke, #fff);
  background: linear-gradient(top, whitesmoke, #fff);
  display: -moz-inline-stack;
  display: inline-block;
  vertical-align: middle;
  zoom: 1;
  border: 1px solid #d4d4d4;
  height: 32px;
  line-height: 32px;
  padding: 0px 25.6px;
  font-weight: 300;
  font-size: 14px;
  font-family: "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue", Helvetica, Arial, "Lucida Grande", sans-serif;
  color: #666666;
  text-shadow: 0 1px 1px white;
  margin: 0;
  text-decoration: none;
  text-align: center;
  -webkit-border-radius: 30px;
  -moz-border-radius: 30px;
  -ms-border-radius: 30px;
  -o-border-radius: 30px;
  border-radius: 30px;

  -moz-animation:funny 3s linear infinite;
  -o-animation:funny 3s linear infinite;
  animation:funny 3s linear infinite;
  -webkit-animation:funny 3s linear infinite;

  margin-right: 40px;
  clear: left;
}
</style>
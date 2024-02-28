import axios from 'axios'
import { ElNotification, ElMessage} from 'element-plus'
import { getToken } from './auth.js'
import errorCode from './errorCode'
import { tansParams, blobValidate } from "./ruoyi";
// import {baseStaticRecourseAPI, NOW_ENVIRONMENT} from "../baseAPIConfig.js";

// let downloadLoadingInstance;
// 是否显示重新登录
// export let isRelogin = { show: false };

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'

// 后端问题已经解决，方法弃用
// 自研算法实现超长雪花算法生成id的字符串转化
/**
 * 很好雪花算法，使我的脑仁旋转
 * @param inputString 原始json数据
 * @returns {*|*[]} 匹配列表
 */
const findLongNumbers = (inputString) =>{
  const regex = /\b\d{16,}\b/g;
  const matches = inputString.match(regex);
  return matches ? matches : [];
}

// 算法实现
/**
 * @author Santa Antilles
 * @date 2023.12.22
 */
const idConvert = (input)=>{
  let roughParsed = JSON.parse(input);
  if(roughParsed.rows!==undefined){
    if(roughParsed.rows.length>0){
      let simple = roughParsed.rows[0];
      let recOver = 0;
      let parseKey = [];
      Object.keys(simple).forEach((key)=>{
        if(simple[key]>1e15||parseInt(simple[key])>1e15){
          ++recOver;
          parseKey.push(key)
        }
      })
      if(recOver>0){
        let ids = findLongNumbers(input);
        let toParse = roughParsed.rows;
        for (let i=0;i<toParse.length;++i){
          for (let j=0;j<recOver;++j){
            toParse[i][parseKey[j]] = ids[i*recOver+j];
          }
        }
        roughParsed.rows = toParse;
        return roughParsed;
      }else{
        return roughParsed;
      }
    }else{
      return roughParsed;
    }
  }else if(roughParsed.data!==undefined){
    let data = roughParsed.data;
    let match = findLongNumbers(input);
    if(!match){
      return roughParsed;
    }else{
      let used = 0;
      Object.keys(data).forEach(key=>{
        if(data[key]>1e15||parseInt(data[key])>1e15){
          data[key] = match[used++];
        }
      })
      roughParsed.data = data;
      return roughParsed;
    }
  }else{
    return roughParsed;
  }
}

// 创建axios实例
const service = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  baseURL: "http://localhost:8080",
  // 超时
  timeout: 10000,
  // 取消axios自动解析json，用自己定义的雪花算法id拦截器拦截
  // transitional: {
  //   forcedJSONParsing: false,
  //   silentJSONParsing: false
  // }
})

// request拦截器
service.interceptors.request.use(config => {
  // 是否需要设置 token
  const isToken = (config.headers || {}).isToken === false
  // 是否需要防止数据重复提交
  // const isRepeatSubmit = (config.headers || {}).repeatSubmit === false
  if (getToken() && !isToken) {
    config.headers['token'] = getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
  }
  // get请求映射params参数
  if (config.method === 'get' && config.params) {
    let url = config.url + '?' + tansParams(config.params);
    url = url.slice(0, -1);
    config.params = {};
    config.url = url;
  }
  // if (!isRepeatSubmit && (config.method === 'post' || config.method === 'put')) {
  if ((config.method === 'post' || config.method === 'put')) {
    const requestObj = {
      url: config.url,
      data: typeof config.data === 'object' ? JSON.stringify(config.data) : config.data,
      time: new Date().getTime()
    }
    const requestSize = Object.keys(JSON.stringify(requestObj)).length; // 请求数据大小
    const limitSize = 5 * 1024 * 1024; // 限制存放数据5M
    if (requestSize >= limitSize) {
      console.warn(`[${config.url}]: ` + '请求数据大小超出允许的5M限制，无法进行防重复提交验证。')
      return config;
    }
  }
  return config
}, error => {
    console.log(error)
    return Promise.reject(error)
})

// 响应拦截器
service.interceptors.response.use(res => {
    // 未设置状态码则默认成功状态
    const code = res.data.code || 200;
    // 获取错误信息
    const msg = errorCode[code] || res.data.msg || errorCode['default']
    // 二进制数据则直接返回
    if (res.request.responseType ===  'blob' || res.request.responseType ===  'arraybuffer') {
      return res.data
    }
    if (code === 401) {
      ElMessage({ message: '会话已过期，请重新登录！', type: 'error' })
      return Promise.reject('无效的会话，或者会话已过期，请重新登录。')
    } else if (code === 500) {
      ElMessage({ message: msg, type: 'error' })
      return Promise.reject(new Error(msg))
    } else if (code === 601) {
      ElMessage({ message: msg, type: 'warning' })
      return Promise.reject('error')
    } else if (code !== 200) {
      ElNotification.error({ title: msg })
      return Promise.reject('error')
    } else {
      // console.log(idConvert(res.data))
      return res.data;
    }
  },
  error => {
    console.log('err' + error)
    let { message } = error;
    if (message === "Network Error") {
      message = "后端接口连接异常";
    } else if (message.includes("timeout")) {
      message = "系统接口请求超时";
    } else if (message.includes("Request failed with status code")) {
      message = "系统接口" + message.substr(message.length - 3) + "异常";
    }
    ElMessage({ message: message, type: 'error', duration: 5 * 1000 })
    return Promise.reject(error)
  }
)

// 通用下载方法
/**export function download(url, params, filename, config) {
  downloadLoadingInstance = Loading.service({ text: "正在下载数据，请稍候", spinner: "el-icon-loading", background: "rgba(0, 0, 0, 0.7)", })
  return service.post(url, params, {
    transformRequest: [(params) => { return tansParams(params) }],
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    responseType: 'blob',
    ...config
  }).then(async (data) => {
    const isBlob = blobValidate(data);
    if (isBlob) {
      const blob = new Blob([data])
      saveAs(blob, filename)
    } else {
      const resText = await data.text();
      const rspObj = JSON.parse(resText);
      const errMsg = errorCode[rspObj.code] || rspObj.msg || errorCode['default']
      ElMessage.error(errMsg);
    }
    downloadLoadingInstance.close();
  }).catch((r) => {
    console.error(r)
    ElMessage.error('下载文件出现错误，请联系管理员！')
    downloadLoadingInstance.close();
  })
}*/

export default service

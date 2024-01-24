/**
 * @author Santa Antilles
 * @description 各省碳排放举措请求接口
 * @date 2024/1/24-01:14:43
 */
import request from "@/utils/request";

// 参数：ProvinceCase类，预计只有provinceId不为空
// 该省的一些成功案例，返回列表
export function queryProvinceCase(province){
    return request({
        url: '/province/case/query',
        params: province,
        method: 'get'
    })
}

// 参数：ProvinceIntent类，预计只有provinceId不为空
// 该省的一些企业动向，返回列表
export function queryProvinceIntent(province){
    return request({
        url: '/province/intent/query',
        params: province,
        method: 'get'
    })
}

// 参数：ProvincePolicy类，预计只有provinceId不为空
// 该省的一些双碳政策，返回列表
export function queryProvincePolicy(province){
    return request({
        url: '/province/policy/query',
        params: province,
        method: 'get'
    })
}

// 参数：ProvinceNews类，预计只有provinceId不为空
// 该省的一些双碳治理的新闻，返回列表
export function queryProvinceNews(province){
    return request({
        url: '/province/news/query',
        params: province,
        method: 'get'
    })
}
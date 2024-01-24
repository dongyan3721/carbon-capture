/**
 * @author Santa Antilles
 * @description 请求后端各省碳排放量
 * @date 2024/1/24-01:11:51
 */
import request from "@/utils/request";

// 请求某年度所有省的碳排量，参数为年份字符串
export function requestCarbonOutput(year){
    return request({
        url: "/province/carbon-output/"+year,
        method: 'get'
    })
}

// 新增该用户归属哪个行业
export function submitUserBelong(belong){
    return request({
        url: '/user-belong/add',
        method: 'post',
        data: belong
    })
}
/**
 * @author Santa Antilles
 * @description 个人中心扩展接口
 * @date 2024/2/21-20:07:28
 */
import request from "@/utils/request";

// 查询所有的34个省级行政区
export function requestAllProvinces(){
    return request({
        url: '/province/query',
        method: 'get'
    })
}

// 参数是用户id，数据库里这个字段是unique的
export function requestUserBelongDetail(userId){
    return request({
        url: 'user-belong/'+userId,
        method: 'get'
    })
}

// 插入新的用户行业信息，参数为不带belongId的UserBelong类
export function addUserBelong(data){
    return request({
        url: 'user-belong/add',
        method: 'post',
        data
    })
}

// 修改用户行业信息，参数为带belongId的UserBelong类
export function modifyUserBelong(data){
    return request({
        url: 'user-belong/modify',
        method: 'put',
        data
    })
}

// 寻求解决方案，这里先不写，估计要调Python
export function requestRecommendedCarbonSolution(userId){
    return new Promise((resolve, reject)=>{

    })
}
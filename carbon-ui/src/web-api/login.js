/**
 * @author Santa Antilles
 * @description 登录请求
 * @date 2024/1/24-01:12:57
 */
import request from "@/utils/request";

// 参数：User类
// 要是能成功登录就返200，其他都返403
export function userLogin(user){
    return request({
        url: '/user/login',
        data: user,
        method: 'post'
    })
}
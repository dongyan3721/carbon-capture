/**
 * @author Santa Antilles
 * @description 注册请求
 * @date 2024/1/24-01:13:15
 */
import request from "@/utils/request";

// 参数：User类
// 要是能成功注册就返200，其他都返401
export function userRegister(user){
    return request({
        url: '/user/add',
        data: user,
        method: 'post'
    })
}
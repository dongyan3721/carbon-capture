/**
 * @author Santa Antilles
 * @description 路由切换时需要用到的函数
 * @date 2024/2/29-19:16:11
 */
import request from "@/utils/request";

export function chatterWithBackendVerifyWhetherTokenIsValid(){
    return request({
        url: '/token',
        method: 'get',
        isToken: true
    })
}

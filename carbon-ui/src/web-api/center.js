/**
 * @author Santa Antilles
 * @description 个人中心相关操作
 * @date 2024/1/24-01:13:53
 */
import request from "@/utils/request";

export function requestDetailUser(id){
    return request({
        url: "/user/" + id,
        method: 'get'
    })
}

// 改密等操作
// 参数：User类，头像字段肯定为空
export function modifyUser(user){
    return request({
        url: '/user/modify',
        data: user,
        method: 'put'
    })
}

// 修改头像
// 参数：{"user": User类, "avatar": MultipartFile}
// 记得这么传参可以用@RequestPart接
export function modifyAvatar(data) {
    return request({
        url: 'user/modify-avatar',
        data: data,
        method: 'put'
    })
}
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

// 修改昵称、绑定的邮箱等操作
// 这一步操作记得判断邮箱有没有重复
// 成功返回代码200，因为邮箱重复返回代码1000
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
// 这边也要做类似的邮箱有无重复判断（
export function modifyAvatar(data) {
    return request({
        url: 'user/modify-avatar',
        data: data,
        method: 'put'
    })
}

// 修改密码
// 参数{userId: 用户id, originalPassword: 原密码,, newPassword: 新密码}
export function modifyPassword(data){
    return request({
        url: 'user/modify-password',
        data,
        method: 'put'
    })
}
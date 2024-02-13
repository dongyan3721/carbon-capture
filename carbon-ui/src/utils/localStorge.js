// 用户账号
const KEY_USER_NAME = 'username'
// 用户角色
const KEY_ROLE = 'role'
// 如果是食堂管理员，这是她的食堂id
const KEY_RESTAURANT_ID = "restaurant_id"
// 头像
const KEY_AVATAR = "avatar"
// 用户昵称
const KEY_NICKNAME = "nickname"
// 用户id
const KEY_USER_ID = "user_id"
// const KEY_DINING_HALL_NAME = "dining_hall"
// 用户邮箱
const KEY_EMAIL = 'email'
export {KEY_USER_NAME, KEY_ROLE, KEY_RESTAURANT_ID, KEY_AVATAR, KEY_NICKNAME, KEY_USER_ID, KEY_EMAIL}

function setLocalStorage(key, value){
    localStorage.setItem(key, value)
}

function getLocalStorage(key){
    return localStorage.getItem(key);
}

export {setLocalStorage, getLocalStorage}

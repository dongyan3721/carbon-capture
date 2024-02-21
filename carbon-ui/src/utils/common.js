/**
 * @author Santa Antilles
 * @usage 各种通用功能，包括各种常用函数、字典等
 */

// 判断字符串能不能构成纯数字
export function isNumeric(value) {
    return /^-?\d*\.?\d+$/.test(value);
}

// 重置表单
export function objReset(obj){
    let keys = Object.keys(obj);
    for (let i=0;i<keys.length;++i){
        obj[keys[i]] = null;
    }
}

// 字典-是否
const dict_yes_no = [
    {label: '是', value: '1'}, {label: '否', value: '0'}
]
export {dict_yes_no}

const dict_industry_belong = [
    {
        label: '钢铁',
        value: '钢铁',
        children: [
            {
                label: '加热炉尾气',
                value: '加热炉尾气'
            },
            {
                label: '重整炉顶煤气',
                value: '重整炉顶煤气'
            },
            {
                label: '其他',
                value: '其他'
            }
        ]
    },
    {
        label: '空气',
        value: '空气',
        children: [
            {
                label: '锅炉烟气中的二氧化碳',
                value: '锅炉烟气中的二氧化碳'
            },
            {
                label: '反应器的出口烟气',
                value: '反应器的出口烟气'
            },
            {
                label: '电厂尾部烟气',
                value: '电厂尾部烟气'
            },
            {
                label: '其他',
                value: '其他'
            }
        ]
    },
    {
        label: '燃煤电厂',
        value: '燃煤电厂',
        children: [
            {
                label: '二氧化碳捕集',
                value: '二氧化碳捕集'
            },
            {
                label: '其他',
                value: '其他'
            }
        ]
    },
    {
        label: '煤化工',
        value: '煤化工',
        children: [
            {
                label: '煤制甲醇',
                value: '煤制甲醇'
            },
            {
                label: '其他',
                value: '其他'
            }
        ]
    },
    {
        label: '水泥',
        value: '水泥',
        children: [
            {
                label: '尾气处理',
                value: '尾气处理'
            },
            {
                label: '其他',
                value: '其他'
            }
        ]
    }
]

export {dict_industry_belong}

// 字典，用户类型
const dict_user_type = [
    {label: '系统管理员', value: '00'}, {label: '食堂管理员', value: '01'},
    {label: '教师', value: '10'}, {label: '学生', value: '11'}
]
export {dict_user_type}

// 字典，性别
const dict_gender = [
   {label: '男', value: '0'}, {label: '女', value: '1'}, {label: '未知', value: '2'}
]
export {dict_gender}

const dict_status = [
    {label: '正常', value: '0'}, {label: '停用', value: '1'}
]
export {dict_status}

// 通用验证器，只验证字段是否为空
export const generalValidatorJudgeIfEmpty = (msg)=>{
    return (rule, value, callback)=>{
        if(value===''||value===null){
            callback(new Error(`${msg}不能为空！`));
        }
        callback();
    }
}

export const emptyValidator = ()=>{
    return (rule, value, callback)=>{
        callback();
    }
}

// 日后这个可能还要加特殊字符的验证，这里跟用户名长度判断分开
const generalPasswordValidator = (attribute)=>{
    return (rule, value, callback)=>{
        if(value===''||value===null){
            callback(new Error(`${attribute}不能为空！`))
        }else if(value.length<6){
            callback(new Error(`${attribute}长度过短！`))
        }else if(value.length>16){
            callback(new Error(`${attribute}长度过长！`))
        }
        callback();
    }
}
export {generalPasswordValidator}

export function nicknameLengthValidator(attribute, max_length, min_length){
    return (rule, value, callback)=>{
        if(value===''||value===null){
            callback(new Error(`${attribute}不能为空！`))
        }else if(value.length<min_length){
            callback(new Error(`${attribute}长度过短！`))
        }else if(value.length>max_length){
            callback(new Error(`${attribute}长度过长！`))
        }
        callback();
    }
}

const generalPhoneNumberValidator = (attribute)=>{
    let phonePattern = /^1[3456789]\d{9}$/
    return (rule, value, callback)=>{
        if(value!==null||value!==''){
            if(!phonePattern.test(value)){
                callback(new Error(`${attribute}格式不正确！`));
            }else{
                callback();
            }
        }else{
            callback();
        }
        callback();
    }
}
export {generalPhoneNumberValidator}

const generalEmailValidator = (attribute)=>{
    let pattern = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    return (rule, value, callback)=>{
        if(value!==null||value!==''){
            if(!pattern.test(value)){
                callback(new Error(`${attribute}格式不正确！`));
            }else{
                callback();
            }
        }else{
            callback();
        }
        callback();
    }
}
export {generalEmailValidator}

export function sleep(delay) {
    let start = (new Date()).getTime();
    while ((new Date()).getTime() - start < delay) {
        continue;
    }
}

export function generalNumericValidator(attribute){
    return function (rule, value, callback) {
        if(!isNumeric(value)){
            callback(new Error(`${attribute}不是合法的数字！`));
            return;
        }
        callback();
    }
}
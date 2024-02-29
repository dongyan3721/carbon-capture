package com.carboncapture.carbon.controller;

import com.carboncapture.carbon.core.AjaxResult;
import com.carboncapture.carbon.entity.CarbonUser;
import com.carboncapture.carbon.framework.annotation.AccessWithoutVerification;
import com.carboncapture.carbon.service.CarbonUserService;
import com.carboncapture.carbon.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class LoginController {
    @Autowired
    private CarbonUserService carbonUserService;

    @AccessWithoutVerification
    @PostMapping("/user/login")
    public AjaxResult login(@RequestBody CarbonUser user){
        log.info("员工登录");
        CarbonUser selectedUser =carbonUserService.login(user);
        //登录成功，生成JWT令牌，下发令牌
        if(selectedUser!=null){
            String jwt= JwtUtils.sign(selectedUser.getUserId());//生成jwt并且包含了登录员工的信息
            selectedUser.setPassword("***********");
            return AjaxResult.success(selectedUser).put("token", jwt);
        }
        //登录失败，返回错误信息
        return AjaxResult.error("账号或密码错误");
    }
}

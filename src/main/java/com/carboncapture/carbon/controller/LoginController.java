package com.carboncapture.carbon.controller;

import com.carboncapture.carbon.core.AjaxResult;
import com.carboncapture.carbon.entity.CarbonUser;
import com.carboncapture.carbon.service.CarbonUserService;
import com.carboncapture.carbon.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private CarbonUserService carbonUserService;

    @PostMapping("/user/login")
    public AjaxResult login(@RequestBody CarbonUser user){
        log.info("员工登录");
        user =carbonUserService.login(user);

        //登录成功，生成JWT令牌，下发令牌
        if(user!=null){
            Map<String,Object> claims=new HashMap<>();
            claims.put("userId",user.getUserId());
            claims.put("userType",user.getUserType());
            claims.put("email",user.getEmail());
            claims.put("nickname",user.getNickname());
            claims.put("avatar",user.getAvatar());
            claims.put("password",user.getPassword());

            String jwt= JwtUtils.generateJwt(claims);//生成jwt并且包含了登录员工的信息
            return AjaxResult.success(jwt);
        }
        //登录失败，返回错误信息
        return AjaxResult.error("账号或密码错误");
    }
}

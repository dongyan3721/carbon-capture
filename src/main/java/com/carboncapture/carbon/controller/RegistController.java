package com.carboncapture.carbon.controller;

import com.carboncapture.carbon.core.AjaxResult;
import com.carboncapture.carbon.entity.CarbonUser;
import com.carboncapture.carbon.framework.annotation.AccessWithoutVerification;
import com.carboncapture.carbon.service.CarbonUserService;
import com.carboncapture.carbon.utils.RandomLengthStringGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RegistController {
    @Autowired
    private CarbonUserService carbonUserService;

    @AccessWithoutVerification
    @PostMapping("/user/add")
    public AjaxResult regist(@RequestBody CarbonUser user){
        if(carbonUserService.selectUserByEmail(user)!=null){
            return AjaxResult.error("重复注册");
        }
        user.setAvatar("https://profile-avatar.csdnimg.cn/30970520123147abbd521f732168e280_death_lover.jpg!1");
        user.setNickname(RandomLengthStringGenerator.generateRandomString(6));
        carbonUserService.regist(user);
        return AjaxResult.success();
    }
}

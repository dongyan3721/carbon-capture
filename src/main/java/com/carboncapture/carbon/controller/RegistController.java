package com.carboncapture.carbon.controller;

import com.carboncapture.carbon.core.AjaxResult;
import com.carboncapture.carbon.entity.CarbonUser;
import com.carboncapture.carbon.service.CarbonUserService;
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
    @PostMapping("/user/add")
    public AjaxResult regist(@RequestBody CarbonUser user){
        carbonUserService.regist(user);

        return AjaxResult.success();
    }
}

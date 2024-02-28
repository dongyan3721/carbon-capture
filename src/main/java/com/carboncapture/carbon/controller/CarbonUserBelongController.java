package com.carboncapture.carbon.controller;

import com.carboncapture.carbon.core.AjaxResult;
import com.carboncapture.carbon.entity.CarbonUerBelong;
import com.carboncapture.carbon.service.CarbonUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class CarbonUserBelongController {
    @Autowired
    private CarbonUserService carbonUserService;
    @GetMapping("/user-belong/{id}")
    public AjaxResult requestUserBelongDetail(@PathVariable String id){
        CarbonUerBelong c=carbonUserService.getBelongById(id);
        return AjaxResult.success(c);
    }

    @PostMapping("/user-belong/add")
    public AjaxResult addUserBelong(@RequestBody CarbonUerBelong c){
        carbonUserService.addUserBelong(c);
        return AjaxResult.success("添加成功！");

    }

    @PutMapping("/user-belong/modify")
    public AjaxResult modifyUserBelong(@RequestBody CarbonUerBelong c){
        carbonUserService.changeUserBelong(c);
        return AjaxResult.success();
    }
}

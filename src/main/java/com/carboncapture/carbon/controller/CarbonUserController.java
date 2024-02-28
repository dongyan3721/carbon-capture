package com.carboncapture.carbon.controller;

import com.carboncapture.carbon.core.AjaxResult;
import com.carboncapture.carbon.entity.CarbonUser;
import com.carboncapture.carbon.service.CarbonUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class CarbonUserController {
    @Autowired
    private CarbonUserService carbonUserService;
    @GetMapping("/user/{id}")
    public AjaxResult  requestDetailUser(@PathVariable String id){
        CarbonUser user=carbonUserService.getUserDetail(id);
        return AjaxResult.success(user);
    }

    @PutMapping("/user/modify")
    public AjaxResult modifyUser(@RequestBody CarbonUser user){
        int flag=carbonUserService.modifyUser(user);
        if(flag==200){
            return AjaxResult.success("修改成功");
        }else{
            return AjaxResult.error("修改失败");
        }
    }
//    @PutMapping("user/{avatar}")
//    public AjaxResult modifyAvatar(@RequestPart("user") CarbonUser user,
//                                   @RequestPart("avator") MultipartFile avatar){
//        int flag=carbonUserService.modifyAvatar();
//
//    }

    @PutMapping("/user/{password}")
    public AjaxResult modifyPassword(@RequestParam String id,@RequestParam String password){
        carbonUserService.changeCode(id,password);
        return AjaxResult.success();
    }

}

package com.carboncapture.carbon.controller;

import com.carboncapture.carbon.core.AjaxResult;
import com.carboncapture.carbon.entity.CarbonUser;
import com.carboncapture.carbon.entity.passwordmodify.ModifyEntity;
import com.carboncapture.carbon.framework.config.StaticValueConfig;
import com.carboncapture.carbon.service.CarbonUserService;
import com.carboncapture.carbon.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
    @PutMapping("user/modify-avatar")
    public AjaxResult modifyAvatar(@RequestPart("user") CarbonUser user,
                                   @RequestPart("avatar") MultipartFile file) throws IOException {

        List<String> split = StringUtils.str2List(file.getOriginalFilename(), "\\.", true, true);
        String fileName = user.getUserId() +"-"+ UUID.randomUUID()+"."+split.get(split.size()-1);
        String storePath = StaticValueConfig.getAvatarUpload() + fileName;
        file.transferTo(new File(storePath));
        user.setAvatar("/static/avatar/"+fileName);
        System.out.println(user);
        int flag=carbonUserService.modifyUser(user);
        if(flag==200){
            return AjaxResult.success("修改成功");
        }else{
            return AjaxResult.error("修改失败");
        }
    }

    @PutMapping("/user/modify-password")
    public AjaxResult modifyPassword(@RequestBody ModifyEntity modifyEntity){
        CarbonUser userDetail = carbonUserService.getUserDetail(modifyEntity.getUserId());
        if(userDetail.getPassword().equals(modifyEntity.getOriginalPassword())){
            carbonUserService.changeCode(modifyEntity.getUserId(), modifyEntity.getNewPassword());
            return AjaxResult.success();
        }
        return AjaxResult.error("原始密码错误！");
    }


}

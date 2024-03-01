package com.carboncapture.carbon.controller.commoncontroller;

import com.carboncapture.carbon.core.AjaxResult;
import com.carboncapture.carbon.framework.annotation.AccessWithoutVerification;
import com.carboncapture.carbon.framework.annotation.EnableProlongTokenSurvival;
import com.carboncapture.carbon.framework.exception.UnAuthenticatedException;
import com.carboncapture.carbon.utils.JwtUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Santa Antilles
 * @description 前端进入每个页面前都要检查token有效无效，有效就给他延时，无效就提示登录
 * @date 2024/2/29-15:34:52
 */
@RestController
public class ProlongTokenSurvivalController {

    @GetMapping("/token")
    @AccessWithoutVerification
    @EnableProlongTokenSurvival
    public AjaxResult checkProlongTokenSurvival(@RequestHeader(JwtUtils.TOKEN) String token) throws UnAuthenticatedException {
        return AjaxResult.success("success").put(JwtUtils.TOKEN, JwtUtils.sign(JwtUtils.verify(token)));
    }
}

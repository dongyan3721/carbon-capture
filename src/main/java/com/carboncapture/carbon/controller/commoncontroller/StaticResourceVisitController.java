package com.carboncapture.carbon.controller.commoncontroller;

import com.carboncapture.carbon.core.HttpStatus;
import com.carboncapture.carbon.framework.annotation.AccessWithoutVerification;
import com.carboncapture.carbon.framework.config.StaticValueConfig;
import com.carboncapture.carbon.framework.exception.ServiceException;
import com.carboncapture.carbon.framework.exception.UnsupportedFileExtensionException;
import com.carboncapture.carbon.utils.AutoInferenceContentTypeUtils;
import com.carboncapture.carbon.utils.FileUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Santa Antilles
 * @description 静态资源访问控制类
 * @date 2024/2/28-17:27:42
 */
@CrossOrigin
@RestController
@RequestMapping
public class StaticResourceVisitController {
    /**
     * 自定义资源访问处理器
     * @param response 返回响应
     * @param request 请求
     */
    private void privateService(HttpServletResponse response, HttpServletRequest request) throws Exception {
        String decode = URLDecoder.decode(request.getRequestURL().toString(), StandardCharsets.UTF_8);
        String resourceName = decode.replace("http://localhost:8080/static/", "");
        if(!FileUtils.checkAllowDownload(resourceName)){
            throw new Exception(String.format("请求的文件%s不存在！", resourceName));
        }
        String fullPath = StaticValueConfig.getUploadPath() + resourceName;
        System.out.println(fullPath);
        try {
            response.setContentType(AutoInferenceContentTypeUtils.inference(resourceName));
        } catch (UnsupportedFileExtensionException e) {
            throw new ServiceException("不支持的文件类型！", HttpStatus.BAD_REQUEST);
        }
        try {
            FileUtils.setAttachmentResponseHeader(response, resourceName);
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException("网络错误！", HttpStatus.ERROR);
        }
        try {
            FileUtils.writeBytes(fullPath, response.getOutputStream());
        } catch (IOException e) {
            throw new ServiceException("网络错误！", HttpStatus.ERROR);
        }
    }

    @AccessWithoutVerification
    @RequestMapping(value = "/static/**", method = RequestMethod.GET)
    public void getStaticResourceGet(HttpServletResponse response, HttpServletRequest request) throws Exception {
        privateService(response, request);
    }

    @AccessWithoutVerification
    @RequestMapping(value = "/static/**", method = RequestMethod.POST)
    public void getStaticResourcePost(HttpServletResponse response, HttpServletRequest request) throws Exception {
        privateService(response, request);
    }
}

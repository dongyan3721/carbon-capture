package com.carboncapture.carbon.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Santa Antilles
 * @description
 * @date 2024/2/28-17:18:29
 */
@Component
@ConfigurationProperties(prefix = "backend")
public class StaticValueConfig {

    private static String uploadPath;

    public static String getUploadPath(){
        return uploadPath;
    }

    public static String getAvatarUpload(){
        return uploadPath + "avatar/";
    }

    public void setUploadPath(String path){
        StaticValueConfig.uploadPath = path;
    }
}

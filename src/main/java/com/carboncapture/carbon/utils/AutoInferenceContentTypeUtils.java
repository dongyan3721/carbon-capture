package com.carboncapture.carbon.utils;

import com.carboncapture.carbon.framework.exception.UnsupportedFileExtensionException;

import java.util.List;

/**
 * @author Santa Antilles
 * @description
 * @date 2024/2/29-01:46:05
 */
public class AutoInferenceContentTypeUtils {
    // 不管什么文件路径，最后带个.xxx就行了
    public static String inference(String filePathWithExtend) throws UnsupportedFileExtensionException {
        List<String> split = StringUtils.str2List(filePathWithExtend, "\\.", true, true);
        if(ContentTypeUtils.getContentType("."+split.get(split.size()-1))==null){
            throw new UnsupportedFileExtensionException("不受支持的文件类型！");
        }else{
            return ContentTypeUtils.getContentType("."+split.get(split.size()-1));
        }
    }
}

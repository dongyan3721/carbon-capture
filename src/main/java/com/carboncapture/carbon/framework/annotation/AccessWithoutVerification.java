package com.carboncapture.carbon.framework.annotation;

import java.lang.annotation.*;

/**
 * @author Santa Antilles
 * @description 标识该方法不需要jwt验证即可放行
 * @date 2024/2/29-15:22:41
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessWithoutVerification {

}

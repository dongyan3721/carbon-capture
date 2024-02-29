package com.carboncapture.carbon.framework.exception;

import com.carboncapture.carbon.core.AjaxResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Santa Antilles
 * @Date 2023/12/2-14:01
 */

@ControllerAdvice
public class GlobalException {
    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public AjaxResult handleServiceException(ServiceException e){
        return new AjaxResult(e.getCode(), e.getMessage());
    }
}

package com.carboncapture.carbon.framework.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Santa Antilles
 * @Date 2023/12/2-13:58
 */
@Setter
@Getter
public class ServiceException extends RuntimeException{
    public ServiceException(String message) {
        super(message);
    }
    private int code;

    public ServiceException(String message, int code) {
        super(message);
        this.code  = code;
    }
}

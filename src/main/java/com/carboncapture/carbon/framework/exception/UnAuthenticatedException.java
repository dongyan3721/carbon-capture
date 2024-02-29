package com.carboncapture.carbon.framework.exception;

import java.io.PrintStream;

/**
 * @author Santa Antilles
 * @description
 * @date 2024/2/29-16:28:05
 */
public class UnAuthenticatedException extends Exception{
    public UnAuthenticatedException(final String msg){
        super(msg);
    }

    @Override
    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
    }
}

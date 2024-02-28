package com.carboncapture.carbon.framework.exception;


import java.io.PrintStream;

/**
 * @author Santa Antilles
 * @description
 * @date 2024/2/29-01:46:42
 */
public class UnsupportedFileExtensionException extends Exception{

    public UnsupportedFileExtensionException(final String msg){
        super(msg);
    }

    @Override
    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);

    }
}

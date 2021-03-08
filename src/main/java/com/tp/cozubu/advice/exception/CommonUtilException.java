package com.tp.cozubu.advice.exception;

public class CommonUtilException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1291658484551919953L;
    public CommonUtilException(String msg, Throwable t) {
        super(msg, t);
    }
     
    public CommonUtilException(String msg) {
        super(msg);
    }
     
    public CommonUtilException() {
        super();
    }

}
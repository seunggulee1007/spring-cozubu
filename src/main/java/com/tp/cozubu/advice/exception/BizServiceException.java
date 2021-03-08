package com.tp.cozubu.advice.exception;

public class BizServiceException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1291658484551919953L;
    public BizServiceException(String msg, Throwable t) {
        super(msg, t);
    }
     
    public BizServiceException(String msg) {
        super(msg);
    }
     
    public BizServiceException() {
        super();
    }

}
package com.tp.cozubu.advice.exception;


import com.tp.cozubu.enums.CommonMsg;

/**
 * 버전 체크
 * @author es-seungglee
 *
 */
public class FalseIDException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1291658484551919953L;
    public FalseIDException(String msg, Throwable t) {
        super(msg, t);
    }
     
    public FalseIDException(String msg) {
        super(msg);
    }
     
    public FalseIDException() {
        super(CommonMsg.FALSE_ID.getMsg());
    }

}

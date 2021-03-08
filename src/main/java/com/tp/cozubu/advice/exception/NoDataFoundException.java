package com.tp.cozubu.advice.exception;


import com.tp.cozubu.enums.CommonMsg;

/**
 * 버전 체크
 * @author es-seungglee
 *
 */
public class NoDataFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1291658484551919953L;
    public NoDataFoundException(String msg, Throwable t) {
        super(msg, t);
    }
     
    public NoDataFoundException(String msg) {
        super(msg);
    }
     
    public NoDataFoundException() {
        super(CommonMsg.NO_DATA_FOUND.getMsg());
    }

}

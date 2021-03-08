package com.tp.cozubu.advice.exception;


import com.tp.cozubu.enums.CommonMsg;

/**
 * 버전 체크
 * @author es-seungglee
 *
 */
public class DuplicationException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1291658484551919953L;
    public DuplicationException(String msg, Throwable t) {
        super(msg, t);
    }

    public DuplicationException(String msg) {
        super(msg);
    }

    public DuplicationException() {
        super(CommonMsg.NO_VERSION.getMsg());
    }

}

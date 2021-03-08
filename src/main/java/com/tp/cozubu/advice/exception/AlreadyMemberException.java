package com.tp.cozubu.advice.exception;

import com.tp.cozubu.enums.CommonMsg;

/**
 * 버전 체크
 * @author es-seungglee
 *
 */
public class AlreadyMemberException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1291658484551919953L;
    public AlreadyMemberException(String msg, Throwable t) {
        super(msg, t);
    }
     
    public AlreadyMemberException(String msg) {
        super(msg);
    }
     
    public AlreadyMemberException() {
        super(CommonMsg.ALREADY_MEMBER.getMsg());
    }

}

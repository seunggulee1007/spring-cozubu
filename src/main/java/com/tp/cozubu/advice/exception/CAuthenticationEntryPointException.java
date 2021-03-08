package com.tp.cozubu.advice.exception;

public class CAuthenticationEntryPointException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = -7452366245688907860L;

    public CAuthenticationEntryPointException(String msg, Throwable t) {
        super(msg, t);
    }

    public CAuthenticationEntryPointException(String msg) {
        super(msg);
    }

    public CAuthenticationEntryPointException() {
        super();
    }
}

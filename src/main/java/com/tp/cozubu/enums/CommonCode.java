package com.tp.cozubu.enums;

/**
 * 공통 코드 집합
 * @author es-seungglee
 *
 */
public enum CommonCode {

    SUCCESS(0),
    FAIL(-1),
    FORBIDDEN(-1001),
    SAVE(1),
    MODIFY(2),
    DELETE(3),
    USER_TABLE(1)
    ;
    int code;
    CommonCode(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
    
}

package com.tp.cozubu.enums;

/**
 * 리턴용 메시지
 * @author es-seungglee
 *
 */
public enum CommonMsg {

    SUCCESS_WRITE("저장에 성공하였습니다."),
    SUCCESS_MODIFY("수정에 성공하였습니다."),
    SUCCESS_DELETE("삭제하였습니다."),
    FAIL_WRITE("저장에 실패하였습니다."),
    FAIL_MODIFY("수정에 실패하였습니다."),
    FAIL_DELETE("삭제에 실패하였습니다."),
    NO_VERSION("버전 정보가 잘못 되었습니다. 버전을 확인해 주세요"),
    ALREADY_MEMBER("이미 존재하는 아이디 입니다. 다른 아이디를 입력해 주세요"),
    NO_MEMBER("계정이 존재하지 않는 회원입니다."),
    FALSE_ID("비밀번호가 틀렸습니다."),
    SUCCESS_LOGIN("로그인이 성공하였습니다."),
    NO_DATA_FOUND("조회된 데이터가 없습니다."),
    SUCCESS_SEARCH("조회에 성공하였습니다."),
    EXPIRE_LOGIN("토큰 유효기간이 만료되었습니다. 다시 로그인 해 주세요."),
    SUCCESS_SYNC("동기화에 성공하였습니다."),
    DUPLE_AUTH_GROUP_NM("중복된 그룹 명이 있습니다."),
    AVAILABLE_AUTH_GROUP_NM("사용 가능한 그룹명 입니다."),
    AVALIABLE_CLIENT("사용 가능한 거래처 명 입니다."),
    ;
    String msg;
    CommonMsg(String msg) {
        this.msg = msg;
    }
    
    public String getMsg() {
        return msg;
    }
    
}

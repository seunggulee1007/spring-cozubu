package com.tp.cozubu.advice;


import com.tp.cozubu.advice.exception.*;
import com.tp.cozubu.enums.CommonCode;
import com.tp.cozubu.enums.CommonMsg;
import com.tp.cozubu.model.vo.common.ResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 커스텀 예외 처리
 * @author es-seungglee
 *
 */
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    protected ResultVO defaultException(Exception e) {
        e.printStackTrace();
        ResultVO resultVO = ResultVO.builder()
                .result(CommonCode.FAIL.getCode())
                .resultMsg(e.getMessage())
                .errMsg(e.getStackTrace()[0].toString())
                .build();
        return resultVO;
    }
    
    @ExceptionHandler(NoVersionException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResultVO noVersionException(Exception e) {
        ResultVO resultVO = ResultVO.builder()
                .result(CommonCode.FAIL.getCode())
                .resultMsg(e.getMessage())
                .errMsg(e.getStackTrace()[0].toString())
                .build();
        return resultVO;
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultVO accessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        ResultVO resultVO = ResultVO.builder()
                .result(CommonCode.FAIL.getCode())
                .resultMsg(e.getMessage())
                .errMsg(e.getStackTrace()[0].toString())
                .build();
        return resultVO;
    }

    @ExceptionHandler(CAuthenticationEntryPointException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultVO CEmailSigninFailedException(CAuthenticationEntryPointException e, HttpServletRequest request) {
        ResultVO resultVO = ResultVO.builder()
                .result(CommonCode.FAIL.getCode())
                .resultMsg(CommonMsg.EXPIRE_LOGIN.getMsg())
                .build();
        return resultVO;
    }
    
    @ExceptionHandler(NoMemberException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResultVO NoMemberException(NoMemberException e, HttpServletRequest request) {
        ResultVO resultVO = ResultVO.builder()
                .result(CommonCode.FAIL.getCode())
                .resultMsg(e.getMessage())
                .errMsg(e.getStackTrace()[0].toString())
                .build();
        return resultVO;
        
    }
    
    @ExceptionHandler(AlreadyMemberException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResultVO AlreadyMemberException(AlreadyMemberException e, HttpServletRequest request) {
        ResultVO resultVO = ResultVO.builder()
                .result(CommonCode.FAIL.getCode())
                .resultMsg(e.getMessage())
                .errMsg(e.getStackTrace()[0].toString())
                .build();
        return resultVO;
    }
    
    @ExceptionHandler(FalseIDException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResultVO FalseIDException(FalseIDException e, HttpServletRequest request) {
        ResultVO resultVO = ResultVO.builder()
                .result(CommonCode.FAIL.getCode())
                .resultMsg(e.getMessage())
                .errMsg(e.getStackTrace()[0].toString())
                .build();
        return resultVO;
    }
    
    @ExceptionHandler(BizServiceException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResultVO BizServiceException(BizServiceException e, HttpServletRequest request) {
        return ResultVO.builder()
                .result(CommonCode.FAIL.getCode())
                .resultMsg(e.getMessage())
                .errMsg(e.getStackTrace()[0].toString())
                .build();
    }
    
    @ExceptionHandler(CommonUtilException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResultVO CommonUtilException(CommonUtilException e, HttpServletRequest request) {
        return ResultVO.builder()
                .result(CommonCode.FAIL.getCode())
                .resultMsg(e.getMessage())
                .errMsg(e.getStackTrace()[0].toString())
                .build();
    }

    @ExceptionHandler(DuplicationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResultVO DuplicationException(DuplicationException e) {
        return ResultVO.builder()
                .result(CommonCode.FAIL.getCode())
                .resultMsg(e.getMessage())
                .errMsg(e.getStackTrace()[0].toString())
                .build();
    }
    
}

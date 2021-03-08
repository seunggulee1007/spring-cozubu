package com.tp.cozubu.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tp.cozubu.advice.exception.AlreadyMemberException;
import com.tp.cozubu.model.service.common.user.UserService;
import com.tp.cozubu.model.vo.common.ResultVO;
import com.tp.cozubu.model.vo.common.UserVO;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SignController {

    private final UserService userService;
    
    @PostMapping("/signUp")
    @ApiOperation(value="회원 등록", notes="회원 등록")
    public ResultVO signUp(UserVO userVO)  throws AlreadyMemberException {
        return userService.signUp(userVO);
    }
    
    @PostMapping("/signIn")
    @ApiOperation(value="로그인", notes="회원 로그인")
    public ResultVO signIn(UserVO userVO) {
        return userService.signIn(userVO);
    }
    
    @PostMapping("/chgPwd")
    @ApiOperation(value="비밀번호변경", notes="비밀번호 변경 프로세스")
    public ResultVO chgPwd(UserVO userVO) {
        return userService.chgPwd(userVO);
    }
}

package com.tp.cozubu.controller;

import com.tp.cozubu.advice.exception.AlreadyMemberException;
import com.tp.cozubu.model.entity.User;
import com.tp.cozubu.model.service.common.user.UserService;
import com.tp.cozubu.model.vo.common.ResultVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SignController {

    private final UserService userService;

    @PostMapping("/signUp")
    @ApiOperation(value = "회원 등록", notes = "회원 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "사용자명", required = true, dataType = "string"),
            @ApiImplicitParam(name = "password", value = "비밀번호", required = true, dataType = "string", format = "password"),
            @ApiImplicitParam(name = "authenticated", value = "인증", required = true, dataType = "boolean", defaultValue = "true"),
    })
    public ResultVO signUp(User userVO) throws AlreadyMemberException {
        return userService.signUp(userVO);
    }

    @PostMapping("/signIn")
    @ApiOperation(value = "로그인", notes = "회원 로그인")
    public ResultVO signIn(User userVO) {
        return userService.signIn(userVO);
    }

    @PutMapping("/updatePwd")
    @ApiOperation(value = "비밀번호 변경", notes = "회원 비밀번호 변경")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "사용자명", required = true, dataType = "string"),
            @ApiImplicitParam(name = "nowPwd", value = "비밀번호", required = true, dataType = "string", format = "password"),
            @ApiImplicitParam(name = "chgPwd", value = "비밀번호", required = true, dataType = "string", format = "password"),
            @ApiImplicitParam(name = "confirmPwd", value = "비밀번호", required = true, dataType = "string", format = "password"),
    })
    public ResultVO updatePwd(String username, String nowPwd, String chgPwd, String confirmPwd) throws Exception {
        return userService.updatePwd(username, nowPwd, chgPwd, confirmPwd);
    }
}

package com.tp.cozubu.model.service.common.user;

import com.tp.cozubu.advice.exception.AlreadyMemberException;
import com.tp.cozubu.model.vo.common.ResultVO;
import com.tp.cozubu.model.vo.common.UserVO;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    ResultVO selectUserList(int deptCd);

    ResultVO signUp(UserVO userVO) throws AlreadyMemberException;
    
    ResultVO signIn(UserVO userVO);
    
    ResultVO chgPwd(UserVO userVO);

    ResultVO insertUser(HttpServletRequest request, UserVO userVO);

    ResultVO updateUser(HttpServletRequest request, UserVO userVO);

}

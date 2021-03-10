package com.tp.cozubu.model.service.common.user;

import com.tp.cozubu.advice.exception.AlreadyMemberException;
import com.tp.cozubu.model.entity.User;
import com.tp.cozubu.model.vo.common.ResultVO;

public interface UserService {

    ResultVO signUp(User userVO) throws AlreadyMemberException;

    ResultVO signIn(User userVO);

    ResultVO updatePwd(String username, String nowPwd, String chgPwd, String confirmPwd) throws Exception;

}

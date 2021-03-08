package com.tp.cozubu.model.service.common.security;


import com.tp.cozubu.advice.exception.NoMemberException;
import com.tp.cozubu.model.dao.UserDao;
import com.tp.cozubu.model.vo.common.UserDetailVO;
import com.tp.cozubu.model.vo.common.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserDao userDao;
    
    public UserDetails loadUserByUsername(String userPk) {
         UserVO userVO = userDao.selectUser(userPk).orElseThrow(NoMemberException::new);
         UserDetailVO user = UserDetailVO.builder().build();
         user.setUserId(userVO.getUserId());
         user.setPassword(userVO.getPassword());
         return user;
    }
}

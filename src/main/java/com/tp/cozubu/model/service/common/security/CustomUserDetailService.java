package com.tp.cozubu.model.service.common.security;


import com.tp.cozubu.advice.exception.NoMemberException;
import com.tp.cozubu.model.entity.User;
import com.tp.cozubu.model.mapper.UserMapper;
import com.tp.cozubu.model.vo.common.UserDetailVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserMapper userDao;

    public UserDetails loadUserByUsername(String userPk) {
        User userVO = userDao.findByUsername(userPk).orElseThrow(NoMemberException::new);
        UserDetailVO user = UserDetailVO.builder().build();
        user.setUsername(userVO.getUsername());
        user.setPassword(userVO.getPassword());
        return user;
    }
}

package com.tp.cozubu.model.service.common.user;


import com.tp.cozubu.advice.exception.AlreadyMemberException;
import com.tp.cozubu.advice.exception.FalseIDException;
import com.tp.cozubu.advice.exception.NoMemberException;
import com.tp.cozubu.config.JwtTokenProvider;
import com.tp.cozubu.enums.CommonMsg;
import com.tp.cozubu.model.entity.User;
import com.tp.cozubu.model.mapper.UserMapper;
import com.tp.cozubu.model.vo.common.ResultVO;
import com.tp.cozubu.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final FileUtil fileUtil;

    /**
     * 회원 가입
     *
     * @param userVO
     * @return
     * @throws AlreadyMemberException
     */
    @Transactional
    public ResultVO signUp(User userVO) throws AlreadyMemberException {
        User user = userMapper.findByUsername(userVO.getUsername()).orElseGet(() -> new User());
        if (!StringUtils.isEmpty(user.getUsername())) {
            throw new AlreadyMemberException();
        }
        userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));
        userMapper.save(userVO);
        return ResultVO.builder().resultMsg("등록되었습니다").build();
    }

    /**
     * 로그인
     *
     * @param userVO
     * @return
     */
    public ResultVO signIn(User userVO) {
        List<String> authorities = new ArrayList<>();
        User user = userMapper.findByUsername(userVO.getUsername()).orElseThrow(NoMemberException::new);
        authorities.add("ROLE_USER");
        if (!passwordEncoder.matches(userVO.getPassword(), user.getPassword())) throw new FalseIDException();
        String token = jwtTokenProvider.createAccessToken(user.getUsername(), authorities);
        Map<String, Object> map = new HashMap<>();
        user.setPassword(null);
        map.put("authToken", token);
        map.put("user", user);
        return ResultVO.builder().data(map).resultMsg(CommonMsg.SUCCESS_LOGIN.getMsg()).build();
    }

    public ResultVO updatePwd(String username, String nowPwd, String chgPwd, String confirmPwd) throws Exception {
        if (!nowPwd.equals(chgPwd)) {
            throw new Exception("변경할 비밀번호가 일치하지 않습니다.");
        }
        User user = userMapper.findByUsername(username).orElseThrow(NoMemberException::new);
        if (!passwordEncoder.matches(nowPwd, user.getPassword())) throw new FalseIDException();
        user.setPassword(passwordEncoder.encode(chgPwd));
        userMapper.save(user);
        return ResultVO.builder().resultMsg("비밀번호가 변경 되었습니다.").build();
    }

}

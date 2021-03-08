package com.tp.cozubu.model.service.common.user;


import com.tp.cozubu.advice.exception.AlreadyMemberException;
import com.tp.cozubu.advice.exception.FalseIDException;
import com.tp.cozubu.advice.exception.NoMemberException;
import com.tp.cozubu.config.JwtTokenProvider;
import com.tp.cozubu.enums.CommonMsg;
import com.tp.cozubu.model.dao.UserDao;
import com.tp.cozubu.model.vo.common.FileVO;
import com.tp.cozubu.model.vo.common.ResultVO;
import com.tp.cozubu.model.vo.common.UserVO;
import com.tp.cozubu.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final FileUtil fileUtil;

    /**
     * 사용자 리스트 조회
     * @param deptCd
     * @return
     */
    public ResultVO selectUserList(int deptCd) {
        return ResultVO.builder().data(userDao.selectUserListByDeptId(deptCd)).build();
    }

    /**
     * 사용자 등록
     * @param request
     * @param userVO
     * @return
     */
    @Transactional
    public ResultVO insertUser(HttpServletRequest request, UserVO userVO) {
        UserVO user = userDao.selectUser(userVO.getUserId()).orElseGet(() -> new UserVO());
        if(!StringUtils.isEmpty(user.getUserId())) {
            throw new AlreadyMemberException();
        }
        userVO.setPassword(passwordEncoder.encode(userVO.getUserId() + "123!@"));

        return ResultVO.builder().resultMsg("등록되었습니다").build();
    }

    /**
     * 회원 가입
     * @param userVO
     * @return
     * @throws AlreadyMemberException
     */
    @Transactional
    public ResultVO signUp(UserVO userVO) throws AlreadyMemberException {
        UserVO user = userDao.selectUser(userVO.getUserId()).orElseGet(() -> new UserVO());
        if(!StringUtils.isEmpty(user.getUserId())) {
            throw new AlreadyMemberException();
        }
        userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));
        userDao.insertUser(userVO);
        return ResultVO.builder().resultMsg("등록되었습니다").build();
    }

    /**
     * 로그인
     * @param userVO
     * @return
     */
    public ResultVO signIn(UserVO userVO) {
        List<String> authorities = new ArrayList<>();
        UserVO user = userDao.selectUser(userVO.getUserId()).orElseThrow(NoMemberException::new);
        authorities.add("ROLE_USER");
        if (!passwordEncoder.matches(userVO.getPassword(), user.getPassword())) throw new FalseIDException();
        String token = jwtTokenProvider.createToken(user.getUserId(), authorities);
        Map<String, Object> map = new HashMap<>();
        user.setPassword(null);
        map.put("authToken", token);
        map.put("user", user);
        return ResultVO.builder().data(map).resultMsg(CommonMsg.SUCCESS_LOGIN.getMsg()).build();
    }

    @Transactional
    public ResultVO chgPwd(UserVO userVO) {
        UserVO user = userDao.selectUser(userVO.getUserId()).orElseThrow(NoMemberException::new);
        String resultMsg = "비밀번호 변경에 실패하였습니다.";
        long result = -1;
        if (!passwordEncoder.matches(userVO.getPassword(), user.getPassword())) throw new FalseIDException();
        if(userDao.updatePwd(userVO) > 0) {
            resultMsg = "비밀번호 변경에 성공하였습니다.";
            result = 0;
        }
        
        return ResultVO.builder().result(result).resultMsg(resultMsg).build();
        
    }

    /**
     * 사용자 변경
     * @param request
     * @param userVO
     * @return
     */
    @Transactional
    public ResultVO updateUser(HttpServletRequest request, UserVO userVO){
        userDao.updateUser(userVO);
        return ResultVO.builder().resultMsg("수정되었습니다").build();
    }

}

package com.tp.cozubu.model.dao;

import com.tp.cozubu.model.vo.common.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserDao {

    /** 부서 아이디로 유저 리스트 조회 */
    List<UserVO> selectUserListByDeptId(int deptId);
    /** 사용자 아이디로 해당 사용자 정보 조회 */
    Optional<UserVO> selectUser(String userId);
    /** 사용자 등록 */
    int insertUser(UserVO userVO);
    /** 비밀번호 변경 */
    int updatePwd(UserVO userVO);
    /** 사용자 변경 */
    int updateUser(UserVO userVO);
    
}

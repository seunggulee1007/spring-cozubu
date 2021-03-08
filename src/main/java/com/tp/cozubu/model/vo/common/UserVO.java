package com.tp.cozubu.model.vo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel
@EqualsAndHashCode(callSuper = false)
public class UserVO extends CommonVO {
    
    @ApiModelProperty(value="아이디", required=true)
    private String userId;
    @ApiModelProperty(value="비밀번호", required=true)
    private String password;
    @ApiModelProperty(value="변경 비밀번호")
    private String chgPwd;
    @ApiModelProperty(value="이름")
    private String userNm;
    @ApiModelProperty(value="관리자 여부")
    private String adminYn;
    @ApiModelProperty(value="부서 아이디")
    private int deptId;
    @ApiModelProperty(value="사원번호")
    private int empNo;
    @ApiModelProperty(value="휴대폰번호")
    private String phone;
    @ApiModelProperty(value="이메일")
    private String email;
    @ApiModelProperty(value="직급")
    private int rankCd;
    /** 직급명 */
    private String rankCdNm;
    @ApiModelProperty(value="사원 상태")
    private int empStatus;
    /** 사원 상태 명 */
    private String empStatusNm;
    /** 사진 */
    private String photo;
    /** 부서명 */
    private String deptNm;
}

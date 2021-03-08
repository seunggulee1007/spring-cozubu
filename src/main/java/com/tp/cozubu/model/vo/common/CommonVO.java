package com.tp.cozubu.model.vo.common;

import lombok.Data;

import java.sql.Date;

@Data
public class CommonVO {

    /** 등록자 */
    private String crtId;
    /** 등록일자 */
    private Date crtDtm;
    /** 수정자 */
    private String chgId;
    /** 수정일자 */
    private Date chgDtm;
    
}

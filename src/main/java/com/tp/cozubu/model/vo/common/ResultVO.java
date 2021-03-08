package com.tp.cozubu.model.vo.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @apiNote 공통 리턴용 vo
 * @apiNote exception이 일어나도 해당 vo로 리턴하기 때문에 화면에선 일정한 리턴 값을 받을 수 있음
 * @author es-seungglee
 *
 */
@Builder
@Getter
@Setter
@ToString
public class ResultVO {

    @Builder.Default
    private long result = 0;
    private String resultMsg;
    private String errMsg;
    private Object data;
    
}

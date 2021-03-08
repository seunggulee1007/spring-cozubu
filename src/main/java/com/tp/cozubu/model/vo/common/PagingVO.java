package com.tp.cozubu.model.vo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class PagingVO {
    
    @ApiModelProperty(value="전체 글 갯수")
    private int totalCnt;
    @ApiModelProperty(value="한 페이지에 보여줄 글 갯수")
    private int listCnt = 10;
    @ApiModelProperty(value="전체 페이지")
    private int totalPage;
    @ApiModelProperty(value="현재 페이지")
    private int pageNo;
    @ApiModelProperty(value="페이지 시작 번호")
    private int startPage;
    @ApiModelProperty(value="페이지 끝 번호")
    private int endPage;
    @ApiModelProperty(value="페이지 보여줄 갯수 (하단)")
    private int pageCnt = 10;
    @ApiModelProperty(value="검색조건 시작 날짜")
    private String searchStdDt;
    @ApiModelProperty(value="검색조건 종료 날짜")
    private String searchEndDt;
    @ApiModelProperty(value="검색어")
    private String searchKeyword;
    @ApiModelProperty(value="검색 종류")
    private String searchKind;
    @ApiModelProperty(value="검색 아이디")
    private String searchId;
    @ApiModelProperty(value="사용자  아이디")
    private String userId;
    
    public void calcPage(int totalCnt) {
        this.totalCnt = totalCnt;
        totalPage = totalCnt / listCnt;
        if(totalCnt % listCnt > 0) {
            totalPage ++;
        }
        if(totalPage < pageNo) {
            pageNo = totalPage;
        }
        startPage = ((pageNo-1) / pageCnt) * pageCnt +1;
        endPage = startPage + pageCnt -1;
        if(endPage > totalPage) {
            endPage = totalPage;
        }
    }
    
}

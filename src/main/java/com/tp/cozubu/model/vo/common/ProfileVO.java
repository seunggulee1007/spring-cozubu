package com.tp.cozubu.model.vo.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @apiNote 유저 프로필 vo
 * @author es-seungglee
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileVO {

    private int profileId;
    
    private String fileNm;
    
    private String originalFileNm;
    
    private String filePath;
    
    private long fileSize;
    
    private String contentType;
    
    private String extention;
    
    private Date crtDate;
    
    private String userId;

    public ProfileVO(FileVO fileVO) {
        this.fileNm = fileVO.getFileNm();
        this.originalFileNm = fileVO.getOriginalFileNm();
        this.filePath = fileVO.getFilePath();
        this.fileSize = fileVO.getFileSize();
        this.contentType = fileVO.getContentType();
        this.extention = fileVO.getExtention();
    }

}

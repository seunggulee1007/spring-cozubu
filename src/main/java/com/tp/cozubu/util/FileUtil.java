package com.tp.cozubu.util;

import com.tp.cozubu.model.vo.common.FileVO;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @apiNote 파일 처리 util
 * @apiNote 파일에관련된 처리(I/O)를 마치고 해당 파일에 대한 정보를
 * @apiNote fileVO 리스트 정보를 리턴해준다.
 * @author seungglee
 *
 */
@Component
public class FileUtil {
    
    @Value("${file.upload-dir}")
    private String SAVE_PATH;             // 파일 업로드 경로
    
    public List<FileVO> makeFileVO(MultipartHttpServletRequest request) {
        return makeFileVO(request, "");
    }
    
    /**
     * @apiNote 파일 업로드
     * @param request
     * @author seungglee
     * @return
     */
    public List<FileVO> makeFileVO(MultipartHttpServletRequest request, String filePath) {
        Iterator<String> fileNames = request.getFileNames();
        List<FileVO> fileVOList = new ArrayList<>();                                // 여러개 파일일 때를 대비한 list
        while(fileNames.hasNext()) {
            String fileName = fileNames.next();
            
            List<MultipartFile> fileList = request.getFiles(fileName);
            for(MultipartFile mFile : fileList) {
                String sourceFileName = mFile.getOriginalFilename();                // 실제 파일 이름
                String sourceFileNameExtension = FilenameUtils
                        .getExtension(sourceFileName)
                        .toLowerCase();                                             // 파일 확장자
                String destinationFileName = RandomStringUtils
                        .randomAlphanumeric(32) 
                        + "." + sourceFileNameExtension;                            // 저장될  파일 이름(확장자 포함)
                if(StringUtils.isNotEmpty(filePath)) {                              // 각자 저장될 경로가 다르기 때문에
                    if(StringUtils.isEmpty(SAVE_PATH)) {
                        SAVE_PATH = "";
                    }
                    SAVE_PATH+=filePath;
                }
                File file = new File(SAVE_PATH + destinationFileName);              // 저장할 파일 생성
                
                if(mFile.getSize() != 0) {                                          // file null check
                    if(!file.exists()) {                                            // 해당 경로가 존재하는지 판단 후
                        if(file.getParentFile().mkdirs()) {                         // 경로에 해당하는 디렉토리들을 생성
                            try {
                                file.createNewFile();                               // 파일 생성
                            }catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                try {
                    mFile.transferTo(file);                                         // 실제 경로에 multipartFile을 전송
                }catch (IllegalStateException e) {
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
                
                FileVO fileVO = FileVO.builder()
                        .fileNm(destinationFileName)                                // 변환된 파일 이름
                        .fileSize(mFile.getSize())                                  // 파일 크기
                        .originalFileNm(mFile.getOriginalFilename())                // 실제 파일이름
                        .contentType(mFile.getContentType())                        // 컨텐트 타입
                        .extention(sourceFileNameExtension)                         // 확장자
                        .filePath(SAVE_PATH)                                        // 저장경로
                        .build();
                
                fileVOList.add(fileVO);
            }
        }
        return fileVOList;
    }
    
    public boolean deleteFile(String fileNm) {
        return deleteFile(fileNm, "");
    }

    public boolean deleteFile(String fileNm, String filePath) {
        if(StringUtils.isEmpty(SAVE_PATH)) {
            SAVE_PATH = "";
        }
        if(StringUtils.isNotEmpty(filePath)) {
            SAVE_PATH += filePath;
        }
        File file = new File(SAVE_PATH+fileNm);
        if(file.exists()) {
            if(file.isDirectory()) {
                File[] files = file.listFiles();
                for(File f : files) {
                    if(!f.delete()) {
                        return false;
                    }
                }
            }
            if(!file.delete()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @apiNote 파일 다운로드
     * @author seungglee
     * @param fileVO
     * @return
     * @throws IOException
     */
    public ResponseEntity<Resource> download(FileVO fileVO) throws IOException {
        String fileNm = fileVO.getFilePath() + fileVO.getFileNm();
        File file = new File(fileNm);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        
        return ResponseEntity.ok() 
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(fileVO.getOriginalFileNm().getBytes("UTF-8"), "ISO-8859-1") + "\"") 
                .contentLength(file.length()) 
                .contentType(MediaType.parseMediaType(fileVO.getContentType())) 
                .body(resource);
    }
    
}

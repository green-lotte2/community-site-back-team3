package kr.co.orangenode.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PageService {

    @Value("${file.upload.path}")
    private String fileUploadPath;

    // 파일 업로드
    public void upload(MultipartFile pagefile) {
        log.info("파일 업로드 ... 1");
        // 이미지 파일 등록 : 해당 디렉토리 없을 경우 자동 생성
        String path = new File(fileUploadPath).getAbsolutePath();

        // oName, sName 구하기
        String oName = pagefile.getOriginalFilename();
        log.info("파일 업로드 ... 2 " + oName);
        String ext = oName.substring(oName.lastIndexOf("."));
        log.info("파일 업로드 ... 3 " + ext);
        String sName = UUID.randomUUID().toString() + ext;
        log.info("파일 업로드 ... 4 path : " + path);

        // upload
        File destinationFile = new File(path, sName);

        try {
            pagefile.transferTo(destinationFile);
            log.info("파일 업로드 ... 5");
            log.info("file : {}", destinationFile.getAbsolutePath());
        } catch (IOException e) {
            log.error("파일 업로드 실패", e);
            throw new RuntimeException("파일 업로드 실패", e);
        }
    }
}


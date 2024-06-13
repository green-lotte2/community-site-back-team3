package kr.co.orangenode.service;

import kr.co.orangenode.dto.page.BlockDTO;
import kr.co.orangenode.dto.page.PageDTO;
import kr.co.orangenode.entity.page.Page;
import kr.co.orangenode.mapper.PageMapper;
import kr.co.orangenode.repository.page.PageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PageService {

    private final PageRepository pageRepository;
    private final PageMapper pageMapper;

    private final ModelMapper modelMapper;

    @Value("${file.upload.path}")
    private String fileUploadPath;

    // 페이지 목록 조회
    public ResponseEntity<?> selectPages(String uid){
        List<PageDTO> pageDTOS = pageRepository.findAllByUid(uid)
                .stream()
                .map(entity -> {
                    PageDTO pageDTO = modelMapper.map(entity, PageDTO.class);
                    return pageDTO;
                })
                .toList();
        return ResponseEntity.ok().body(pageDTOS);
    }
    // 페이지 조회
    public ResponseEntity<?> selectPage(int pageNo){
        return ResponseEntity.ok().body(pageRepository.findByPageNo(pageNo));
    }
    // 파일 업로드
    public ResponseEntity<String> upload(MultipartFile pagefile) {
        log.info("파일 업로드 ... 1");
        // 이미지 파일 등록 : 해당 디렉토리 없을 경우 자동 생성
        String path = new File(fileUploadPath).getAbsolutePath();

        // oName, sName 구하기
        String oName = pagefile.getOriginalFilename();
        String ext = oName.substring(oName.lastIndexOf("."));
        String sName = UUID.randomUUID().toString() + ext;

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
        log.info("sName : " +sName);
        return ResponseEntity.ok().body(sName);
    }
    // 페이지 생성
    public ResponseEntity<?> insertPage(PageDTO pageDTO){
        Page page = pageRepository.save(modelMapper.map(pageDTO, Page.class));
        // page 리턴
        return ResponseEntity.ok().body(page);
    }

    // 페이지 저장
    public void updateTitle(PageDTO pageDTO){
        log.info("update PAge Serv");

        pageRepository.save(modelMapper.map(pageDTO, Page.class));
    }
}


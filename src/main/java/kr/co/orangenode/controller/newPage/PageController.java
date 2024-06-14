package kr.co.orangenode.controller.newPage;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.orangenode.dto.page.PageDTO;
import kr.co.orangenode.service.PageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.server.UID;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PageController {

    private final ObjectMapper objectMapper;
    private final PageService pageService;

    // 페이지 목록 조회
    @GetMapping("/pages")
    public ResponseEntity<?> select(@RequestParam("uid") String uid){
        log.info("페이지 목록 조회 : " + uid);
        return pageService.selectPages(uid);
    }

    // 페이지 조회
    @GetMapping("/page")
    public ResponseEntity<?> select(@RequestParam("pageNo") int pageNo){
        log.info("페이지 조회 : " + pageNo);
        return pageService.selectPage(pageNo);
    }
    // 페이지 생성
    @PostMapping("/page")
    public ResponseEntity<?> insertPage(@RequestBody PageDTO pageDTO){
        log.info("pageDTO : " + pageDTO);
        return pageService.insertPage(pageDTO);
    }
    // 페이지 내용 저장
    @PostMapping("/savepage")
    public void savePage(@RequestBody PageDTO pageDTO) throws IOException {
        log.info("받은 데이터 : {}", pageDTO.getContent());
        log.info("받은 제목 : {}", pageDTO.getTitle());

        log.info("pageNo : "+ pageDTO.getPageNo());

        // 현재 시간 설정
        pageDTO.setUDate(LocalDateTime.now());

        // 페이지 저장
        pageService.updateTitle(pageDTO);
    }

    // 파일 전송 테스트
    @PostMapping("/page/upload")
    public ResponseEntity<String> upload(MultipartFile file) {
        log.info("upload  !!! ");
        log.info(file.toString());
        return pageService.upload(file);
    }
}

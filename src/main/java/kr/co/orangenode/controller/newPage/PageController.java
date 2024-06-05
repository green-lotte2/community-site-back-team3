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
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PageController {

    private final ObjectMapper objectMapper;
    private final PageService pageService;

    // 페이지 조회
    @GetMapping("/page")
    public ResponseEntity<?> select(@RequestParam("uid") String uid){
        log.info("페이지 조회 : " + uid);
        return pageService.selectPages(uid);
    }
    // 페이지 생성
    @PostMapping("/page")
    public ResponseEntity<?> insertPage(@RequestBody PageDTO pageDTO){
        log.info("pageDTO : " + pageDTO);
        return pageService.insertPage(pageDTO);
    }
    // 페이지 내용 저장
    @PostMapping("/savepage")
    public void savePage(@RequestParam("data") String data) throws IOException {
        log.info("받은 데이터 : {}", data);

        // JSON 데이터를 역직렬화
        Map<String, Object> articleData = objectMapper.readValue(data, Map.class);
        List<Map<String, Object>> blocks = (List<Map<String, Object>>) articleData.get("blocks");
        log.info("저장할 데이터 : {}", articleData);
        log.info("저장할 블록들 : {}", blocks);

    }

    // 파일 전송 테스트
    @PostMapping("/page/upload")
    public ResponseEntity<String> upload(MultipartFile imgFile) {
        log.info("upload  !!! ");
        log.info(imgFile.toString());
        return pageService.upload(imgFile);
    }
}

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

        // JSON 데이터를 역직렬화
        // Map<String, Object> articleData = objectMapper.readValue(data, Map.class);
        //List<Map<String, Object>> blocks = (List<Map<String, Object>>) articleData.get("blocks");
        log.info("pageNo : "+ pageDTO.getPageNo());


        // 제목 저장
        pageService.updateTitle(pageDTO);
        // 블록 저장
        //pageService.insertBlocks(blocks, pageNo);

    }
    // 페이지 내용 불러오기
    @GetMapping("/block")
    public ResponseEntity<?> selectBlocks(@RequestParam("pageNo") int pageNo){
        log.info("페이지 내용 Cont : " + pageNo);
        return pageService.selectBlocks(pageNo);
    }

    // 파일 전송 테스트
    @PostMapping("/page/upload")
    public ResponseEntity<String> upload(MultipartFile file) {
        log.info("upload  !!! ");
        log.info(file.toString());
        return pageService.upload(file);
    }
}

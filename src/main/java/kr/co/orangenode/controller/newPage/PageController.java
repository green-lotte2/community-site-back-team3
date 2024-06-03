package kr.co.orangenode.controller.newPage;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    // 페이지 저장 
    @PostMapping("/savepage")
    public void savePage(@RequestParam("data") String data,
                         @RequestBody  Map<String, MultipartFile> files) throws IOException {
        log.info("Received data: {}", data);

        // JSON 데이터를 역직렬화
        Map<String, Object> articleData = objectMapper.readValue(data, Map.class);
        List<Map<String, Object>> blocks = (List<Map<String, Object>>) articleData.get("blocks");

        // 이미지 파일 처리 및 URL 업데이트
        for (Map.Entry<String, MultipartFile> entry : files.entrySet()) {
            String key = entry.getKey();
            MultipartFile file = entry.getValue();

            // 파일이 있다면
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                //File dest = new File("path" + fileName);
                //file.transferTo(dest);
                log.info("저장할 파일 : {}", fileName);

                // 블록 데이터에서 해당 이미지 블록의 URL을 업데이트
                int index = Integer.parseInt(key.split("_")[1]);
                Map<String, Object> block = blocks.get(index);
                Map<String, Object> dataMap = (Map<String, Object>) block.get("data");
                dataMap.put("url", "가져온 이미지 경로" + fileName);
            }
        }

        log.info("수정할 데이터 : {}", articleData);
    }

    // 파일 전송 테스트
    @PostMapping("/page/upload")
    public void upload(@RequestBody MultipartFile file) {
        log.info("upload  !!! ");
        log.info(file.toString());
    }
}

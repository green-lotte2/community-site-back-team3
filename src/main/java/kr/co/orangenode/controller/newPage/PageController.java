package kr.co.orangenode.controller.newPage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
                         @RequestParam Map<String, MultipartFile> files) throws IOException {
        log.info("Received data: {}", data);

        // JSON 데이터를 역직렬화
        Map<String, Object> articleData = objectMapper.readValue(data, Map.class);
        List<Map<String, Object>> blocks = (List<Map<String, Object>>) articleData.get("blocks");

        // 이미지 파일 처리 및 URL 업데이트
        for (Map.Entry<String, MultipartFile> entry : files.entrySet()) {
            String key = entry.getKey();
            MultipartFile file = entry.getValue();
            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                //File dest = new File("path/to/save/" + fileName);
                //file.transferTo(dest);
                log.info("Saved file: {}", fileName);

                // 블록 데이터에서 해당 이미지 블록의 URL을 업데이트
                int index = Integer.parseInt(key.split("_")[1]);
                Map<String, Object> block = blocks.get(index);
                Map<String, Object> dataMap = (Map<String, Object>) block.get("data");
                dataMap.put("url", "/path/to/save/" + fileName);
            }
        }

        // 업데이트된 데이터를 저장하거나 필요한 처리를 수행
        // 예: ArticleData updatedArticleData = objectMapper.convertValue(articleData, ArticleData.class);
        log.info("Updated data: {}", articleData);
    }

    private void saveImage(String base64Image) {
        // Remove the data:image/png;base64, part if present
        if (base64Image.contains(",")) {
            base64Image = base64Image.split(",")[1];
        }
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        try (FileOutputStream fos = new FileOutputStream("saved_image.png")) {
            fos.write(imageBytes);
            log.info("Image saved successfully.");
        } catch (IOException e) {
            log.error("Error saving image: ", e);
        }
    }
}

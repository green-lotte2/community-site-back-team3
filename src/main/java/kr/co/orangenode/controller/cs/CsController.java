package kr.co.orangenode.controller.cs;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.orangenode.dto.cs.QuestionDTO;
import kr.co.orangenode.service.CsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor

public class CsController {

    private final CsService csService;

    @GetMapping("/csCate")
    public ResponseEntity<?> getAllChatRooms() {
        log.info("컨트롤러");
        return csService.selectCsCate();
    }

    @GetMapping("/selectCs")
    public ResponseEntity<?> selectCs(@RequestParam String cateName) {
        log.info("cs컨트롤러 " + cateName);
        return csService.selectCs(cateName);
    }

    @PostMapping("/cs/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile[] imgFiles) {

        log.info(imgFiles.toString());

        return csService.uploadFile(imgFiles);
    }

    @PostMapping("/cs/insert")
    public ResponseEntity<?> insertQuestion(HttpServletRequest req, @RequestBody QuestionDTO questionDTO) {

        log.info("cs 글작성 컨트롤러" + questionDTO.toString());
        String ip = req.getRemoteAddr();
        questionDTO.setIp(ip);

        return csService.insertQuestion(questionDTO);
    }
}

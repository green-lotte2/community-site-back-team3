package kr.co.orangenode.controller.cs;

import kr.co.orangenode.service.CsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<?> upload(@RequestParam MultipartFile file) {
        return null;
    }
}

package kr.co.orangenode.controller.cs;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.orangenode.dto.cs.CsDTO;
import kr.co.orangenode.dto.cs.QuestionDTO;
import kr.co.orangenode.service.cs.CsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

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

    @GetMapping("/cs/selects")
    public ResponseEntity<?> csSelects() {
        log.info("admin cs 내용 가져오기");
        return csService.csSelects();
    }

    @GetMapping("/cs/delete")
    public ResponseEntity<?> csDelete(@RequestParam int[] csNo) {
        log.info("admin cs 삭제하기" + Arrays.toString(csNo));

        return csService.deleteCs(csNo);
    }

    @GetMapping("/cs/select")
    public ResponseEntity<?> csSelect(@RequestParam int csNo) {
        log.info("글 하나 가져오기" + csNo);

        return csService.csSelect(csNo);
    }

    @PostMapping("/cs/modify")
    public ResponseEntity<?> csModify(@RequestBody CsDTO csDTO) {
        log.info("cs글 수정"+csDTO);
        return csService.csModify(csDTO);
    }

    @PostMapping("/cs/write")
    public ResponseEntity<?> csWrite(@RequestBody CsDTO csDTO, HttpServletRequest req) {
        String ip = req.getRemoteAddr();
        csDTO.setIp(ip);

        log.info("관리자 cs 글 쓰기" + csDTO);

        return csService.csWrite(csDTO);
    }
}

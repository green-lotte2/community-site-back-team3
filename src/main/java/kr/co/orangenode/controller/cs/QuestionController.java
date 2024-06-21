package kr.co.orangenode.controller.cs;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.orangenode.dto.cs.QuestionDTO;
import kr.co.orangenode.repository.cs.QuestionRepository;
import kr.co.orangenode.service.cs.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    /** 관리자 문의하기 글 가져오기*/
    @GetMapping("/question/select")
    public ResponseEntity<?> selectQuestion( ) {
        log.info("관리자 문의하기 목록 가져오기");
        
        return questionService.selectQuestion();
    }

    /** 관리자 문의하기 답변하기*/
    @PostMapping("/question/answer")
    public ResponseEntity<?> selectQuestion(@RequestBody QuestionDTO questionDTO, HttpServletRequest req) {
        log.info("관리자 문의하기 답변하기");
        String ip = req.getRemoteAddr();

        questionDTO.setIp(ip);

        return questionService.answerQuestion(questionDTO);
    }
}

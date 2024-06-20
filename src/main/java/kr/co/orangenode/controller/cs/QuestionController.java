package kr.co.orangenode.controller.cs;

import kr.co.orangenode.repository.cs.QuestionRepository;
import kr.co.orangenode.service.cs.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/question/select")
    public ResponseEntity<?> selectQuestion( ) {
        log.info("관리자 문의하기 목록 가져오기");
        
        return questionService.selectQuestion();
    }
}

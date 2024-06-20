package kr.co.orangenode.controller.user;

import kr.co.orangenode.dto.terms.TermsDTO;
import kr.co.orangenode.service.user.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TermsController {

    private final TermsService termsService;

    // 이용 약관 조회
    @GetMapping("/user/terms")
    public ResponseEntity<?> getTerms() {
        List<TermsDTO> termsList  = termsService.getAllTerms();
        return ResponseEntity.ok(termsList);
    }
}

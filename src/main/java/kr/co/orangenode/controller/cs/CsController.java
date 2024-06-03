package kr.co.orangenode.controller.cs;

import kr.co.orangenode.service.CsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}

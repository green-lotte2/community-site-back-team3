package kr.co.orangenode.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    // 메인페이지 매핑
    @GetMapping(value = {"/index", "/"})
    public String indexPage() {
        return "/index";
    }
}

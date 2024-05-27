package kr.co.orangenode.contoller.user;


import jakarta.servlet.http.HttpSession;
import kr.co.orangenode.service.user.EmailCheckService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@ToString
public class EmaliCheckController {

    private final EmailCheckService emailCheckService;

    // 회원가입 유저 정보 중복 체크 (아이디, 전화번호, 이메일)
    @GetMapping("/member/checkUser/{type}/{value}")
    public ResponseEntity<?> registerUserCheck(HttpSession session, @PathVariable("type") String type, @PathVariable("value") String value){
        // service에서 중복 체크
        int result = emailCheckService.UserCheck(session, type, value);
        log.info("session :" + session);

        String code = (String) session.getAttribute("code");
        log.info("session code : " + code);

        // json 형식으로 변환
        Map<String, Integer> data = new HashMap<>();
        data.put("result", result);
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/member/checkEmailCode/{inputCode}")
    public ResponseEntity<?> checkEmailCode(HttpSession session, @PathVariable("inputCode") String inputCode){
        // 서버에서 발급한 인증 코드
        log.info("checkEmailCode:" + session.toString());
        String code = (String) session.getAttribute("code");
        log.info("checkEmailCode:" + session.getAttribute("code"));
        log.info("code:" + code);
        // 회원가입하는 사용자가 입력한 코드
        String checkCode = inputCode;
        log.info("CheckCode: " + inputCode);
        Map<String, Integer> data = new HashMap<>();
        if(code != null && code.equals(checkCode)){
            data.put("result", 0);
            return ResponseEntity.ok().body(data);
        }else {
            data.put("result", 1);
            return ResponseEntity.ok().body(data);
        }
    }


}
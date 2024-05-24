package kr.co.orangenode.contoller.user;

import jakarta.servlet.http.HttpSession;
import kr.co.orangenode.service.user.EmailCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EmaliCheckController {

    private final EmailCheckService emailCheckService;

    // 회원가입 유저 정보 중복 체크 (아이디, 전화번호, 이메일)
    @GetMapping("/member/checkUser/{type}/{value}")
    public ResponseEntity<?> registerUserCheck(HttpSession session, @PathVariable("type") String type, @PathVariable("value") String value){
        // service에서 중복 체크
        int result = emailCheckService.UserCheck(session, type, value);
        // json 형식으로 변환
        Map<String, Integer> data = new HashMap<>();
        data.put("result", result);
        return ResponseEntity.ok().body(data);
    }

    // 이메일 인증코드 체크
    @GetMapping("/member/checkEmailCode/{inputCode}")
    public ResponseEntity<?> checkEmailCode(HttpSession session, @PathVariable("inputCode") String inputCode){
        // 서버에서 발급한 인증 코드
        String code = (String) session.getAttribute("code");
        log.info("code:" + code);
        // 회원가입하는 사용자가 입력한 코드
        String checkCode = inputCode;
        Map<String, Integer> data = new HashMap<>();
        if(code.equals(checkCode)){
            //json 형식으로 변환
            data.put("result", 0);
            return ResponseEntity.ok().body(data);
        }else {
            //json 형식으로 변환
            data.put("result", 1);
            return ResponseEntity.ok().body(data);
        }
    }
}

package kr.co.orangenode.controller.user;


import jakarta.servlet.http.HttpSession;
import kr.co.orangenode.entity.user.User;
import kr.co.orangenode.service.user.EmailCheckService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    // userId찾기
    @GetMapping("/member/findUserId")
    public ResponseEntity<?> findUserId(@RequestParam String name, @RequestParam String email, HttpSession session) {
        Optional<User> user = emailCheckService.findUserIdByUserNameAndUserEmail(name, email, session);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body("유저가 없습니다.");
        }
    }

    // userPass수정
    @GetMapping("/member/changePass")
    public ResponseEntity<?> changePass(@RequestParam String uid, @RequestParam String pass, @RequestParam String email, HttpSession session) {
        long result = emailCheckService.updatePw(uid, pass, email, session);
        if (result > 0) {
            return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
        } else {
            return ResponseEntity.status(500).body("비밀번호 변경에 실패했습니다.");
        }
    }

    // 이메일 코드 발송
    @PostMapping("/member/sendEmailCode")
    public ResponseEntity<?> checkEmail(@RequestBody Map<String, String> request, HttpSession session) {
        String email = request.get("email");

        int result = emailCheckService.findIdCheckEmail(session, email);
        log.info("session :" + session);

        String code = (String) session.getAttribute("code");
        log.info("session code : " + code);

        if (result == 0) {
            return ResponseEntity.ok("이메일 코드 전송 완료");
        } else {
            return ResponseEntity.status(400).body("에러");
        }
    }
}
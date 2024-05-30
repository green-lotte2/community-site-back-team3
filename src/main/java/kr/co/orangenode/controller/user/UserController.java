package kr.co.orangenode.controller.user;

import kr.co.orangenode.dto.user.UserDTO;
import kr.co.orangenode.entity.user.User;
import kr.co.orangenode.security.MyUserDetails;
import kr.co.orangenode.service.user.UserService;
import kr.co.orangenode.util.JWTProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Log4j2
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;

    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody UserDTO userDTO){

        log.info("login...1 : " + userDTO);

        try {
            // Security 인증 처리
            UsernamePasswordAuthenticationToken authToken
                    = new UsernamePasswordAuthenticationToken(userDTO.getUid(), userDTO.getPass());
            // 사용자 DB 조회
            Authentication authentication = authenticationManager.authenticate(authToken);

            // 인증된 사용자 가져오기
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
            User user = userDetails.getUser();

            // 토큰 발급(액세스, 리프레쉬)
            String access  = jwtProvider.createToken(user, 1); // 1일
            String refresh = jwtProvider.createToken(user, 7); // 7일

            // 리프레쉬 토큰 DB 저장

            // 액세스 토큰 클라이언트 전송
            Map<String, Object> map = new HashMap<>();
            map.put("grantType", "Bearer");
            map.put("uid", user.getUid());
            map.put("name", user.getName());
            map.put("email", user.getEmail());
            map.put("hp", user.getHp());
            map.put("role", user.getRole());
            map.put("grade", user.getGrade());
            map.put("nick", user.getNick());
            map.put("profile", user.getProfile());
            map.put("rdate", user.getRdate());
            map.put("company", user.getCompany());
            map.put("department", user.getDepartment());
            map.put("position", user.getPosition());
            map.put("accessToken", access);
            map.put("refreshToken", refresh);


            return ResponseEntity.ok().body(map);

        }catch (Exception e){
            log.info("login...3 : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }
    }
    @PostMapping("/user")
    public Map<String, String> register(@RequestBody UserDTO userDTO){
        String uid = userService.register(userDTO);
        return Map.of("userid", uid);
    }
    // 회사별로 유저 조회 //
    @GetMapping("/user/company")
    public ResponseEntity<?> selectUserByCompany(@RequestParam String company) {
        log.info("company111  :" + company);
        return userService.selectUserByCompany(company);
    }
}

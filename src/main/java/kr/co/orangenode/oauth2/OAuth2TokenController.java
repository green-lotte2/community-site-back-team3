package kr.co.orangenode.oauth2;

import jakarta.servlet.http.HttpSession;
import kr.co.orangenode.dto.user.UserDTO;
import kr.co.orangenode.entity.user.User;
import kr.co.orangenode.service.user.UserService;
import kr.co.orangenode.util.JWTProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OAuth2TokenController {

    private final OAuth2AuthorizedClientService authorizedClientService;
    private final KakaoTokenService kakaoTokenService;
    private final UserService userService;
    private final JWTProvider jwtProvider;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    @GetMapping("/oauth/callback/kakao")
    public ResponseEntity<?> kakaoCallback(@RequestParam("code") String code) {
        // 카카오 인증 코드로부터 액세스 토큰을 받아옴
        log.info("code {}", code);

        String kakaoAccessToken = kakaoTokenService.getAccessToken(code);

        Map<String, Object> userMap = kakaoTokenService.getUser(kakaoAccessToken);
        String userEmail = (String) userMap.get("email");
        log.info("Kakao access token: {}", kakaoAccessToken);
        log.info("Kakao access userEmail: {}", userEmail);

        // 데이터베이스에서 사용자 정보 조회 또는 등록
        User user = userService.findByUid(userEmail);
        if (user == null) {
            user = User.builder()
                    .uid(userEmail)
                    .name(userEmail)
                    //.pass("kakao")
                    .grade("FREE")
                    .email(userEmail)
                    .role("USER")
                    // 추가 필드 설정
                    .build();
            user = userService.register(modelMapper.map(user, UserDTO.class));
        }
//        // DB 전송
//        User savedUser = userService.register(modelMapper.map(user, UserDTO.class));

        // 세션에 사용자 정보 저장 (예시로 HttpSession을 사용)
        httpSession.setAttribute("user", user);

        String accessToken = jwtProvider.createToken(user, 1);

        log.info("accessToken : {}", accessToken);
        // 받아온 액세스 토큰을 사용하여 원하는 동작을 수행하거나 다른 서비스로 전달
        // 여기에서는 단순히 액세스 토큰을 반환함

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
        map.put("accessToken", accessToken);

        return ResponseEntity.ok().body(map);

    }
}

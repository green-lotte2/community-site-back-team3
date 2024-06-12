package kr.co.orangenode.oauth2;

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

    @GetMapping("/oauth/callback/kakao")
    public ResponseEntity<?> kakaoCallback(@RequestParam("code") String code) {
        // 카카오 인증 코드로부터 액세스 토큰을 받아옴
        log.info("code {}", code);

        String kakaoAccessToken = kakaoTokenService.getAccessToken(code);

        Map<String, Object> userMap = kakaoTokenService.getUser(kakaoAccessToken);
        String userEmail = (String) userMap.get("email");
        log.info("Kakao access token: {}", kakaoAccessToken);
        log.info("Kakao access userEmail: {}", userEmail);

        User user = User.builder()
                        .uid(userEmail)
                        .email(userEmail)
                .role("USER")
                .build();

        // DB 전송
        User savedUser = userService.register(modelMapper.map(user, UserDTO.class));



        String accessToken = jwtProvider.createToken(user, 1);

        log.info("accessToken : {}", accessToken);
        // 받아온 액세스 토큰을 사용하여 원하는 동작을 수행하거나 다른 서비스로 전달
        // 여기에서는 단순히 액세스 토큰을 반환함

        Map<String, Object> map = new HashMap<>();
        map.put("grantType", "Bearer");
        map.put("uid", savedUser.getUid());
        map.put("name", "카카오 유저");
        map.put("email", savedUser.getEmail());
        map.put("hp", savedUser.getHp());
        map.put("role", savedUser.getRole());
        map.put("grade", savedUser.getGrade());
        map.put("nick", savedUser.getNick());
        map.put("profile", savedUser.getProfile());
        map.put("rdate", savedUser.getRdate());
        map.put("company", savedUser.getCompany());
        map.put("department", savedUser.getDepartment());
        map.put("position", savedUser.getPosition());
        map.put("accessToken", accessToken);

        return ResponseEntity.ok().body(map);

    }
}

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

        log.info("kakaoCallback 컨트롤러 : " +code);
        // 카카오에 인가코드를 전달하고 토큰을받는 코드
        String kakaoAccessToken = kakaoTokenService.getAccessToken(code);

        log.info("kakaoCallback 컨트롤러 2 : " +kakaoAccessToken);
        // 카카오 토큰을 통해 유저 정보 조회
        Map<String, Object> userMap = kakaoTokenService.getUser(kakaoAccessToken);

        log.info("userMap체크 {}", userMap);
        String userEmail = (String) userMap.get("email");
        String nick = (String) ((Map<String, Object>) userMap.get("profile")).get("nickname");
        log.info("usernick체크 {}", nick);
        log.info("Kakao access userEmail: {}", userEmail);

        // DB에서 사용자를 조회하고 사용자가 없다면 새로운 사용자를 등록
        User user = userService.findByUid(userEmail);
        if (user == null) {
            user = User.builder()
                    .uid(userEmail)
                    .name(nick)
                    .grade("FREE")
                    .email(userEmail)
                    .role("USER")
                    // 추가 필드 설정
                    .build();
            user = userService.register(modelMapper.map(user, UserDTO.class));
        }

        // 세션에 사용자 정보 저장 (예시로 HttpSession을 사용)
        //httpSession.setAttribute("user", user);

        // jwt토큰 생성
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
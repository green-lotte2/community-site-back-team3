package kr.co.orangenode.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuth2TokenController {

    private final OAuth2AuthorizedClientService authorizedClientService;
    private final KakaoTokenService kakaoTokenService;

    /*
    @GetMapping("/token")
    public String getToken(OAuth2AuthenticationToken authenticationToken) {
        OAuth2AccessToken accessToken = authorizedClientService.loadAuthorizedClient(
                authenticationToken.getAuthorizedClientRegistrationId(),
                authenticationToken.getName()
        ).getAccessToken();
        return accessToken.getTokenValue();
    }*/

    @GetMapping("/login/kakao/callback")
    public String kakaoCallback(@RequestParam("code") String code) {
        // 카카오 인증 코드로부터 액세스 토큰을 받아옴
        String accessToken = kakaoTokenService.getAccessToken(code);

        // 받아온 액세스 토큰을 사용하여 원하는 동작을 수행하거나 다른 서비스로 전달
        // 여기에서는 단순히 액세스 토큰을 반환함
        return accessToken;
    }
}

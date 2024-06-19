package kr.co.orangenode.oauth2;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@Log4j2
@Service
public class KakaoTokenService {
    private static final String TOKEN_ENDPOINT = "https://kauth.kakao.com/oauth/token";
    private static final String CLIENT_ID = "8412b8200aef151b8d5e19641b967e1b";
    private static final String CLIENT_SECRET = "MLJBEAg6jItFD1ykjdZ7NhhFMwl62HsQ";
    //private static final String REDIRECT_URI = "http://127.0.0.1:3000/oauth/callback/kakao";
    private static final String REDIRECT_URI = "http://ec2-3-34-204-24.ap-northeast-2.compute.amazonaws.com:3000/oauth/callback/kakao";
    private static final String GRANT_TYPE = "authorization_code";

    public String getAccessToken(String authorizationCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", GRANT_TYPE);
        requestBody.add("client_id", CLIENT_ID);
        requestBody.add("client_secret", CLIENT_SECRET);
        requestBody.add("redirect_uri", REDIRECT_URI);
        requestBody.add("code", authorizationCode);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.postForEntity(TOKEN_ENDPOINT, request, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> responseBody = response.getBody();
            log.info("response1111111 :" +  response.getBody());

            log.info(responseBody);

            if (responseBody != null && responseBody.containsKey("access_token")) {
                return (String) responseBody.get("access_token");
            }
        }

        return null;
    }

    public Map<String, Object> getUser(String kakaoAccessToken) {

        log.info("getUser...1 : " + kakaoAccessToken);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer "+kakaoAccessToken);

        log.info("getUser...2");
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", GRANT_TYPE);

        log.info("getUser...3");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

        log.info("getUser...4");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.postForEntity("https://kapi.kakao.com/v2/user/me", request, Map.class);

        log.info("getUser...5");

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> responseBody = response.getBody();
            log.info("222222:" + response.getBody());
            log.info(responseBody);

            if (responseBody != null && responseBody.containsKey("kakao_account")) {
                return (Map<String, Object>) responseBody.get("kakao_account");
            }
        }

        return null;
    }
}

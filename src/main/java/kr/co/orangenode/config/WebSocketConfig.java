package kr.co.orangenode.config;

import kr.co.orangenode.handler.WebsocketHandler;
import kr.co.orangenode.handler.WebsocketHandler2;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketConfigurer, WebSocketMessageBrokerConfigurer {

    private final WebsocketHandler2 crdtWebSocketHandler;

    @Value("${front.url}")
    private String frontUrl;

    // 페이지 동시편집 WebSocket 설정
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        log.info("config");
        registry.addHandler(crdtWebSocketHandler, "/ws/crdt")
                .setAllowedOrigins("*") // WebSocket 핸들러 등록 및 CORS 허용 설정
                .addInterceptors(new HttpSessionHandshakeInterceptor());
    }

    // 채팅 WebSocketMessage 설정
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic/chatroom");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat")
                .setAllowedOriginPatterns(frontUrl, "http://3.34.204.24")
                .withSockJS();
    }
}

package kr.co.orangenode.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class CRDTWebSocketConfig implements WebSocketConfigurer {
    private final WebSocketHandler crdtWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(crdtWebSocketHandler, "/ws/crdt").setAllowedOrigins("*"); // WebSocket 핸들러 등록 및 CORS 허용 설정 (front에서 new webSocket에 넣어줄 path)
    }
}

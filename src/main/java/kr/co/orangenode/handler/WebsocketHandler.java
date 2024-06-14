package kr.co.orangenode.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebsocketHandler extends TextWebSocketHandler {
    private final Map<String, Queue<String>> messageQueueMap = new HashMap<>(); // 메시지 큐를 담아둘 맵

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // 메시지 발송
        String msg = message.getPayload();
        String sessionId = session.getId();

        Queue<String> messageQueue = messageQueueMap.computeIfAbsent(sessionId, k -> new LinkedList<>());
        messageQueue.add(msg);

        // 메시지 전송 메서드를 호출하여 큐에 있는 메시지를 전송합니다.
        sendNextMessage(session);
    }

    private void sendNextMessage(WebSocketSession session) {
        String sessionId = session.getId();
        Queue<String> messageQueue = messageQueueMap.get(sessionId);

        if (messageQueue != null && !messageQueue.isEmpty() && session.isOpen()) {
            String message = messageQueue.poll();
            try {
                session.sendMessage(new TextMessage(message));
            } catch (Exception e) {
                log.error("Failed to send message", e);
            }
        }
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 소켓 종료
        log.info("here3333333//");
        String sessionId = session.getId();
        messageQueueMap.remove(sessionId);
        super.afterConnectionClosed(session, status);
    }
}

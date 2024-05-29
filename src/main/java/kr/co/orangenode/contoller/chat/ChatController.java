package kr.co.orangenode.contoller.chat;

import kr.co.orangenode.entity.chat.ChatMessage;
import kr.co.orangenode.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/chatroom/{chatNo}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        chatMessage.setCDate(LocalDateTime.now());
        log.info("Sending message to chat room {}: {}" + chatMessage);
        return chatMessageService.saveMessage(chatMessage);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getUid());
        return chatMessage;
    }

    @GetMapping("/chat/messages")
    public List<ChatMessage> getMessages(@RequestParam int chatNo) {
        return chatMessageService.getMessages(chatNo);
    }
}

package kr.co.orangenode.contoller.chat;

import kr.co.orangenode.entity.chat.ChatMessage;
import kr.co.orangenode.repository.ChatMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
public class ChatController {

    private ChatMessageRepository chatMessageRepository;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        chatMessage.setUid(chatMessage.getUid());
        chatMessage.setCDate(LocalDateTime.now());
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        log.info("하이");
        headerAccessor.getSessionAttributes().put("username", chatMessage.getUid());
        return chatMessage;
    }
    @GetMapping("/chat/messages")
    public List<ChatMessage> getMessages(@RequestParam int chatNo) {
        return chatMessageRepository.findByChatNo(chatNo);
    }
}
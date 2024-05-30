package kr.co.orangenode.controller.chat;

import kr.co.orangenode.dto.chat.ChatMessageDTO;
import kr.co.orangenode.entity.chat.ChatMessage;
import kr.co.orangenode.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage/{chatNo}")
    public void sendMessage(@Payload ChatMessageDTO chatMessageDTO, @DestinationVariable int chatNo) {
        chatMessageDTO.setCDate(LocalDateTime.now());
        ChatMessage savedMessage = chatMessageService.saveMessage(chatMessageDTO);

        // 동적으로 경로를 설정하여 메시지 전송
        messagingTemplate.convertAndSend("/topic/chatroom/" + chatNo, savedMessage);
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessageDTO chatMessageDTO, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessageDTO.getUid());
        messagingTemplate.convertAndSend("/topic/public", chatMessageDTO);
    }

    @GetMapping("/chat/messages")
    public List<ChatMessage> getMessages(@RequestParam int chatNo) {
        return chatMessageService.getMessages(chatNo);
    }
}
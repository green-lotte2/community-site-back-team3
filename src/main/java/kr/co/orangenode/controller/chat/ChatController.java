package kr.co.orangenode.controller.chat;

import kr.co.orangenode.dto.chat.ChatMessageDTO;
import kr.co.orangenode.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage/{chatNo}")
    public void sendMessage(@Payload ChatMessageDTO chatMessageDTO, @DestinationVariable int chatNo) {
        chatMessageDTO.setCDate(LocalDateTime.now());
        ResponseEntity<?> response = chatMessageService.saveMessage(chatMessageDTO);

        messagingTemplate.convertAndSend("/topic/chatroom/" + chatNo, response.getBody());
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessageDTO chatMessageDTO, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessageDTO.getUid());
        messagingTemplate.convertAndSend("/topic/public", chatMessageDTO);
    }

    @GetMapping("/chat/messages")
    public ResponseEntity<?> getMessages(@RequestParam int chatNo) {
        return chatMessageService.getMessages(chatNo);
    }
}

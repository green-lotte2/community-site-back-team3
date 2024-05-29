package kr.co.orangenode.contoller.chat;

import jakarta.transaction.Transactional;
import kr.co.orangenode.entity.chat.ChatMessage;
import kr.co.orangenode.entity.chat.ChatRoom;
import kr.co.orangenode.repository.ChatMessageRepository;
import kr.co.orangenode.repository.ChatRoomRepository;
import kr.co.orangenode.service.ChatMessageService;
import kr.co.orangenode.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;


    @GetMapping("/chatrooms")
    public ResponseEntity<?> getAllChatRooms() {
        log.info("여기?");
        return chatRoomService.getAllChatRooms();
    }

    @PostMapping("/chatroom")
    public ChatRoom createChatRoom(@RequestBody ChatRoom chatRoom) {
        log.info("여기2");
        return chatRoomService.createRoom(chatRoom);
    }

    @DeleteMapping("/chatroom/{cmNo}")
    public ResponseEntity<?> deleteChatRoom(@PathVariable int cmNo) {
        chatRoomService.deleteRoom(cmNo);
        return chatRoomService.getAllChatRooms();
    }

   @GetMapping("/chatroom/{chatno}")
    public List<ChatMessage> getChatRoomMessage(@PathVariable int chatno) {
        return chatMessageService.getMessages(chatno);
   }
}

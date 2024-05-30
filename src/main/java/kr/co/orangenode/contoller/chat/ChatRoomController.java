package kr.co.orangenode.contoller.chat;

import jakarta.transaction.Transactional;
import kr.co.orangenode.entity.chat.ChatMessage;
import kr.co.orangenode.entity.chat.ChatRoom;
import kr.co.orangenode.entity.chat.ChatUser;
import kr.co.orangenode.repository.ChatMessageRepository;
import kr.co.orangenode.repository.ChatRoomRepository;
import kr.co.orangenode.service.ChatMessageService;
import kr.co.orangenode.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;


    @GetMapping("/chatroom")
    public ResponseEntity<?> getAllChatRooms() {
        log.info("여기?");
        return chatRoomService.getAllChatRooms();
    }

    @PostMapping("/chatroom")
    public ChatRoom createChatRoom(@RequestBody ChatRoom chatRoom, @RequestParam String uid) {
        return chatRoomService.createRoom(chatRoom, uid);
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

    @PostMapping("/chatroom/{chatNo}/invite")
    public ResponseEntity<?> inviteFriend(@PathVariable int chatNo, @RequestBody String inviteuid) {
        ChatUser chatUser = ChatUser.builder()
                .uid(inviteuid)
                .chatNo(chatNo)
                .build();
        chatRoomService.inviteFriend(chatNo, inviteuid);
        return ResponseEntity.status(HttpStatus.OK).body("친구초대 완료");
    }

    @GetMapping("/user/{uid}")
    public List<ChatRoom> getUserChatRooms(@PathVariable String uid){
        return chatRoomService.getUserChatRooms(uid);
    }

}

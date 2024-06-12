package kr.co.orangenode.controller.chat;

import kr.co.orangenode.dto.chat.ChatRoomDTO;
import kr.co.orangenode.dto.chat.ChatUserDTO;
import kr.co.orangenode.entity.chat.ChatMessage;
import kr.co.orangenode.entity.chat.ChatRoom;
import kr.co.orangenode.service.ChatMessageService;
import kr.co.orangenode.service.ChatRoomService;
import kr.co.orangenode.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final UserService userService;



    // 채팅방 생성
    @PostMapping("/chatroom/{uid}")
    public ResponseEntity<?> createChatRoom(@RequestBody ChatRoomDTO chatRoomDTO, @PathVariable String uid) {
        try {
            ChatRoom chatRoom = chatRoomService.createRoom(chatRoomDTO, uid);
            return ResponseEntity.ok(chatRoom);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }


    // 채팅방 삭제
    @DeleteMapping("/chatroom")
    public ResponseEntity<?> deleteChatRoom(@RequestParam int chatNo, @RequestParam String uid) {
        chatRoomService.deleteRoom(uid, chatNo);
        return chatRoomService.getAllChatRooms();
    }

    // 이거 안쓰는 거 같아요 chatController에 getMessages()랑 기능 같음
    @GetMapping("/chatroom/{chatno}")
    public ResponseEntity<?> getChatRoomMessage(@PathVariable int chatno) {
        return chatMessageService.getMessages(chatno);
    }

    // 친구 초대
    @PostMapping("/chatroom/invite")
    public ResponseEntity<?> inviteFriend(@RequestBody ChatUserDTO chatUserDTO) {
        chatRoomService.inviteFriend(chatUserDTO);
        log.info("들어오나 : " + chatUserDTO.getChatNo());
        return ResponseEntity.status(HttpStatus.OK).body("친구초대 완료");
    }

    // 채팅방 목록 조회
    @GetMapping("/user/{uid}")
    public List<ChatRoom> getUserChatRooms(@PathVariable String uid) {
        return chatRoomService.getUserChatRooms(uid);
    }
    
    @GetMapping("/friends")
    public ResponseEntity<?> getFriendsByDepartment(@RequestParam String company) {
        log.info("aaa");
        return userService.selectUserByCompany(company);
    }

    @PostMapping("/chatRoom/getRoomNo")
    public ResponseEntity<?> getRoomNo(@RequestBody Map<String, String> request) {
        String title = request.get("title");
        log.info("title" + title);
        ChatRoom chatRoom = chatRoomService.findByTitle(title);
        if (chatRoom == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chat room not found");
        }
        return ResponseEntity.ok(Collections.singletonMap("chatNo", chatRoom.getChatNo()));
    }

}

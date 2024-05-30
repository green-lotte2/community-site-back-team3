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

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final UserService userService;


    @GetMapping("/chatroom")
    public ResponseEntity<?> getAllChatRooms() {
        log.info("여기?");
        return chatRoomService.getAllChatRooms();
    }

    @PostMapping("/chatroom/{uid}")
    public ChatRoom createChatRoom(@RequestBody ChatRoomDTO chatRoomDTO, @PathVariable String uid) {
        return chatRoomService.createRoom(chatRoomDTO, uid);
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

    @PostMapping("/chatroom/invite")
    public ResponseEntity<?> inviteFriend(@RequestBody ChatUserDTO chatUserDTO) {
        log.info("친구 초대 !!! : " + chatUserDTO.toString());
        chatRoomService.inviteFriend(chatUserDTO);
        return ResponseEntity.status(HttpStatus.OK).body("친구초대 완료");
    }

    @GetMapping("/user/{uid}")
    public List<ChatRoom> getUserChatRooms(@PathVariable String uid) {
        return chatRoomService.getUserChatRooms(uid);
    }

    @GetMapping("/friends/{company}")
    public ResponseEntity<?> getFriendsByDepartment(@PathVariable String company) {
        return userService.selectUserByCompany(company);
    }
}
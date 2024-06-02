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


    // 모든 채팅방 조회 : 이거 왜 있는거죠?
    @GetMapping("/chatroom")
    public ResponseEntity<?> getAllChatRooms() {
        log.info("여기?");
        return chatRoomService.getAllChatRooms();
    }

    // 채팅방 생성
    @PostMapping("/chatroom/{uid}")
    public ChatRoom createChatRoom(@RequestBody ChatRoomDTO chatRoomDTO, @PathVariable String uid) {
        return chatRoomService.createRoom(chatRoomDTO, uid);
    }

    // 채팅방 삭제
    @DeleteMapping("/chatroom")
    public ResponseEntity<?> deleteChatRoom(@RequestParam int chatNo, @RequestParam String uid) {
        log.info("이야ㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑ" + chatNo);
        log.info("UID@@@@@@@@@@@@@" + uid);
        chatRoomService.deleteRoom(uid, chatNo);
        return chatRoomService.getAllChatRooms();
    }

    // 이거 안쓰는 거 같아요 chatController에 getMessages()랑 기능 같음
    @GetMapping("/chatroom/{chatno}")
    public List<ChatMessage> getChatRoomMessage(@PathVariable int chatno) {
        return chatMessageService.getMessages(chatno);
    }

    // 친구 초대
    @PostMapping("/chatroom/invite")
    public ResponseEntity<?> inviteFriend(@RequestBody ChatUserDTO chatUserDTO) {
        log.info("친구 초대 !!! : " + chatUserDTO.toString());
        chatRoomService.inviteFriend(chatUserDTO);
        return ResponseEntity.status(HttpStatus.OK).body("친구초대 완료");
    }

    // 채팅방 목록 조회
    @GetMapping("/user/{uid}")
    public List<ChatRoom> getUserChatRooms(@PathVariable String uid) {
        return chatRoomService.getUserChatRooms(uid);
    }

    // 초대할 수 있는(같은 회사) 친구 목록 조회
    @GetMapping("/friends/{company}")
    public ResponseEntity<?> getFriendsByCompany(@PathVariable String company) {
        return userService.selectUserByCompany(company);
    }
}

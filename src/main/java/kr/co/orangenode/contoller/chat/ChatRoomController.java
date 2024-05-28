package kr.co.orangenode.contoller.chat;

import kr.co.orangenode.entity.chat.ChatRoom;
import kr.co.orangenode.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/chatroom")
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomService.getAllChatRooms();
    }

    @PostMapping("/chatroom")
    public ChatRoom createChatRoom(@RequestBody ChatRoom chatRoom) {
        return chatRoomService.createRoom(chatRoom);
    }
}

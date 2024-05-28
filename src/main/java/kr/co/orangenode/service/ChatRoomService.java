package kr.co.orangenode.service;

import kr.co.orangenode.entity.chat.ChatRoom;
import kr.co.orangenode.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom createRoom(ChatRoom chatRoom){
        return chatRoomRepository.save(chatRoom);
    }

    public List<ChatRoom> getAllChatRooms(){
        return chatRoomRepository.findAll();
    }
}

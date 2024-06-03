package kr.co.orangenode.service;

import jakarta.transaction.Transactional;
import kr.co.orangenode.dto.chat.ChatRoomDTO;
import kr.co.orangenode.dto.chat.ChatUserDTO;
import kr.co.orangenode.entity.chat.ChatRoom;
import kr.co.orangenode.entity.chat.ChatUser;
import kr.co.orangenode.repository.ChatRoomRepository;
import kr.co.orangenode.repository.ChatUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ModelMapper modelMapper;
    private final ChatUserRepository chatUserRepository;

    public ChatRoom createRoom(ChatRoomDTO chatRoomDTO, String uid){
        ChatRoom chatRoom = modelMapper.map(chatRoomDTO, ChatRoom.class);
        chatRoom = chatRoomRepository.save(chatRoom);
        chatUserRepository.save(new ChatUser(0, uid, chatRoom.getChatNo()));
        return chatRoom;
    }

    // 모든 채팅방 조회
    public ResponseEntity<?> getAllChatRooms(){
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        log.info("chatRooms : " + chatRooms);

        List<ChatRoomDTO> chatRoomDTOs = chatRooms.stream()
                                                    .map(chatRoom -> modelMapper.map(chatRoom, ChatRoomDTO.class))
                                                    .toList();
        return ResponseEntity.status(HttpStatus.OK).body(chatRoomDTOs);
    }

    // 채팅방 삭제
    @Transactional
    public void deleteRoom(String uid, int chatNo){
        chatUserRepository.deleteChatUserByChatNoAndUid(chatNo, uid);
    }

    // 친구 초대
    public void inviteFriend(ChatUserDTO chatUserDTO) {
        ChatUser chatUser = ChatUser.builder()
                .uid(chatUserDTO.getUid())
                .chatNo(chatUserDTO.getChatNo())
                .build();
        chatUserRepository.save(chatUser);
    }

    public List<ChatRoom> getUserChatRooms(String uid){
        return chatRoomRepository.findChatRoomsByUid(uid);
    }

    public ChatRoom findByTitle(String title) {
        return chatRoomRepository.findByTitle(title);
    }
}
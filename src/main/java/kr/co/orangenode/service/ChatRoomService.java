package kr.co.orangenode.service;

import kr.co.orangenode.dto.chat.ChatRoomDTO;
import kr.co.orangenode.entity.chat.ChatRoom;
import kr.co.orangenode.repository.ChatRoomRepository;
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

    public ChatRoom createRoom(ChatRoom chatRoom){
        ChatRoom aa = chatRoomRepository.save(chatRoom);
        log.info("aa : " + aa);
        return aa;
    }

    public ResponseEntity<?> getAllChatRooms(){
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();

        log.info("chatRooms : " + chatRooms);

        List<ChatRoomDTO> chatRoomDTOs = chatRooms.stream()
                .map(chatRoom -> modelMapper.map(chatRoom, ChatRoomDTO.class))
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(chatRoomDTOs);
    }

    public void deleteRoom(int cmNo){
        chatRoomRepository.deleteById(cmNo);
    }
}

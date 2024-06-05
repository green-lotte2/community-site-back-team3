package kr.co.orangenode.service;

import com.querydsl.core.Tuple;
import jakarta.transaction.Transactional;
import kr.co.orangenode.dto.chat.ChatMessageDTO;
import kr.co.orangenode.entity.chat.ChatMessage;
import kr.co.orangenode.repository.chat.ChatMessageRepository;
import kr.co.orangenode.repository.chat.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor @Slf4j
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ResponseEntity<?> saveMessage(ChatMessageDTO chatMessageDTO) {
        // chatNo가 유효한지 확인
        if (chatRoomRepository.existsById(chatMessageDTO.getChatNo())) {

            ChatMessage chatMessage = modelMapper.map(chatMessageDTO, ChatMessage.class);

            List<Tuple> tuples = chatMessageRepository.saveMessageWithRoom(chatMessage.getChatNo());

            List<ChatMessageDTO> result = tuples.stream()
                    .map(tuple -> {

                        String Name = tuple.get(0,String.class);
                        ChatMessage chatMessage1 = tuple.get(1, ChatMessage.class);
                        ChatMessageDTO chatMessageDTO1 = modelMapper.map(chatMessage, ChatMessageDTO.class);
                        chatMessageDTO.setName(Name);

                        return chatMessageDTO;

                    }).collect(Collectors.toList());

            log.info("챗메세지 서비스"+ result);

            return ResponseEntity.ok().body(result);


        } else {
            throw new IllegalArgumentException("Invalid chat room id: " + chatMessageDTO.getChatNo());
        }
    }

    public ResponseEntity<?> getMessages(int chatNo) {
        List<Tuple> tuples = chatMessageRepository.saveMessageWithRoom(chatNo);

        if(tuples.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");

        } else {
            List<ChatMessageDTO> result = tuples.stream()
                    .map(tuple -> {

                        String Name = tuple.get(0,String.class);
                        ChatMessage chatMessage = tuple.get(1, ChatMessage.class);
                        ChatMessageDTO chatMessageDTO = modelMapper.map(chatMessage, ChatMessageDTO.class);
                        chatMessageDTO.setName(Name);

                        return chatMessageDTO;

                    }).collect(Collectors.toList());

            log.info("챗메세지 서비스"+ result);

            return ResponseEntity.ok().body(result);

        }


    }
}

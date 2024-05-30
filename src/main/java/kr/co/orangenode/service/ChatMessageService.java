package kr.co.orangenode.service;

import jakarta.transaction.Transactional;
import kr.co.orangenode.dto.chat.ChatMessageDTO;
import kr.co.orangenode.entity.chat.ChatMessage;
import kr.co.orangenode.repository.ChatMessageRepository;
import kr.co.orangenode.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ChatMessage saveMessage(ChatMessageDTO chatMessageDTO) {
        // chatNo가 유효한지 확인
        if (chatRoomRepository.existsById(chatMessageDTO.getChatNo())) {
            ChatMessage chatMessage = modelMapper.map(chatMessageDTO, ChatMessage.class);
            return chatMessageRepository.saveMessageWithRoom(chatMessage);
        } else {
            throw new IllegalArgumentException("Invalid chat room id: " + chatMessageDTO.getChatNo());
        }
    }

    public List<ChatMessage> getMessages(int chatNo) {
        return chatMessageRepository.findByChatNo(chatNo);
    }
}

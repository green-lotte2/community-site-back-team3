package kr.co.orangenode.service;

import jakarta.transaction.Transactional;
import kr.co.orangenode.entity.chat.ChatMessage;
import kr.co.orangenode.repository.ChatMessageRepository;
import kr.co.orangenode.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public ChatMessage saveMessage(ChatMessage chatMessage) {
        // chatNo가 유효한지 확인
        if (chatRoomRepository.existsById(chatMessage.getChatNo())) {
            return chatMessageRepository.save(chatMessage);
        } else {
            throw new IllegalArgumentException("Invalid chat room id: " + chatMessage.getChatNo());
        }
    }

    public List<ChatMessage> getMessages(int chatNo) {
        return chatMessageRepository.findByChatNo(chatNo);
    }
}

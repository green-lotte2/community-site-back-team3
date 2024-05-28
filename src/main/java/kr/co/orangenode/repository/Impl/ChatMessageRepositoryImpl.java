package kr.co.orangenode.repository.Impl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import kr.co.orangenode.entity.chat.ChatMessage;
import kr.co.orangenode.entity.chat.ChatRoom;
import kr.co.orangenode.repository.Custom.ChatMessageCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@Transactional
@RequiredArgsConstructor
public class ChatMessageRepositoryImpl implements ChatMessageCustomRepository {

    private final EntityManager entityManager;

    @Override
    public ChatMessage saveMessageWithRoom(ChatMessage chatMessage) {
        chatMessage.setUid(chatMessage.getUid());
        chatMessage.setCDate(LocalDateTime.now());

        entityManager.persist(chatMessage);
        return chatMessage;
    }

}
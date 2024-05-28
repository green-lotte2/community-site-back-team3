package kr.co.orangenode.repository;

import kr.co.orangenode.entity.chat.ChatMessage;
import kr.co.orangenode.repository.Custom.ChatMessageCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer>, ChatMessageCustomRepository {
    List<ChatMessage> findByChatNo(int chatNo);
}

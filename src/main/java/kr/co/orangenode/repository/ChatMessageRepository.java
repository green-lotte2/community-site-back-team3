package kr.co.orangenode.repository;

import kr.co.orangenode.entity.chat.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer>, kr.co.orangenode.repository.custom.ChatMessageRepositoryCustom {
    List<ChatMessage> findByChatNo(int chatNo);
}

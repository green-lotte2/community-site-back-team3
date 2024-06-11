package kr.co.orangenode.repository.chat;

import kr.co.orangenode.entity.chat.ChatMessage;
import kr.co.orangenode.repository.custom.chat.ChatMessageRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer>, ChatMessageRepositoryCustom {
    List<ChatMessage> findByChatNo(int chatNo);

    ChatMessage findBysName(String sName);

}
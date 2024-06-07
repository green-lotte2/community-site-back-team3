package kr.co.orangenode.repository.custom.chat;

import com.querydsl.core.Tuple;
import kr.co.orangenode.entity.chat.ChatMessage;

import java.util.List;

public interface ChatMessageRepositoryCustom {
    List<Tuple> saveMessageWithRoom(int chatNo);
    ChatMessage saveMessageWithRoom2(ChatMessage chatMessage);
}

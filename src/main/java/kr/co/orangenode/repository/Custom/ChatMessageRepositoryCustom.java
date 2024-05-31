package kr.co.orangenode.repository.custom;

import kr.co.orangenode.entity.chat.ChatMessage;

public interface ChatMessageRepositoryCustom {
    ChatMessage saveMessageWithRoom(ChatMessage chatMessage);
}

package kr.co.orangenode.repository.custom;

import kr.co.orangenode.entity.chat.ChatMessage;

public interface ChatMessageCustomRepository {
    ChatMessage saveMessageWithRoom(ChatMessage chatMessage);
}

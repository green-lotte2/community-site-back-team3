package kr.co.orangenode.repository.Custom;

import kr.co.orangenode.entity.chat.ChatMessage;

public interface ChatMessageCustomRepository {
    ChatMessage saveMessageWithRoom(ChatMessage chatMessage);
}

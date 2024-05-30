package kr.co.orangenode.repository.custom;

import kr.co.orangenode.entity.chat.ChatRoom;

import java.util.List;

public interface ChatRoomCustomRepository {
    List<ChatRoom> findChatRoomsByUid(String uid);
}

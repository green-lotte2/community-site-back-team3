package kr.co.orangenode.repository.chat;

import kr.co.orangenode.entity.chat.ChatRoom;

import kr.co.orangenode.repository.custom.chat.ChatRoomRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer>, ChatRoomRepositoryCustom {
    ChatRoom findByTitle(String title);

}

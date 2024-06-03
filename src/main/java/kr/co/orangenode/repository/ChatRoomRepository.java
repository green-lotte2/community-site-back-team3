package kr.co.orangenode.repository;

import kr.co.orangenode.entity.chat.ChatRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer>, kr.co.orangenode.repository.custom.ChatRoomRepositoryCustom {
    ChatRoom findByTitle(String title);

}

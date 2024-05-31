package kr.co.orangenode.repository;

import kr.co.orangenode.entity.chat.ChatRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer>, kr.co.orangenode.repository.custom.ChatRoomRepositoryCustom {
    // 기본 CRUD 메서드 및 사용자 정의 메서드 포함

}

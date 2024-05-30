package kr.co.orangenode.repository;

import kr.co.orangenode.entity.chat.ChatRoom;

import kr.co.orangenode.repository.custom.ChatRoomCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer>, ChatRoomCustomRepository {
    // 기본 CRUD 메서드 및 사용자 정의 메서드 포함
}

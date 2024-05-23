package kr.co.orangenode.repository;

import kr.co.orangenode.entity.chat.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, Integer> {
}

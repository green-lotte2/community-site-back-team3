package kr.co.orangenode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessageRepository, Integer> {
}

package kr.co.orangenode.repository;

import kr.co.orangenode.entity.board.Article;
import kr.co.orangenode.entity.board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}

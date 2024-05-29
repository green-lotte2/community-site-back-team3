package kr.co.orangenode.repository;

import kr.co.orangenode.entity.board.Article;
import kr.co.orangenode.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    public List<Article> findAllByOrderByAnoDesc();
}

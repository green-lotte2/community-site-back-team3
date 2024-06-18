package kr.co.orangenode.repository.article;

import kr.co.orangenode.entity.article.Article;
import kr.co.orangenode.repository.custom.article.ArticleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer>, ArticleRepositoryCustom {
    public List<Article> findAllByOrderByAnoDesc();


}
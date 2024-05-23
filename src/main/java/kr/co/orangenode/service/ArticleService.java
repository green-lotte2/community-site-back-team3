package kr.co.orangenode.service;

import kr.co.orangenode.entity.board.Article;
import kr.co.orangenode.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> checkAllArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> checkArticleById(int uid) {
        return articleRepository.findById(uid);
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Optional<Article> updateArticle(int uid, Article articleDetails) {
        return articleRepository.findById(uid).map(article -> {
            article.setTitle(articleDetails.getTitle());
            article.setContent(articleDetails.getContent());
            // 기타 필드 업데이트
            return articleRepository.save(article);
        });
    }

    public void deleteArticle(int uid) {
        articleRepository.deleteById(uid);
    }
}
package kr.co.orangenode.contoller.board;

import kr.co.orangenode.entity.board.Article;
import kr.co.orangenode.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ArticleController {

    private ArticleService articleService;


    // 전체 게시글 조회
    @GetMapping("/api/articles")
    public List<Article> CheckAllArticles(){
        return articleService.checkAllArticles();
    }

    // 특정 게시글 조회
    @GetMapping("/api/articles/{uid}")
    public Article CheckArticleId(@PathVariable int uid) {
        Optional<Article> article = articleService.checkArticleById(uid);
        if (article.isPresent()) {
            return article.get();
        } else {
            log.info("CheckArticleId ERROR!");
            return null;
        }

    }

    // 게시글 생성
    @PostMapping("/api/articles")
    public Article CreateArticle(@RequestBody Article article){
        return articleService.createArticle(article);
    }


    // 게시글 업데이트
    @PutMapping("/api/articles/{uid}")
    public Article updateArticle(@PathVariable int uid, @RequestBody Article article1) {
        Optional<Article> updatedArticle  = articleService.updateArticle(uid, article1);
        if (updatedArticle.isPresent()) {
            return updatedArticle.get();
        } else {
            log.info("UpdateError!!");
            return null;
        }
    }

    @DeleteMapping("/{uid}")
    public void deleteArticle(@PathVariable int uid) {
        articleService.deleteArticle(uid);
    }


}


















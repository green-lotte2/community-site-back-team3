package kr.co.orangenode.controller.board;

import kr.co.orangenode.entity.board.Article;
import kr.co.orangenode.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ArticleController {

    private ArticleService articleService;


    // 전체 게시글 조회
    @GetMapping("/articles")
    public ResponseEntity<?> CheckAllArticles(@RequestParam("cno") int cno){
        log.info("cno : " + cno);
        return  ResponseEntity.ok().body("백엔드랑 연결되어있음!!");
        //articleService.checkAllArticles();
    }

    // 특정 게시글 조회
    @GetMapping("/articles/{uid}")
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
    @PostMapping("/articles")
    public Article CreateArticle(@RequestBody Article article){
        return articleService.createArticle(article);
    }


    // 게시글 업데이트
    @PutMapping("/articles/{uid}")
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


















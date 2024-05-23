package kr.co.orangenode.contoller.board;

import kr.co.orangenode.entity.board.Article;
import kr.co.orangenode.repository.ArticleRepository;
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



    // 전체 게시글 조회
    @GetMapping("/api/articles")
    public List<Article> CheckAllArticles(){
        return articleRepository.findAll(); 
    }

    // 특정 게시글 조회
    @GetMapping("/api/articles/{uid}")
    public Article CheckArticleId(@PathVariable int uid) {
        Optional<Article> article = articleRepository.findById(uid);
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
        return articleRepository.save(article);
    }


    // 게시글 업데이트
    @PutMapping("/api/articles/{uid}")
    public Article updateArticle(@PathVariable int uid, @RequestBody Article article1) {
        Optional<Article> articleOptional = articleRepository.findById(uid);
        if(articleOptional.isPresent()) {
            Article article2 = articleOptional.get();
            article2.setTitle(article1.getTitle());
            article2.setContent(article1.getTitle());

            return articleRepository.save(article2);
        }else{
            log.info("UpdateError!!");
            return null;
        }
    }

    @DeleteMapping("/api/articles/{uid}")
    public void deleteArticle(@PathVariable int uid){
        Optional<Article> articleOptional = articleRepository.findById(uid);
        if(articleOptional.isPresent()){
            articleRepository.delete(articleOptional.get());
        }else{
            log.info("DeleteError!!!");
        }
    }


}


















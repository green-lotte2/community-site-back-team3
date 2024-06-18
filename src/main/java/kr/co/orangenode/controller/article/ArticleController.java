package kr.co.orangenode.controller.article;

import kr.co.orangenode.dto.article.PageRequestDTO;
import kr.co.orangenode.entity.article.Article;
import kr.co.orangenode.entity.user.User;
import kr.co.orangenode.service.ArticleService;
import kr.co.orangenode.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;

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
    @PostMapping("/article")
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

    @GetMapping("/article/list")
    public ResponseEntity<?> selectList(PageRequestDTO pageRequestDTO, Pageable pageable){
        log.info("게시판 카테별 가져오기" + pageRequestDTO.getCateName());
        return articleService.getArticleList(pageRequestDTO, pageable);
    }

    @GetMapping("/article/cate")
    public ResponseEntity<?> selectCate(){
        log.info("야야ㅑ야야");
        return articleService.getCateList();
    }

    @GetMapping("/article/userInfo")
    public ResponseEntity<?> getUserInfo(@RequestParam String uid){
        return articleService.getUserInfo(uid);
    }



}

















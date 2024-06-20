package kr.co.orangenode.controller.article;

import kr.co.orangenode.dto.article.ArticleCateDTO;
import kr.co.orangenode.dto.article.ArticleDTO;
import kr.co.orangenode.dto.article.PageRequestDTO;
import kr.co.orangenode.entity.article.Article;
import kr.co.orangenode.entity.article.ArticleCate;
import kr.co.orangenode.service.ArticleService;
import kr.co.orangenode.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

        return ResponseEntity.ok().body("백엔드랑 연결되어있음!!");
    }

    // 특정 게시글 조회
    @GetMapping("/articles/{ano}")
    public ResponseEntity<ArticleDTO> getArticle(@PathVariable int ano) {
        return articleService.getArticle(ano);
    }

    // 게시글 생성
    @PostMapping("/article")
    public ResponseEntity<?> CreateArticle(@RequestBody ArticleDTO articleDTO) {
        try {
            log.info("Received articleDTO: " + articleDTO); // articleDTO 로그 출력
            log.info("Received cNo: " + articleDTO.getCno()); // cNo 로그 출력

            ArticleCate category = articleService.getCategoryByCno(articleDTO.getCno());
            if (category == null) {
                log.info("Invalid category ID: " + articleDTO.getCno()); // Invalid category ID 로그 출력
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid category ID");
            }

            Article article = Article.builder()
                    .uid(articleDTO.getUid())
                    .cNo(articleDTO.getCno()) // 유효한 cNo 설정
                    .title(articleDTO.getTitle())
                    .content(articleDTO.getContent())
                    .cateName(category.getCateName())
                    .build();

            log.info("Saving article: " + article); // article 로그 출력

            Article createdArticle = articleService.createArticle(article);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdArticle);
        } catch (Exception e) {
            e.printStackTrace(); // 에러 로그 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating article");
        }
    }




    // 게시글 업데이트
    @PutMapping("/articles/{uid}")
    public ResponseEntity<?> updateArticle(@PathVariable int uid, @RequestBody Article article1) {
        Optional<Article> updatedArticle = articleService.updateArticle(uid, article1);
        if (updatedArticle.isPresent()) {
            return ResponseEntity.ok(updatedArticle.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Article not found");
        }
    }

    // 게시글 삭제
    @DeleteMapping("/articles/{uid}")
    public ResponseEntity<?> deleteArticle(@PathVariable int uid) {
        try {
            articleService.deleteArticle(uid);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting article");
        }
    }

    // 카테고리별 게시글 목록 조회
    @PostMapping("/article/list")
    public ResponseEntity<?> selectList(@RequestBody PageRequestDTO pageRequestDTO){
        log.info("게시판 카테별 가져오기" + pageRequestDTO.toString());
        return articleService.getArticleList(pageRequestDTO);
    }

    // 게시글 카테고리 조회
    @GetMapping("/article/cate")
    public ResponseEntity<?> selectCate(){
        return articleService.getCateList();
    }

    // 사용자 정보 조회
    @GetMapping("/article/userInfo")
    public ResponseEntity<?> getUserInfo(@RequestParam String uid){
        return articleService.getUserInfo(uid);
    }

    // 게시글 카테고리 생성
    @PostMapping("/article/cate/add")
    public ResponseEntity<?> createCate(@RequestBody ArticleCateDTO articleCateDTO){
        return articleService.createCate(articleCateDTO);
    }

    // 댓글 추가
    @PostMapping("/articles/{ano}/comments")
    public ResponseEntity<ArticleDTO> addComment(@PathVariable int ano, @RequestBody Map<String, String> request) {
        String content = request.get("content");
        String uid = request.get("uid");
        ArticleDTO comment = articleService.addComment(ano, content, uid);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    // 특정 게시글의 댓글 조회
    @GetMapping("/articles/{ano}/comments")
    public ResponseEntity<List<ArticleDTO>> getComments(@PathVariable int ano) {
        List<ArticleDTO> comments = articleService.getComments(ano);
        return ResponseEntity.ok(comments);
    }
}

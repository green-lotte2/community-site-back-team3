package kr.co.orangenode.service;

import kr.co.orangenode.dto.article.ArticleCateDTO;
import kr.co.orangenode.dto.article.ArticleDTO;
import kr.co.orangenode.dto.article.PageRequestDTO;
import kr.co.orangenode.dto.article.PageResponseDTO;
import kr.co.orangenode.entity.article.Article;
import kr.co.orangenode.entity.article.ArticleCate;
import kr.co.orangenode.repository.article.ArticleCateRepository;
import kr.co.orangenode.repository.article.ArticleRepository;
import kr.co.orangenode.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleCateRepository articleCateRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

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
            return articleRepository.save(article);
        });
    }

    public void deleteArticle(int uid) {
        articleRepository.deleteById(uid);
    }

    public ResponseEntity<?> getCateList(){
        List<ArticleCate> getCateList = articleCateRepository.findAll();
        log.info("222222222222" + getCateList);
        if (getCateList.isEmpty()) {
            log.info("33333333333333");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error!!");
        } else {
            log.info("444444444444");
            return ResponseEntity.ok().body(getCateList);
        }
    }

    public ResponseEntity<?> getArticleList(PageRequestDTO pageRequestDTO){

        Pageable pageable = pageRequestDTO.getPageable("ano");

        Page<Article> getArticleList = articleRepository.getArticleList(pageRequestDTO, pageable);

        log.info("카테별 게시글 서비스...1" + getArticleList);

        List<ArticleDTO> articleDTOS = getArticleList.getContent().stream()
                .map(article -> modelMapper.map(article, ArticleDTO.class))
                .collect(Collectors.toList());

        log.info("카테별 게시글 서비스...3" + articleDTOS);

        int total = (int) getArticleList.getTotalElements();

        PageResponseDTO pageResponseDTO = PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(articleDTOS)
                .total(total)
                .build();

        return ResponseEntity.ok().body(pageResponseDTO);

    }

    public ResponseEntity<?> getUserInfo(String uid) {
        return userService.userInfo(uid);
    }

    public ResponseEntity<?> createCate(ArticleCateDTO articleCateDTO) {
        try {
            ArticleCate articleCate = modelMapper.map(articleCateDTO, ArticleCate.class);
            ArticleCate savedCate = articleCateRepository.save(articleCate);
            return ResponseEntity.ok().body(savedCate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating category");
        }
    }

    public ArticleCate getCategoryByCno(int cno) {
        log.info("Fetching category with cNo: " + cno); // cNo 로그 출력
        Optional<ArticleCate> category = articleCateRepository.findById(cno);
        if (category.isPresent()) {
            log.info("Category found: " + category.get()); // 카테고리 로그 출력
        } else {
            log.info("Category not found with cNo: " + cno); // 카테고리 없음 로그 출력
        }
        return category.orElse(null);
    }


    public ResponseEntity<ArticleDTO> getArticle(int ano) {
        Optional<Article> articleOptional = articleRepository.findById(ano);

        if (articleOptional.isPresent()) {
            Article article = articleOptional.get();

            // 조회수 증가 로직 추가
            article.setHit(article.getHit() + 1);
            articleRepository.save(article); // 변경된 조회수를 저장

            ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);
            return ResponseEntity.ok(articleDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ArticleDTO addComment(int parentAno, String content, String uid) {
        Optional<Article> parentArticle = articleRepository.findById(parentAno);
        if (parentArticle.isPresent()) {
            Article comment = Article.builder()
                    .content(content)
                    .uid(uid)
                    .parent(String.valueOf(parentAno))
                    .build();
            Article savedComment = articleRepository.save(comment);
            return modelMapper.map(savedComment, ArticleDTO.class);
        }
        throw new RuntimeException("Article not found");
    }

    public List<ArticleDTO> getComments(int parentAno) {
        List<Article> comments = articleRepository.findByParent(String.valueOf(parentAno));
        return comments.stream()
                .map(article -> modelMapper.map(article, ArticleDTO.class))
                .collect(Collectors.toList());
    }
}

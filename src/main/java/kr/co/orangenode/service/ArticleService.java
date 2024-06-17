package kr.co.orangenode.service;

import kr.co.orangenode.dto.article.ArticleDTO;
import kr.co.orangenode.dto.article.PageRequestDTO;

import kr.co.orangenode.dto.article.PageResponseDTO;
import kr.co.orangenode.entity.article.Article;
import kr.co.orangenode.entity.article.ArticleCate;
import kr.co.orangenode.repository.article.ArticleCateRepository;
import kr.co.orangenode.repository.article.ArticleRepository;
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

    public ResponseEntity<?> getCateList(){
       List<ArticleCate> getCateList = articleCateRepository.findAll();
       log.info("222222222222" + getCateList);
       if(getCateList.isEmpty()){
           log.info("33333333333333");
           return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error!!");
       }else{
           log.info("444444444444");
           return ResponseEntity.ok().body(getCateList);
       }
    }

    public ResponseEntity<?> getArticleList(PageRequestDTO pageRequestDTO, Pageable pageable){
        Page<Article> getArticleList = articleRepository.getArticleList(pageRequestDTO, pageable);
        if(getArticleList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR!!!!");
        }else{

            List<ArticleDTO> articleDTOS = getArticleList.getContent().stream()
                    .map(article -> modelMapper.map(article, ArticleDTO.class))
                    .toList();
            int total = (int) getArticleList.getTotalElements();

            PageResponseDTO pageResponseDTO = PageResponseDTO.builder()
                                                            .pageRequestDTO(pageRequestDTO)
                                                            .dtoList(articleDTOS)
                                                            .total(total)
                                                            .build();

            return ResponseEntity.ok().body(pageResponseDTO);

        }
    }


}
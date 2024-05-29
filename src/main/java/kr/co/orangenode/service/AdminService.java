package kr.co.orangenode.service;

import kr.co.orangenode.entity.board.Article;
import kr.co.orangenode.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    @Autowired
    private ArticleRepository articleRepository;

    // 관리자에서 글 목록 보기
    public ResponseEntity<?> adminArticleList(){

        if(articleRepository.findAllByOrderByAnoDesc().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        }else {
            return ResponseEntity.ok().body(articleRepository.findAllByOrderByAnoDesc());
        }

    }

    // 관리자에서 글 보기
    public Optional<Article> adminArticleView(int ano){
        return articleRepository.findById(ano);

    }


    // 관리자 글 삭제
    public void adminArticleDel(int ano){
        articleRepository.deleteById(ano);
    }


}

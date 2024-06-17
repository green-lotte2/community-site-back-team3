package kr.co.orangenode.service;

import kr.co.orangenode.entity.article.Article;
import kr.co.orangenode.entity.user.User;
import kr.co.orangenode.repository.article.ArticleRepository;
import kr.co.orangenode.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private final UserRepository userRepository;

    ///////////////////////////////////////
    ////////////////글 관리////////////////
    ///////////////////////////////////////

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

    // 관리자에서 글 수정하기
    public Optional<Article> adminArticleUpd(int ano, Article updatedArticle) {
        return articleRepository.findById(ano).map(article -> {
            article.setTitle(updatedArticle.getTitle());
            article.setContent(updatedArticle.getContent());
            article.setReply(updatedArticle.getReply());
            // 나중에 필요한거 추가하기

            return articleRepository.save(article);
        });
    }


    // 관리자 글 삭제
    public void adminArticleDel(int ano){
        articleRepository.deleteById(ano);
    }

    ////////////////////////////////////////
    ////////////////멤버관리////////////////
    ////////////////////////////////////////

    // 관리자에서 유저 목록 보기

    public List<User> adminUserList(){
        return userRepository.findAll();
    }



    }





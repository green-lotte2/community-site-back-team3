package kr.co.orangenode.service;

import kr.co.orangenode.entity.board.Article;
import kr.co.orangenode.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    @Autowired
    private ArticleRepository articleRepository;

    // 관리자에서 글 목록 보기
    public List<Article> getAdminArticleList(){
        return articleRepository.findAll();
    }


}

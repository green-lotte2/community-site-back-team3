package kr.co.orangenode.repository.custom.article;

import kr.co.orangenode.dto.article.PageRequestDTO;
import kr.co.orangenode.entity.article.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepositoryCustom {

    public Page<Article> getArticleList(PageRequestDTO pageRequestDTO, Pageable pageable);
}

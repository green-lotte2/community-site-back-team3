package kr.co.orangenode.repository.impl.article;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.orangenode.dto.article.PageRequestDTO;
import kr.co.orangenode.entity.article.Article;
import kr.co.orangenode.entity.article.QArticle;
import kr.co.orangenode.entity.calendar.Calendar;
import kr.co.orangenode.repository.custom.article.ArticleRepositoryCustom;
import kr.co.orangenode.repository.custom.calendar.CalendarRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QArticle qArticle = QArticle.article;


    @Override
    public Page<Article> getArticleList (PageRequestDTO pageRequestDTO, Pageable pageable) {

        QueryResults<Article> article = jpaQueryFactory.selectFrom(qArticle)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .where(qArticle.cateName.eq(pageRequestDTO.getCateName()))
                .fetchResults();

        return new PageImpl<>(article.getResults(), pageable, article.getTotal());

    }

}

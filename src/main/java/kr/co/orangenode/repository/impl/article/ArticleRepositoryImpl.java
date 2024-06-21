package kr.co.orangenode.repository.impl.article;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
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
    private final EntityManager entityManager;


    @Override
    public Page<Article> getArticleList(PageRequestDTO pageRequestDTO, Pageable pageable) {
        // 검색이 아닐때
        if(pageRequestDTO.getSearchType() == null && pageRequestDTO.getSearchKeyword() == null) {
            log.info("검색 아닐때");
            QueryResults<Article> article = jpaQueryFactory.selectFrom(qArticle)
                    .orderBy(qArticle.ano.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .where(qArticle.cateName.eq(pageRequestDTO.getCateName()))
                    .fetchResults();
            return new PageImpl<>(article.getResults(), pageable, (int)article.getTotal());
        } else{
            log.info("검색 일때");
            QueryResults<Article> article = jpaQueryFactory.selectFrom(qArticle)
                    .orderBy(qArticle.ano.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .where(qArticle.cateName.eq(pageRequestDTO.getCateName()))
                    .where(qArticle.title.contains(pageRequestDTO.getSearchKeyword())) // contains는 포함만해도 찾음
                    .fetchResults();
            return new PageImpl<>(article.getResults(), pageable, (int)article.getTotal());
        }
    }

    @Override
    public Article updateArticle(int uid, Article articleDetails){
        Article article = entityManager.find(Article.class, uid);
        if(article != null){
            article.setTitle(articleDetails.getTitle());
            article.setContent(articleDetails.getContent());
            entityManager.merge(article);
        }
        return article;
    }

    @Override
    public List<Article> findByParent(String parent) {
        return jpaQueryFactory.selectFrom(qArticle)
                .where(qArticle.parent.eq(parent))
                .fetch();
    }

}

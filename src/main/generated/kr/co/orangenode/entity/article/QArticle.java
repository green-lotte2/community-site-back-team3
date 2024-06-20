package kr.co.orangenode.entity.article;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QArticle is a Querydsl query type for Article
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArticle extends EntityPathBase<Article> {

    private static final long serialVersionUID = -1293978250L;

    public static final QArticle article = new QArticle("article");

    public final NumberPath<Integer> ano = createNumber("ano", Integer.class);

    public final StringPath cateName = createString("cateName");

    public final NumberPath<Integer> cNo = createNumber("cNo", Integer.class);

    public final StringPath content = createString("content");

    public final NumberPath<Integer> file = createNumber("file", Integer.class);

    public final StringPath font = createString("font");

    public final StringPath fontColor = createString("fontColor");

    public final NumberPath<Integer> hit = createNumber("hit", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> newDate = createDateTime("newDate", java.time.LocalDateTime.class);

    public final StringPath parent = createString("parent");

    public final DateTimePath<java.time.LocalDateTime> rdate = createDateTime("rdate", java.time.LocalDateTime.class);

    public final StringPath reply = createString("reply");

    public final StringPath title = createString("title");

    public final StringPath uid = createString("uid");

    public QArticle(String variable) {
        super(Article.class, forVariable(variable));
    }

    public QArticle(Path<? extends Article> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArticle(PathMetadata metadata) {
        super(Article.class, metadata);
    }

}


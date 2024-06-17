package kr.co.orangenode.entity.article;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QArticleCate is a Querydsl query type for ArticleCate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArticleCate extends EntityPathBase<ArticleCate> {

    private static final long serialVersionUID = -1564755483L;

    public static final QArticleCate articleCate = new QArticleCate("articleCate");

    public final StringPath cateName = createString("cateName");

    public final NumberPath<Integer> cNo = createNumber("cNo", Integer.class);

    public final StringPath uid = createString("uid");

    public QArticleCate(String variable) {
        super(ArticleCate.class, forVariable(variable));
    }

    public QArticleCate(Path<? extends ArticleCate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArticleCate(PathMetadata metadata) {
        super(ArticleCate.class, metadata);
    }

}


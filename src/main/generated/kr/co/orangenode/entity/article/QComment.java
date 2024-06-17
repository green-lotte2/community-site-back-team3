package kr.co.orangenode.entity.article;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = 388798175L;

    public static final QComment comment = new QComment("comment");

    public final NumberPath<Integer> ano = createNumber("ano", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> cdate = createDateTime("cdate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> cno = createNumber("cno", Integer.class);

    public final StringPath content = createString("content");

    public final StringPath uid = createString("uid");

    public QComment(String variable) {
        super(Comment.class, forVariable(variable));
    }

    public QComment(Path<? extends Comment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComment(PathMetadata metadata) {
        super(Comment.class, metadata);
    }

}


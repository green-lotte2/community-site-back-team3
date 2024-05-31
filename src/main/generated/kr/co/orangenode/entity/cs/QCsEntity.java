package kr.co.orangenode.entity.cs;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCsEntity is a Querydsl query type for CsEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCsEntity extends EntityPathBase<CsEntity> {

    private static final long serialVersionUID = -1919539735L;

    public static final QCsEntity csEntity = new QCsEntity("csEntity");

    public final StringPath cateName = createString("cateName");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> csNo = createNumber("csNo", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> rdate = createDateTime("rdate", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public final StringPath uid = createString("uid");

    public QCsEntity(String variable) {
        super(CsEntity.class, forVariable(variable));
    }

    public QCsEntity(Path<? extends CsEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCsEntity(PathMetadata metadata) {
        super(CsEntity.class, metadata);
    }

}


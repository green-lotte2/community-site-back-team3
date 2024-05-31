package kr.co.orangenode.entity.cs;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCsCate is a Querydsl query type for CsCate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCsCate extends EntityPathBase<CsCate> {

    private static final long serialVersionUID = 708544213L;

    public static final QCsCate csCate = new QCsCate("csCate");

    public final StringPath cateName = createString("cateName");

    public final NumberPath<Integer> ccNo = createNumber("ccNo", Integer.class);

    public QCsCate(String variable) {
        super(CsCate.class, forVariable(variable));
    }

    public QCsCate(Path<? extends CsCate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCsCate(PathMetadata metadata) {
        super(CsCate.class, metadata);
    }

}


package kr.co.orangenode.entity.page;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPartner is a Querydsl query type for Partner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPartner extends EntityPathBase<Partner> {

    private static final long serialVersionUID = 1451066801L;

    public static final QPartner partner = new QPartner("partner");

    public final NumberPath<Integer> pageNo = createNumber("pageNo", Integer.class);

    public final NumberPath<Integer> partNo = createNumber("partNo", Integer.class);

    public final StringPath uid = createString("uid");

    public QPartner(String variable) {
        super(Partner.class, forVariable(variable));
    }

    public QPartner(Path<? extends Partner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPartner(PathMetadata metadata) {
        super(Partner.class, metadata);
    }

}


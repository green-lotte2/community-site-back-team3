package kr.co.orangenode.entity.page;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPage is a Querydsl query type for Page
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPage extends EntityPathBase<Page> {

    private static final long serialVersionUID = 1633782342L;

    public static final QPage page = new QPage("page");

    public final NumberPath<Integer> pageNo = createNumber("pageNo", Integer.class);

    public final StringPath uid = createString("uid");

    public final DateTimePath<java.time.LocalDateTime> update = createDateTime("update", java.time.LocalDateTime.class);

    public QPage(String variable) {
        super(Page.class, forVariable(variable));
    }

    public QPage(Path<? extends Page> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPage(PathMetadata metadata) {
        super(Page.class, metadata);
    }

}


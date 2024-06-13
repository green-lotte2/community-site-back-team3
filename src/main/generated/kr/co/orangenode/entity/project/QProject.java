package kr.co.orangenode.entity.project;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProject is a Querydsl query type for Project
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProject extends EntityPathBase<Project> {

    private static final long serialVersionUID = -1303667012L;

    public static final QProject project = new QProject("project");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> proNo = createNumber("proNo", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> rdate = createDateTime("rdate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath title = createString("title");

    public final StringPath uid = createString("uid");

    public QProject(String variable) {
        super(Project.class, forVariable(variable));
    }

    public QProject(Path<? extends Project> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProject(PathMetadata metadata) {
        super(Project.class, metadata);
    }

}


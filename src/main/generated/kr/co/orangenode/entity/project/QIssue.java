package kr.co.orangenode.entity.project;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QIssue is a Querydsl query type for Issue
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIssue extends EntityPathBase<Issue> {

    private static final long serialVersionUID = 1873774908L;

    public static final QIssue issue = new QIssue("issue");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> iNo = createNumber("iNo", Integer.class);

    public final NumberPath<Integer> proNo = createNumber("proNo", Integer.class);

    public final StringPath status = createString("status");

    public final StringPath title = createString("title");

    public QIssue(String variable) {
        super(Issue.class, forVariable(variable));
    }

    public QIssue(Path<? extends Issue> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIssue(PathMetadata metadata) {
        super(Issue.class, metadata);
    }

}


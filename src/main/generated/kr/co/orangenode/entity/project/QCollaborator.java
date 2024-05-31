package kr.co.orangenode.entity.project;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCollaborator is a Querydsl query type for Collaborator
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCollaborator extends EntityPathBase<Collaborator> {

    private static final long serialVersionUID = 898656835L;

    public static final QCollaborator collaborator = new QCollaborator("collaborator");

    public final NumberPath<Integer> colNo = createNumber("colNo", Integer.class);

    public final NumberPath<Integer> proNo = createNumber("proNo", Integer.class);

    public final StringPath uid = createString("uid");

    public QCollaborator(String variable) {
        super(Collaborator.class, forVariable(variable));
    }

    public QCollaborator(Path<? extends Collaborator> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCollaborator(PathMetadata metadata) {
        super(Collaborator.class, metadata);
    }

}


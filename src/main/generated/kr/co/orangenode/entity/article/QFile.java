package kr.co.orangenode.entity.article;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFile is a Querydsl query type for File
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFile extends EntityPathBase<File> {

    private static final long serialVersionUID = -857858820L;

    public static final QFile file = new QFile("file");

    public final NumberPath<Integer> ano = createNumber("ano", Integer.class);

    public final NumberPath<Integer> fno = createNumber("fno", Integer.class);

    public final StringPath oName = createString("oName");

    public final StringPath sName = createString("sName");

    public QFile(String variable) {
        super(File.class, forVariable(variable));
    }

    public QFile(Path<? extends File> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFile(PathMetadata metadata) {
        super(File.class, metadata);
    }

}


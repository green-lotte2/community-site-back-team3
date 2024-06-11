package kr.co.orangenode.entity.cs;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QQuestionFile is a Querydsl query type for QuestionFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionFile extends EntityPathBase<QuestionFile> {

    private static final long serialVersionUID = -472779528L;

    public static final QQuestionFile questionFile = new QQuestionFile("questionFile");

    public final NumberPath<Integer> qfno = createNumber("qfno", Integer.class);

    public final NumberPath<Integer> qno = createNumber("qno", Integer.class);

    public final StringPath sname = createString("sname");

    public QQuestionFile(String variable) {
        super(QuestionFile.class, forVariable(variable));
    }

    public QQuestionFile(Path<? extends QuestionFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQuestionFile(PathMetadata metadata) {
        super(QuestionFile.class, metadata);
    }

}


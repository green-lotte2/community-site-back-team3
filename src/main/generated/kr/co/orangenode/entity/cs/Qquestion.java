package kr.co.orangenode.entity.cs;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * Qquestion is a Querydsl query type for question
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class Qquestion extends EntityPathBase<question> {

    private static final long serialVersionUID = -658885316L;

    public static final Qquestion question = new Qquestion("question");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> qno = createNumber("qno", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> rdate = createDateTime("rdate", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public Qquestion(String variable) {
        super(question.class, forVariable(variable));
    }

    public Qquestion(Path<? extends question> path) {
        super(path.getType(), path.getMetadata());
    }

    public Qquestion(PathMetadata metadata) {
        super(question.class, metadata);
    }

}


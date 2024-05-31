package kr.co.orangenode.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1089597382L;

    public static final QUser user = new QUser("user");

    public final StringPath company = createString("company");

    public final StringPath department = createString("department");

    public final StringPath email = createString("email");

    public final StringPath grade = createString("grade");

    public final StringPath hp = createString("hp");

    public final StringPath name = createString("name");

    public final StringPath nick = createString("nick");

    public final StringPath pass = createString("pass");

    public final StringPath position = createString("position");

    public final StringPath profile = createString("profile");

    public final DateTimePath<java.time.LocalDateTime> rdate = createDateTime("rdate", java.time.LocalDateTime.class);

    public final StringPath role = createString("role");

    public final StringPath uid = createString("uid");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}


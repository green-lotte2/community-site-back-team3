package kr.co.orangenode.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1366736350L;

    public static final QMember member = new QMember("member1");

    public final StringPath addr1 = createString("addr1");

    public final StringPath addr2 = createString("addr2");

    public final StringPath bizRegNum = createString("bizRegNum");

    public final StringPath ceo = createString("ceo");

    public final StringPath company = createString("company");

    public final StringPath comRegNum = createString("comRegNum");

    public final StringPath email = createString("email");

    public final StringPath fax = createString("fax");

    public final StringPath gender = createString("gender");

    public final StringPath hp = createString("hp");

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final StringPath location = createString("location");

    public final StringPath manager = createString("manager");

    public final StringPath managerHp = createString("managerHp");

    public final StringPath name = createString("name");

    public final StringPath nick = createString("nick");

    public final StringPath pass = createString("pass");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final StringPath provider = createString("provider");

    public final DateTimePath<java.time.LocalDateTime> rdate = createDateTime("rdate", java.time.LocalDateTime.class);

    public final StringPath regip = createString("regip");

    public final StringPath tel = createString("tel");

    public final StringPath uid = createString("uid");

    public final DateTimePath<java.time.LocalDateTime> wdate = createDateTime("wdate", java.time.LocalDateTime.class);

    public final StringPath zip = createString("zip");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}


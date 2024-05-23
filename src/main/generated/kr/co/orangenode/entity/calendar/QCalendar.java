package kr.co.orangenode.entity.calendar;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCalendar is a Querydsl query type for Calendar
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCalendar extends EntityPathBase<Calendar> {

    private static final long serialVersionUID = 1608949670L;

    public static final QCalendar calendar = new QCalendar("calendar");

    public final NumberPath<Integer> calNo = createNumber("calNo", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> date = createDateTime("date", java.time.LocalDateTime.class);

    public final TimePath<java.sql.Time> edate = createTime("edate", java.sql.Time.class);

    public final StringPath event = createString("event");

    public final TimePath<java.sql.Time> sdate = createTime("sdate", java.sql.Time.class);

    public final StringPath uid = createString("uid");

    public QCalendar(String variable) {
        super(Calendar.class, forVariable(variable));
    }

    public QCalendar(Path<? extends Calendar> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCalendar(PathMetadata metadata) {
        super(Calendar.class, metadata);
    }

}


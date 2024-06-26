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

    public final StringPath backgroundColor = createString("backgroundColor");

    public final StringPath calendarId = createString("calendarId");

    public final NumberPath<Integer> calNo = createNumber("calNo", Integer.class);

    public final StringPath color = createString("color");

    public final DateTimePath<java.time.LocalDateTime> end = createDateTime("end", java.time.LocalDateTime.class);

    public final StringPath id = createString("id");

    public final StringPath isAllDay = createString("isAllDay");

    public final StringPath isReadOnly = createString("isReadOnly");

    public final StringPath location = createString("location");

    public final DateTimePath<java.time.LocalDateTime> start = createDateTime("start", java.time.LocalDateTime.class);

    public final StringPath state = createString("state");

    public final StringPath title = createString("title");

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


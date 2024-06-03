package kr.co.orangenode.repository.impl.calendar;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.orangenode.entity.calendar.Calendar;
import kr.co.orangenode.entity.calendar.QCalendar;
import kr.co.orangenode.repository.custom.calendar.CalendarRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CalendarRepositoryImpl implements CalendarRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QCalendar qCalendar = QCalendar.calendar;

    @Override
    public List<Calendar> getCalendars(String uid) {

        return jpaQueryFactory.selectFrom(qCalendar)
                .where(qCalendar.uid.eq(uid))
                .fetch();
    }
}

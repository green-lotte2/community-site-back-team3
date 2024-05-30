package kr.co.orangenode.repository.Custom;

import kr.co.orangenode.entity.calendar.Calendar;

import java.util.List;

public interface CalendarRepositoryCustom {

    public List<Calendar> getCalendars(String uid);
}

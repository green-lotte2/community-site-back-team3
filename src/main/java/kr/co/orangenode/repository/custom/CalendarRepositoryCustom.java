package kr.co.orangenode.repository.custom;

import kr.co.orangenode.entity.calendar.Calendar;

import java.util.List;

public interface CalendarRepositoryCustom {

    public List<Calendar> getCalendars(String uid);
}

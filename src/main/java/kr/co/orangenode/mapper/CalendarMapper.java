package kr.co.orangenode.mapper;

import kr.co.orangenode.dto.calendar.CalendarDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CalendarMapper {
    public void updateEvent(String id, CalendarDTO calendarDTO);
}

package kr.co.orangenode.mapper;

import kr.co.orangenode.dto.calendar.CalendarDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CalendarMapper {
    public void updateEvent(@Param("id") String id, @Param("calendarDTO")CalendarDTO calendarDTO);
}

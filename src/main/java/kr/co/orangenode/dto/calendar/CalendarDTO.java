package kr.co.orangenode.dto.calendar;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CalendarDTO {
    private int calNo;
    private String uid;
    private int calendarId;
    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private String location;
    private String state;
    private String color;
    private String isAllDay;
    private String isReadOnly;


}

package kr.co.orangenode.dto.calendar;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CalendarDTO {
    private int calNo;
    private String uid;
    private String event;
    @CreationTimestamp
    private LocalDateTime date;
    private Time sdate;
    private Time edate;

}

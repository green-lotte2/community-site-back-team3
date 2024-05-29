package kr.co.orangenode.entity.calendar;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
@Table(name = "`calendar`")

public class Calendar {
    @Id
    private int calNo;
    private String uid;
    private String calendarId;
    private String id;
    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private String location;
    private String state;
    private String backgroundColor;
    private String color;
    private String isAllDay;
    private String isReadOnly;

}

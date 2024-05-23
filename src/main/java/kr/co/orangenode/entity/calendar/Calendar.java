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
@Table(name = "calendar")

public class Calendar {
    @Id
    private int calNo;
    private String uid;
    private String event;
    private LocalDateTime date;
    private Time sdate;
    private Time edate;

}

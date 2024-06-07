package kr.co.orangenode.dto.cs;

import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class questionDTO {
    private int qno;
    private String title;
    private String content;

    @CreationTimestamp
    LocalDateTime rdate;
}

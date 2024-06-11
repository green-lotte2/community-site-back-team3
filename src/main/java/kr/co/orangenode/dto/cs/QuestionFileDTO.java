package kr.co.orangenode.dto.cs;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class QuestionFileDTO {
    private int qfno;
    private int qno;
    private String sname;

}

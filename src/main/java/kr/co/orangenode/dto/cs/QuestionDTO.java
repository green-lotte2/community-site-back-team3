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
public class QuestionDTO {
    private int qno;
    private String uid;
    private String cate;
    private String title;
    private String content;
    private Integer parent;
    private String ip;
    private int status;

    @CreationTimestamp
    LocalDateTime rdate;
}

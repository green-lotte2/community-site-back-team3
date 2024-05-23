package kr.co.orangenode.dto.board;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDTO {

    private int cno;
    private String uid;
    private int ano;
    private String content;
    @CreationTimestamp
    private LocalDateTime cdate;

}

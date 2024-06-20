package kr.co.orangenode.dto.article;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

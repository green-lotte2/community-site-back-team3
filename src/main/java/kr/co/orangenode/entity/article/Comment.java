package kr.co.orangenode.entity.article;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
@Table(name = "comment")
public class Comment {

    @Id
    private int cno;
    private String uid;
    private int ano;
    private String content;
    @CreationTimestamp
    private LocalDateTime cdate;

}

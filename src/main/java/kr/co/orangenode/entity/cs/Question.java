package kr.co.orangenode.entity.cs;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@ToString
@Table(name = "question")
public class Question {

    @Id
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

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
public class question {
    @Id
    private int qno;
    private String title;
    private String content;

    @CreationTimestamp
    LocalDateTime rdate;
}

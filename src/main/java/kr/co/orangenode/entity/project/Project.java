package kr.co.orangenode.entity.project;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int proNo;
    private String uid;
    private String title;
    private String content;
    private int status;

    @CreationTimestamp
    private LocalDateTime rdate;
}

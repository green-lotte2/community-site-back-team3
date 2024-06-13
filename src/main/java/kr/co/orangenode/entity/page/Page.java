package kr.co.orangenode.entity.page;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
@Table(name = "page")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pageNo;
    private String uid;
    private String title;
    private String content;

    @CreationTimestamp
    private LocalDateTime uDate;
}

package kr.co.orangenode.entity.cs;

import jakarta.persistence.*;
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
@Table(name = "cs")
public class CsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int csNo;
    String uid;
    String title;
    String content;
    String cateName;

    @CreationTimestamp
    LocalDateTime rdate;
}

package kr.co.orangenode.entity.cs;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
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
    String cate;
    String rdate;
}

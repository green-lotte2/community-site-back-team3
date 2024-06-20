package kr.co.orangenode.entity.article;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
@Table(name = "articleCate")
public class ArticleCate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cNo;
    private String uid;
    private String cateName;

}

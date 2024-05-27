package kr.co.orangenode.entity.board;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
@Table(name = "articlecate")
public class ArticleCate {
    @Id
    private int cNo;
    private String uid;
    private String cateName;

}

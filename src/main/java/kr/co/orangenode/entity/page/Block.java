package kr.co.orangenode.entity.page;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
@Table(name = "block")
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;

    private int pageNo;
    private String data;
    private String type;
    // 블록의 정렬 방식
    //private String alignment;
    // 블록의 순서
    private int blockOrder;
}

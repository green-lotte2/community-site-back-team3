package kr.co.orangenode.entity.page;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
@Table(name = "partner")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int partNo;
    private int pageNo;
    private String uid;

}

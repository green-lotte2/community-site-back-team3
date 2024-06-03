package kr.co.orangenode.entity.page;

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
@Table(name = "partner")
public class Partner {

    @Id
    private int partNo;
    private int pageNo;
    private String uid;

}

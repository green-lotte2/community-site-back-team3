package kr.co.orangenode.entity.terms;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "terms")
public class Terms {
    @Id
    private String terms;
    private String privacy;
    private String finance;
    private String location;
}

package kr.co.orangenode.entity.terms;

import jakarta.persistence.*;
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
    private String age;
}

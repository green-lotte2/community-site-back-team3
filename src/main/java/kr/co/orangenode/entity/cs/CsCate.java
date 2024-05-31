package kr.co.orangenode.entity.cs;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
@Table(name = "csCate")
public class CsCate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ccNo;
    private String cateName;

}

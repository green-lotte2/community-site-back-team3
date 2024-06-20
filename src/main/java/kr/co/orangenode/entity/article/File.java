package kr.co.orangenode.entity.article;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fno;
    private int ano;
    private String sName;
    private String oName;
}

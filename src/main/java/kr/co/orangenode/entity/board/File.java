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
@Table(name = "file")
public class File {
    @Id
    private int fno;
    private int ano;
    private String sName;
    private String oName;
}

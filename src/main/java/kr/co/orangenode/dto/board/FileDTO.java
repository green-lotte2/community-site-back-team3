package kr.co.orangenode.dto.board;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class fileDTO {
    private int fno;
    private int ano;
    private String sName;
    private String oName;
}

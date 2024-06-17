package kr.co.orangenode.dto.article;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileDTO {
    private int fno;
    private int ano;
    private String sName;
    private String oName;
}

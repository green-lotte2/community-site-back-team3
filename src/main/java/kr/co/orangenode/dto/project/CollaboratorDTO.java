package kr.co.orangenode.dto.project;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollaboratorDTO {

    private int colNo;

    private String uid;
    private int proNo;
}

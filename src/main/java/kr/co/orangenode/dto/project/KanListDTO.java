package kr.co.orangenode.dto.project;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KanListDTO {

    private int proNo;
    private int iNo;
    private String issueTitle;
    private String uid;
    private String workerName;
    private String workerProfile;
}

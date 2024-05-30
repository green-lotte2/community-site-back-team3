package kr.co.orangenode.dto.project;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueDTO {

    private int iNo;

    private int proNo;
    private String title;
    private String content;
    private String status;
}

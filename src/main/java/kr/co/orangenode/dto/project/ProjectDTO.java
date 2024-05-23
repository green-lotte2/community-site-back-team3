package kr.co.orangenode.dto.project;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTO {
    private int proNo;
    private String uid;
    private String title;
    private int issue;
    private int status;
}

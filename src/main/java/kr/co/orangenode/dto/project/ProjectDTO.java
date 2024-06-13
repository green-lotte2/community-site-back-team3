package kr.co.orangenode.dto.project;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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
    private int status;

    private String[] uids;

    @CreationTimestamp
    private LocalDateTime rdate;
}

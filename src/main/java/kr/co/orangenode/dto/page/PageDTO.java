package kr.co.orangenode.dto.page;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageDTO {
    private int pageNo;
    private String uid;
    private String title;
    private String content;

    private LocalDateTime uDate;
}

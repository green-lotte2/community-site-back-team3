package kr.co.orangenode.dto.page;

import lombok.*;

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

    private LocalDateTime uDate;
}

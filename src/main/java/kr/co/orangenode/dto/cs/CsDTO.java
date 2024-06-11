package kr.co.orangenode.dto.cs;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CsDTO {
    private int csNo;
    private String uid;
    private String title;
    private String content;
    private String cateName;
    private String ip;

    @CreationTimestamp
    LocalDateTime rdate;
}

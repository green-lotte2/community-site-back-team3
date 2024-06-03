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
    int csNo;
    String uid;
    String title;
    String content;
    String cateName;

    @CreationTimestamp
    LocalDateTime rdate;
}

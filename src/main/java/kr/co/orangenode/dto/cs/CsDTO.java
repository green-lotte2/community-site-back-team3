package kr.co.orangenode.dto.cs;

import lombok.*;

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
    String cate;
    String rdate;
}

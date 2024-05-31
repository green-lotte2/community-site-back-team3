package kr.co.orangenode.dto.board;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArticleCateDTO {
    private int cNo;
    private String uid;
    private String cateName;

}

package kr.co.orangenode.dto.article;

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

package kr.co.orangenode.dto.page;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BlockDTO {
    private int bno;
    private int pageNo;
    private String content;
    private String type;
    private String alignment;
    private int order;
}

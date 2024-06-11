package kr.co.orangenode.dto.page;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BlockDTO {
    private String id;
    private int pageNo;
    private String data;
    private String type;
    //private String alignment;
    private int blockOrder;
}

package kr.co.orangenode.dto.page;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PartnerDTO {

    private int partNo;
    private int pageNo;
    private String uid;

    private String name;

}

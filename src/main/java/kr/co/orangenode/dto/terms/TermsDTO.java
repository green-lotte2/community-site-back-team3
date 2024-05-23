package kr.co.orangenode.dto.terms;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TermsDTO {

    private String terms;
    private String privacy;
    private String finance;
    private String location;
}

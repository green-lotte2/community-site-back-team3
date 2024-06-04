package kr.co.orangenode.dto.page;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageFileDTO {

    private int pfno;
    private int pageNo;
    private String sName;
}

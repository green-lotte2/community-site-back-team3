package kr.co.orangenode.entity.page;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
@Table(name = "pagefile")
public class PageFile {

    @Id
    private int pfno;
    private int pageNo;
    private String sName;
}

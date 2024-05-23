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
@Table(name = "page")
public class Page {

    @Id
    private int pageNo;
    private String uid;
    private String title;
    private String content;
    private String font;
    private int fontSize;
    private String fontColor;

}

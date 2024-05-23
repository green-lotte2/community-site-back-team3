package kr.co.orangenode.dto.board;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArticleDTO {

    private int ano;
    private String uid;
    private int cno;
    private String title;
    private String content;
    private int file;
    private String reply;
    private String font;
    private int fontSize;
    private String fontColor;
    private int hit;
    @CreationTimestamp
    private LocalDateTime rdate;
    @CreationTimestamp
    private LocalDateTime newDate;

}

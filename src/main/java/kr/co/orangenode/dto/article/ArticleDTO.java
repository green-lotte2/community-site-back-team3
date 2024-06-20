package kr.co.orangenode.dto.article;

import kr.co.orangenode.entity.article.Article;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    private int ano;
    private String uid;
    private Integer cno;
    private String title;
    private String content;
    private int file;
    private String reply;
    private String font;
    //private int fontSize;
    private String fontColor;
    private int hit;
    @CreationTimestamp
    private LocalDateTime rdate;
    @CreationTimestamp
    private LocalDateTime newDate;
    private String cateName;
    private String parent;


}

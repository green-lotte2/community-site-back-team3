package kr.co.orangenode.entity.article;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@ToString
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ano;
    private String uid;
    private Integer cNo;
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

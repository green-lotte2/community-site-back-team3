package kr.co.orangenode.entity.board;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
@Table(name = "article")
public class Article {

    @Id
    private int ano;
    private String uid;
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

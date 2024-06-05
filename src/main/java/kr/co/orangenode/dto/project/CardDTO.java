package kr.co.orangenode.dto.project;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDTO {
    private int id;
    private String title;
    private String[] tags;
    private String task;

}


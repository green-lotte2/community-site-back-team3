package kr.co.orangenode.dto.chat;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatUserDTO {
    private int cuNo;
    private String uid;
    private int chatNo;
}

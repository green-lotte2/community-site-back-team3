package kr.co.orangenode.dto.chat;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatRoomDTO {
    private int chatNo;
    private String title;
    private String status;
}

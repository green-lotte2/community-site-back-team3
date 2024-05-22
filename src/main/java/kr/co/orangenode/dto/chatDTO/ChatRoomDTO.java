package kr.co.orangenode.dto.chatDTO;

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

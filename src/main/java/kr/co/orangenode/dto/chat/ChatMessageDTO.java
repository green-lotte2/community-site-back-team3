package kr.co.orangenode.dto.chat;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatMessageDTO {

    private int cmNo;
    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime cDate; // JSON 필드 이름과 일치시키기 위해 사용
    private int chatNo;
    private String uid;
    private String oName;
    private String sName;


    // user join용
    private String name;

    private String profile;


    // cDate 필드를 원하는 형식으로 변환
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime getCDate() {
        return cDate;
    }
}

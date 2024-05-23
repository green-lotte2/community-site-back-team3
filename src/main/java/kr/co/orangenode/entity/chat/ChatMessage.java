package kr.co.orangenode.entity.chat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
@Table(name = "chatmessage")
public class ChatMessage {
    @Id
    private int cmNo;
    private String message;
    private LocalDateTime cDate;
    private int chatNo;
    private String uid;
    private String oName;
    private String sName;
}

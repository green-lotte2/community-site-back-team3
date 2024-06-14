package kr.co.orangenode.entity.chat;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @PrePersist
    protected void onCreate() {
        this.cDate = LocalDateTime.now(ZoneOffset.UTC);
    }

    @PreUpdate
    protected void onUpdate() {
        this.cDate = LocalDateTime.now(ZoneOffset.UTC);
    }


}

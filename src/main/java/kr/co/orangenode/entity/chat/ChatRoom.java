package kr.co.orangenode.entity.chat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@ToString
@Table(name = "chatRoom")
public class ChatRoom {
    @Id
    private int chatNo;
    private String title;
    private String status;
}

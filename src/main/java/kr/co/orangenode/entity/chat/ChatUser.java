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
@Table(name = "chatuser")
public class ChatUser {
    @Id
    private int cuNo;
    private int uid;
}

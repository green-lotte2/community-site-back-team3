package kr.co.orangenode.dto.user;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String uid;
    private String pass;
    private String name;
    private String email;
    private String hp;
    private String role;
    private String grade;
    private String nick;
    private String profile;

    @CreationTimestamp
    private LocalDateTime rDate;
}

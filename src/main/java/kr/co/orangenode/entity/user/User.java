package kr.co.orangenode.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    private String uid;
    private String pass;
    private String name;
    private String email;
    private String hp;
    private String role;
    private String grade;
    private String nick;
    private String profile;
    private String company;
    private String department;
    private String position;

    @CreationTimestamp
    private LocalDateTime rdate;
}


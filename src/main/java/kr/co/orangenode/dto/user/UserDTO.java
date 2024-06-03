package kr.co.orangenode.dto.user;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

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
    private String pass2;
    private String name;
    private String email;
    private String hp;
    private String role;
    private String grade;
    private String nick;
    private String profile;
    private String verificationCode; // 이메일 인증코드
    private String company;
    private String department;
    private String position;

    @CreationTimestamp
    private LocalDateTime rdate;

    // 파일 업로드 추가 //
    private MultipartFile file;
}

package kr.co.orangenode.service.user;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.servlet.http.HttpSession;
import kr.co.orangenode.entity.user.User;
import jakarta.mail.internet.MimeMessage;
import kr.co.orangenode.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailCheckService {

    private final UserRepository userRepository;

    // email 전송
    private final JavaMailSender javaMailSender;
    private final HttpSession httpSession;

    public int UserCheck(HttpSession session, String type, String value) {
        int result = 0;
        if (type.equals("email")) {
            Optional<User> optUser = userRepository.findByEmail(value);
            if (optUser.isPresent()) {
                //사용 불가능
                result = 1;
                return result;
            } else {
                // 사용 가능
                sendEmailCode(session, value);
                return result;
            }
        } else if (type.equals("userHp")) {
            //전화번호 중복검사
            Optional<User> optUser = userRepository.findByHp(value);
            //Optional이 비어있는지 체크
            if (optUser.isPresent()) {
                // 사용 불가능
                result = 1;
                return result;
            } else {
                // 사용가능
                return result;
            }
        }if (type.equals("userId")) {
            // 아이디 중복 검사
            Optional<User> optUser = userRepository.findById(value);
            // optional이 비어있는지 체크
            if (optUser.isPresent()) {
                // 사용 불가능
                result = 1;
                return result;
            } else {
                // 사용 가능
                return result;
            }
        }
        return result;
    }

    @Value("${spring.mail.username}")
    private String sender;
    // 🎈이메일 인증코드 전송
    public void sendEmailCode(HttpSession session, String receiver){
        log.info("sender : " + sender);
        log.info("이메일 인증코드 전송 : " + session);

        // MimeMessage 생성
        MimeMessage message = javaMailSender.createMimeMessage();

        // 인증코드 생성 후 세션 저장
        int code = ThreadLocalRandom.current().nextInt(100000, 1000000);
        session.setAttribute("code", String.valueOf(code));

        log.info("code : " + code);

        String title = "orangeNode 인증코드 입니다.";
        String content = "<h1>인증코드는" +  code + "입니다.</h1>";

        try {
            message.setFrom(new InternetAddress(sender, "보내는 사람", "UTF-8"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject(title);
            message.setContent(content, "text/html;charset=UTF-8");

            javaMailSender.send(message);
            log.info("세션확인111 : " + session.getAttribute("code"));
        } catch(Exception e){
            log.error("sendEmailCode : " + e.getMessage());
        }
        log.info("세션확인222 : " + session.getAttribute("code"));
    }

}
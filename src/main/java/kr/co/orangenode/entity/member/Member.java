package kr.co.orangenode.entity.member;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member {
    @Id
    private String uid;
    private String pass;
    private String name;
    private String nick;
    private String gender;
    private String hp;
    private String email;
    private int point;
    @ColumnDefault("1")
    private int level;
    // 위치기반 약관 동의여부 추가(Y/N)
    private String location;
    private String zip;
    private String addr1;
    private String addr2;
    private String company;
    private String ceo;
    private String bizRegNum;
    private String comRegNum;
    private String tel;
    private String manager;
    private String managerHp;
    private String fax;
    private String regip;
    private String provider;
    private LocalDateTime wdate;
    @CreationTimestamp
    private LocalDateTime rdate;


    }


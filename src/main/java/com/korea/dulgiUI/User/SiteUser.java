package com.korea.dulgiUI.User;

import com.korea.dulgiUI.calendar.UserCalendar;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Base64;

@Getter
@Setter
@Entity
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    private String nickname;

    private String userRole;

    private String gender; // 라디오 버튼 값 받기

    private String mobile;

    private String department;

    private String profile_image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "calendar_id")
    private UserCalendar userCalendar;
}

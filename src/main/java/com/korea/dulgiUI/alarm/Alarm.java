package com.korea.dulgiUI.alarm;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.korea.dulgiUI.User.SiteUser;
import com.korea.dulgiUI.chat.ChatRoom;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private String sender;

    private String chatroomName;

    private Boolean accept;

    @ManyToOne
    @JsonBackReference
    private ChatRoom chatRoom;

    @Builder
    public Alarm(String message, String sender, String chatroomName, ChatRoom chatRoom) {
        this.message = message;
        this.sender = sender;
        this.chatroomName = chatroomName;
        this.chatRoom = chatRoom;
        this.accept = false;
    }
}
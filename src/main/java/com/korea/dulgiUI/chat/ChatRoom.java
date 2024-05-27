package com.korea.dulgiUI.chat;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    List<ChatMessage> chatMessageList = new ArrayList<>();
}

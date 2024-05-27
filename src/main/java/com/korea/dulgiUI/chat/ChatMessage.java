package com.korea.dulgiUI.chat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId; // 채팅방 번호

    private String sender; // 메세지 보낸사람

    private String message; // 메시지
    @DateTimeFormat(pattern = "yyyy-MM-dd'-'HH:mm:ss")
    private LocalDateTime createDate;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonBackReference
    private ChatRoom chatRoom;

    @Builder
    public ChatMessage(String sender, String message, ChatRoom chatRoom, LocalDateTime createDate) {
        this.sender = sender;
        this.message = message;
        this.chatRoom = chatRoom;
        this.createDate = createDate;
    }
}

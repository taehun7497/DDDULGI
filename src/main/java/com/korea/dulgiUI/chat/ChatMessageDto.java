package com.korea.dulgiUI.chat;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ChatMessageDto {
    private String message;
    private LocalDateTime createDate;
    private Long ChatroomId;
    private String sender;
}

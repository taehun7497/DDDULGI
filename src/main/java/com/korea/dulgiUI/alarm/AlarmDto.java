package com.korea.dulgiUI.alarm;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlarmDto {
    private String message;

    private Boolean accept;

    private String sendUsername;

    private String chatroomName;

    private Long chatRoomId;
}
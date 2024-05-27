package com.korea.dulgiUI.websocket;

import com.korea.dulgiUI.chat.*;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class WebsocketController {
    private final ChatroomRepository chatroomRepository;
    private final ChatMessageRepository chatMessageRepository;


    @MessageMapping("/talk/{id}")
    @SendTo("/sub/talk/{id}")
    public ChatMessageDto message(ChatMessageDto message, @DestinationVariable("id") Long id) throws Exception {
        LocalDateTime createDate = message.getCreateDate();
        ChatRoom chatRoom = chatroomRepository.findById(id).orElseThrow();
        ChatMessage chatMessage = ChatMessage.builder().message(message.getMessage()).chatRoom(chatRoom).createDate(createDate).sender(message.getSender()).build();
        chatMessageRepository.save(chatMessage);
        return message;
    }
}

package com.korea.dulgiUI.chat;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatroomService {
    private final ChatroomRepository chatroomRepository;

    public ChatRoom create(String name){
        ChatRoom chatroom = new ChatRoom();
        chatroom.setName(name);
        chatroomRepository.save(chatroom);
        return chatroom;
    }

    public List<ChatRoom> findAll() {
        return chatroomRepository.findAll();
    }

    public ChatRoom findById(Long id) {
        return chatroomRepository.findById(id).orElseThrow();
    }
}

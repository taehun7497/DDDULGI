package com.korea.dulgiUI.chat;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatroomService {
    private final ChatroomRepository chatroomRepository;

    public ChatRoom create(String name, String password) {
        ChatRoom chatroom = new ChatRoom();
        chatroom.setName(name);
        chatroom.setPassword(password);
        chatroomRepository.save(chatroom);
        return chatroom;
    }

    public List<ChatRoom> findAll() {
        return chatroomRepository.findAll();
    }

    public ChatRoom findById(Long id) {
        return chatroomRepository.findById(id).orElseThrow();
    }

    // 비밀번호 유효성 검사 메소드
    public boolean isValidPassword(Long roomId, String password) {
        // roomId를 사용하여 채팅방을 조회
        ChatRoom chatroom = chatroomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));

        // 주어진 비밀번호와 채팅방의 비밀번호를 비교하여 일치 여부 반환
        return password.equals(chatroom.getPassword());
    }
}

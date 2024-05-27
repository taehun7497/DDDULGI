package com.korea.dulgiUI.chat;

import com.korea.dulgiUI.User.SiteUser;
import com.korea.dulgiUI.User.UserDetail;
import com.korea.dulgiUI.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatroomController {
    private final ChatroomService chatroomService;
    private final UserService userService;
    private final ChatMessageService chatMessageService;

    @GetMapping("/list")
    public String list(Model model) {

        List<ChatRoom> chatRoomList = chatroomService.findAll();
        model.addAttribute("chatroomList", chatRoomList);
        return "ChatList";
    }

    @GetMapping("/create")
    public String create() {

        return "createChat_form";
    }

    @PostMapping("/create")
    public String create(@RequestParam("name") String name) {
        chatroomService.create(name);

        return "redirect:/chat/list";
    }

    @GetMapping("/talk/{id}")
    public String talk(@PathVariable("id") Long id, Model model, Principal principal) {
        ChatRoom chatRoom = chatroomService.findById(id);
        SiteUser siteUser = userService.getUser(principal.getName());
        model.addAttribute("chatroom", chatRoom);
        model.addAttribute("ownerUser", siteUser);
        return "Chatroom";
    }
}

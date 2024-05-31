package com.korea.dulgiUI.chat;

import com.korea.dulgiUI.User.SiteUser;
import com.korea.dulgiUI.User.UserDetail;
import com.korea.dulgiUI.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatroomController {
    private final ChatroomService chatroomService;
    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        Long userCalendar = null;

        if (userDetails != null) {
            SiteUser user = userService.getUser(userDetails.getUsername());
            userCalendar = user.getUserCalendar().getId();
            String url = user.getProfile_image();
            model.addAttribute("url", url);
        }

        List<ChatRoom> chatRoomList = chatroomService.findAll();
        model.addAttribute("chatroomList", chatRoomList);
        model.addAttribute("userCalendar", userCalendar);
        return "ChatList";
    }

    @GetMapping("/create")
    public String create() {
        return "createChat_form";
    }

    @PostMapping("/create")
    public String create(@RequestParam("name") String name,
                         @RequestParam("password") String password) {
        chatroomService.create(name, password);
        return "redirect:/chat/list";
    }

    @PostMapping("/enter")
    public String enterChatRoom(@RequestParam("roomId") Long roomId,
                                @RequestParam("password") String password,
                                RedirectAttributes redirectAttributes) {
        if (chatroomService.isValidPassword(roomId, password)) {
            return "redirect:/chat/talk/" + roomId;
        } else {
            redirectAttributes.addFlashAttribute("error", "Incorrect password!");
            return "redirect:/chat/list";
        }
    }

    @GetMapping("/talk/{id}")
    public String talk(@PathVariable("id") Long id, Model model, Principal principal) {
        ChatRoom chatRoom = chatroomService.findById(id);
        SiteUser siteUser = userService.getUser(principal.getName());
        model.addAttribute("chatroom", chatRoom);
        model.addAttribute("ownerUser", siteUser);
        return "chatroom";
    }
}

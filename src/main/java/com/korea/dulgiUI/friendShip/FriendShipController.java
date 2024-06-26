package com.korea.dulgiUI.friendShip;

import com.korea.dulgiUI.User.SiteUser;
import com.korea.dulgiUI.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendShipController {
    private final UserService userService;
    private final FriendShipService friendShipService;

    @GetMapping("/add")
    public String friend(Principal principal, @RequestParam("nickname") String nickname, Model model, RedirectAttributes redirectAttributes) {
        SiteUser friend1 = this.userService.getUser(principal.getName());
        SiteUser friend2 = this.userService.getUserNickname(nickname);
        if(friend2 == null){
            model.addAttribute("error", "djlaskd");
            return "user_info";
        }

        friendShipService.add(friend1, friend2);

        return "redirect:/user/info";
    }

    @PostMapping("/accept/{id}")
    public String accept(@PathVariable("id") Long id, Principal principal, @RequestParam("action") String action) {
        if (action.equals("accept")) {
            SiteUser user = userService.getUser(principal.getName());
            FriendShip friendShip = friendShipService.getFriend(id, user.getId());
            friendShip.setAllow(true);
            friendShipService.save(friendShip);
        } else {
            SiteUser user = userService.getUser(principal.getName());
            FriendShip friendShip = friendShipService.getFriend(id, user.getId());
            friendShipService.delete(friendShip);
        }
        return "redirect:/user/info";
    }

}

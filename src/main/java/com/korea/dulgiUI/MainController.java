package com.korea.dulgiUI;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class MainController {
    @GetMapping("/")
    public String root(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {

            return "redirect:/question/list/qna";
        } else {
            return "redirect:/user/login";
        }
    }
}

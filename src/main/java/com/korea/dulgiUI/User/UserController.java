package com.korea.dulgiUI.User;

import com.korea.dulgiUI.Event.Event;
import com.korea.dulgiUI.Event.EventService;
import com.korea.dulgiUI.Message;
import com.korea.dulgiUI.answer.Answer;
import com.korea.dulgiUI.answer.AnswerService;
import com.korea.dulgiUI.email.EmailService;
import com.korea.dulgiUI.friendShip.FriendShip;
import com.korea.dulgiUI.friendShip.FriendShipService;
import com.korea.dulgiUI.question.Question;
import com.korea.dulgiUI.question.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final EventService eventService;
    private final FriendShipService friendShipService;


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/info")
    public String getInfo(Model model,
                          Principal principal) {

        SiteUser user = this.userService.getUser(principal.getName());
        List<Question> questionList = this.questionService.getQuestions(user);
        List<Answer> answerList = this.answerService.getListByAuthor(user);
        List<FriendShip> acceptUserList = friendShipService.findAccept(user.getId());
        List<FriendShip> friendList = friendShipService.findFriendAll(user.getId());

        Long userCalendar = user.getUserCalendar().getId();

        if (!model.containsAttribute("url") || model.getAttribute("url") == null)
            model.addAttribute("url", user.getProfile_image());

        model.addAttribute("answers", answerList);
        model.addAttribute("questions", questionList);
        model.addAttribute("user", user);
        model.addAttribute("acceptUser", acceptUserList);
        model.addAttribute("friendList", friendList);
        model.addAttribute("userCalendar", userCalendar);
        return "user_info";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/password")
    public String modifyPassword(PasswordModifyForm passwordModifyForm) {
        return "modify_password_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/password")
    public String modifyPassword(@Valid PasswordModifyForm passwordModifyForm,
                                 BindingResult bindingResult,
                                 Principal principal,
                                 Model model) {

        SiteUser user = this.userService.getUser(principal.getName());

        if (bindingResult.hasErrors()) {
            return "modify_password_form";
        }

        if (!this.userService.isSamePassword(user, passwordModifyForm.getBeforePassword())) {
            bindingResult.rejectValue("beforePassword", "notBeforePassword", "이전 비밀번호와 일치하지 않습니다. ");
            return "modify_password_form";
        }

        if (!passwordModifyForm.getPassword1().equals(passwordModifyForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
            return "modify_password_form";
        }

        try {
            userService.modifyPassword(user, passwordModifyForm.getPassword1());
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("modifyPasswordFailed", e.getMessage());
            return "modify_password_form";
        }

        model.addAttribute("data", new Message("비밀번호 변경 되었습니다.", "/user/info"));
        return "message";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm,
                         BindingResult bindingResult,
                         Model model) {

        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }
        if (userCreateForm.getAgree() == null) {
            model.addAttribute("error", "이용약관에 동의해주세요");
            return "signup_form";
        }

        try {
            userService.create(
                    userCreateForm.getUsername(),
                    userCreateForm.getNickname(),
                    userCreateForm.getEmail(),
                    userCreateForm.getPassword1(),
                    userCreateForm.getDepartment(),
                    userCreateForm.getGender(),
                    userCreateForm.getMobile()
            );

            return "success";
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }
    }

    @GetMapping("/find-account")
    public String findAccount(Model model) {
        model.addAttribute("sendConfirm", false);
        model.addAttribute("error", false);
        return "find_account";
    }

    @PostMapping("/find-account")
    public String findAccount(@RequestParam(value = "email") String email) {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String newpassword = passwordGenerator.generateRandomPassword();
        SiteUser siteUser = userService.getUserByEmail(email);
        siteUser.setPassword(passwordEncoder.encode(newpassword));
        userRepository.save(siteUser);
        emailService.sendMail(siteUser.getEmail(), newpassword);

        return "redirect:/user/login";
    }

    @GetMapping("/edit")
    public String update(Principal principal,
                         Model model) {
        try {
            SiteUser user = userService.getUser(principal.getName());
            model.addAttribute("user", user);

            return "user_edit";
        } catch (Exception e) {
            return "redirect:/user/info";
        }
    }

    @PostMapping("/edit")
    public String update(Principal principal,
                         @ModelAttribute SiteUser updatedUser) {
        SiteUser user = userService.getUser(principal.getName());
        userService.updateUser(user, updatedUser.getNickname(), updatedUser.getDepartment(), updatedUser.getMobile(), updatedUser.getEmail());
        return "redirect:/user/info";
    }

    @GetMapping("/calendar/{calendarId}")
    public String personalCalendar(Model model,
                                   @PathVariable(name = "calendarId", required = false) String calendarId,
                                   @RequestParam(name = "targetMonth", required = false, defaultValue = "0") int targetMonth,
                                   @AuthenticationPrincipal UserDetails userDetails) {
        if (calendarId == null || calendarId.isEmpty()) {
            // 기본 calendarId를 설정합니다. 예: "1"
            calendarId = "1";
        }

        Long parsedCalendarId;
        try {
            parsedCalendarId = Long.parseLong(calendarId);
        } catch (NumberFormatException e) {
            // 예외 처리
            return "errorPage"; // 오류가 발생하면 적절한 에러 페이지로 리다이렉트합니다.
        }

        List<Event> events = this.eventService.findByCalendarId(parsedCalendarId);

        // targetMonth가 0 이하면 현재 월의 값을 사용하여 이벤트 목록을 가져옴
        if (targetMonth <= 0) {
            LocalDateTime now = LocalDateTime.now();
            targetMonth = now.getMonthValue();
        }

        int prevMonth = targetMonth - 1;
        int nextMonth = targetMonth + 1;

        List<Event> eventsForMonth = this.eventService.getEventsForMonth(events, targetMonth);

        if (userDetails != null) {
            SiteUser user = userService.getUser(userDetails.getUsername());
            String url = user.getProfile_image();
            model.addAttribute("url", url);
        }

        model.addAttribute("calendarId", calendarId);
        model.addAttribute("targetMonth", targetMonth);
        model.addAttribute("prevMonth", prevMonth);
        model.addAttribute("nextMonth", nextMonth);
        model.addAttribute("parsedCalendarId", parsedCalendarId); // 모델에 추가된 올바른 이름
        model.addAttribute("eventsForMonth", eventsForMonth); // 이벤트 목록을 모델에 추가

        return "UserCalendar";
    }

    @PostMapping("/imageform")
    public String imageform(@RequestParam("file") MultipartFile file,
                            RedirectAttributes redirectAttributes) {
        String url = null;
        if (file.getContentType().contains("image"))
            url = userService.temp_save(file);
        redirectAttributes.addFlashAttribute("url", url);
        return "redirect:/user/info";
    }

    @PostMapping("/imagesaveform")
    public String imagesaveform(Principal principal,
                                @RequestParam(value = "url", defaultValue = "") String url) {
        if (url.isBlank())
            return "redirect:/user/info";
        SiteUser siteUser = userService.getUser(principal.getName());
        userService.save(siteUser, url);
        return "redirect:/user/info";
    }


    public static class PasswordGenerator {
        private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        private static final String NUMBER = "0123456789";
        private static final String OTHER_CHAR = "!@#$%&*()_+-=[]?";
        private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
        private static final int PASSWORD_LENGTH = 12;

        public String generateRandomPassword() {
            if (PASSWORD_LENGTH < 1) throw new IllegalArgumentException("Password length must be at least 1");

            StringBuilder sb = new StringBuilder(PASSWORD_LENGTH);
            Random random = new Random();
            int baseLength = PASSWORD_ALLOW_BASE.length();

            for (int i = 0; i < PASSWORD_LENGTH; i++) {
                int rndCharAt = random.nextInt(baseLength);
                char rndChar = PASSWORD_ALLOW_BASE.charAt(rndCharAt);
                sb.append(rndChar);
            }
            return sb.toString();
        }
    }
}

package com.korea.dulgiUI.User;

import com.korea.dulgiUI.calendar.UserCalendar;
import com.korea.dulgiUI.calendar.CalendarService;
import com.korea.dulgiUI.dulgiUIApplication;
import com.korea.dulgiUI.error.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CalendarService calendarService;
    private final ResourceLoader resourceLoader;

    public SiteUser create(String username,
                           String nickname,
                           String email,
                           String password,
                           String department,
                           String gender,
                           String mobile) {

        SiteUser user = new SiteUser();
        //BCrypt 해싱함수를 사용해서 암호화

        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        user.setEmail(email);
        user.setGender(gender);
        user.setNickname(nickname);
        user.setDepartment(department);
        user.setMobile(mobile);

        // SiteUser 엔티티를 먼저 저장합니다.
        SiteUser savedUser = userRepository.save(user);

        // 새 사용자 등록 시 달력 생성 및 기본 이벤트 추가
        UserCalendar userCalendar = calendarService.createCalendar(savedUser);
        savedUser.setUserCalendar(userCalendar);

        this.userRepository.save(user);
        return user;
    }

    public void modifyPassword(SiteUser modifyUser,
                               String password) {

        modifyUser.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(modifyUser);
    }

    public boolean isSamePassword(SiteUser user,
                                  String password) {

        return passwordEncoder.matches(password, user.getPassword());
    }


    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);

        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("siteUser not found");
        }
    }

    public SiteUser getUserByEmail(String email) {
        Optional<SiteUser> siteUser = this.userRepository.findByEmail(email);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("Email not found!!");
        }
    }

    public SiteUser getUserNickname(String nickname) {
        Optional<SiteUser> user = userRepository.findByNickname(nickname);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    public SiteUser update(SiteUser user,
                           String newPassword) {

        user.setPassword(this.passwordEncoder.encode(newPassword));
        this.userRepository.save(user);
        return user;
    }

    public boolean isMatch(String rawPassword,
                           String encodedPassword) {

        return this.passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public void updateUser(SiteUser user, String nickname, String department, String mobile, String email) {
        user.setNickname(nickname);
        user.setDepartment(department);
        user.setMobile(mobile);
        user.setEmail(email);
        userRepository.save(user); // 유저 정보 업데이트 후 저장
    }

    public String temp_save(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // 파일 저장 경로 설정 (C 드라이브의 Temp 폴더에 저장)
                String path = dulgiUIApplication.getOsType().getLoc();
                File fileFolder = new File(path);
                if (!fileFolder.exists()) {
                    fileFolder.mkdirs();
                }

                // 파일 확장자 얻기
                String originalFilename = file.getOriginalFilename();
                String extension = "";
                if (originalFilename != null && originalFilename.contains(".")) {
                    extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                }

                String name = UUID.randomUUID().toString();
                // 파일 저장 경로 설정
                String filePath = path + "/" +name  + extension;
                file.transferTo(Paths.get(filePath));
                // GIF, JPEG, JPG 파일에 대한 처리
                if (extension.equals(".gif") || extension.equals(".jpeg") || extension.equals(".jpg") || extension.equals(".png")) {
                    // 이미지 파일의 경우 서버에서 접근 가능한 URL을 반환
                    String url = "/images/" + name + extension;
                    return url;
                } else {
                    // GIF, JPEG, JPG 파일이 아닌 경우에는 예외 처리 또는 다른 로직을 수행할 수 있습니다.
                    // 현재는 해당 확장자가 아닌 경우에는 null을 반환하도록 설정되어 있습니다.
                    return null;
                }
            } catch (IOException e) {
                // 예외 처리
                e.printStackTrace();
            }
        }
        return null;
    }

    @Transactional
    public void save(SiteUser user, String url) {
        try {
            String path = dulgiUIApplication.getOsType().getLoc(); // 파일이 저장된 경로에 맞게 변경
            if (user.getProfile_image() != null) {
                File oldFile = new File(path + user.getProfile_image());
                if (oldFile.exists()) {
                    oldFile.delete(); // 기존 파일 삭제
                }
            }
            user.setProfile_image(url);
            userRepository.save(user);
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
        }
    }
}

package com.korea.dulgiUI.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser,Long> {
    Optional<SiteUser> findByUsername(String username);
    Optional<SiteUser> findByNickname(String nickname);
    Optional<SiteUser> findByEmail(String email);
}

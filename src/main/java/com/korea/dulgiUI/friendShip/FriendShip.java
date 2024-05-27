package com.korea.dulgiUI.friendShip;

import com.korea.dulgiUI.User.SiteUser;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class FriendShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private SiteUser friend1;
    @ManyToOne(fetch = FetchType.LAZY)
    private SiteUser friend2;
    private boolean allow;

    @Builder
    public FriendShip(SiteUser friend1, SiteUser friend2) {
        this.friend1 = friend1;
        this.friend2 = friend2;
        this.allow = false;
    }
}

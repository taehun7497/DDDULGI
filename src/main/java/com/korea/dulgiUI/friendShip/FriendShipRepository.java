package com.korea.dulgiUI.friendShip;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendShipRepository extends JpaRepository<FriendShip, Long>, FriendShipCustom {
}

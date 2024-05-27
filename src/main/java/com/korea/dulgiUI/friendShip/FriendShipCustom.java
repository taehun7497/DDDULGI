package com.korea.dulgiUI.friendShip;

import java.util.List;
import java.util.Optional;

public interface FriendShipCustom {
    Optional<FriendShip> findByUsers(Long id1, Long id2);
    List<FriendShip> findByOwner(Long id);
    List<FriendShip> findByFriend(Long id);

    List<FriendShip> findByFriendList(Long id);

    FriendShip findByFriend1AndFriend2(Long Id1, Long Id2);
}

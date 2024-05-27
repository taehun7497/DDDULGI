package com.korea.dulgiUI.friendShip;

import com.korea.dulgiUI.User.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendShipService {
    private final FriendShipRepository friendShipRepository;
    public void add(SiteUser owneruser, SiteUser friendSiteUser) {
        Optional<FriendShip> _Friendship = friendShipRepository.findByUsers(owneruser.getId(), friendSiteUser.getId());
        if (_Friendship.isEmpty()) {
            FriendShip friendship = FriendShip.builder().friend1(owneruser).friend2(friendSiteUser).build();
            friendShipRepository.save(friendship);
        } else {
            // 오류 처리
        }
    }

    public List<FriendShip> findAccept(Long id){
        return friendShipRepository.findByFriend(id);
    }

    public FriendShip getFriend(Long id1,Long id2){
        return friendShipRepository.findByFriend1AndFriend2(id1,id2);
    }

    public void delete(FriendShip friendShip) {
        friendShipRepository.delete(friendShip);
    }

    public List<FriendShip> findFriendAll(Long id){
        return friendShipRepository.findByFriendList(id);
    }

    public void save(FriendShip friendShip) {
        friendShipRepository.save(friendShip);
    }
}

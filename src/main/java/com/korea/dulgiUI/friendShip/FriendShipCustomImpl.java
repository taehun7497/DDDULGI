package com.korea.dulgiUI.friendShip;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class FriendShipCustomImpl implements FriendShipCustom {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QFriendShip qFriendShip = QFriendShip.friendShip;


    @Override
    public Optional<FriendShip> findByUsers(Long id1, Long id2) {
        BooleanBuilder builder = new BooleanBuilder();
        //        builder.andAnyOf(qFriendship.friend1.id.eq(id1)
        //                .and(qFriendship.friend2.id.eq(id2)),
        //                qFriendship.friend2.id.eq(id2)
        //                .and(qFriendship.friend2.id.eq(id1)));

        builder.or(
                        builder.and(qFriendShip.friend1.id.eq(id1)).and(qFriendShip.friend2.id.eq(id2))
                )
                .or(
                        builder.and(qFriendShip.friend2.id.eq(id2)).and(qFriendShip.friend2.id.eq(id1))
                );


        //        qFriendship.friend1.id.eq(id1).and(qFriendship.friend2.id.eq(id2).or(qFriendship.friend2.id.eq(id2)
        //                .and(qFriendship.friend2.id.eq(id1));

        return Optional.ofNullable(jpaQueryFactory
                .select(qFriendShip)
                .from(qFriendShip)
                .where(
                        (qFriendShip.friend1.id.eq(id1).and(qFriendShip.friend2.id.eq(id2)))
                                .or(qFriendShip.friend1.id.eq(id2).and(qFriendShip.friend2.id.eq(id1))))
                .fetchOne());
    }

    @Override
    public List<FriendShip> findByOwner(Long id) {
        return jpaQueryFactory.select(qFriendShip).from(qFriendShip).where(qFriendShip.friend1.id.eq(id).and(qFriendShip.allow.eq(false))).fetch();
    }

    @Override
    public List<FriendShip> findByFriend(Long id) {
        return jpaQueryFactory.select(qFriendShip).from(qFriendShip).where(qFriendShip.friend2.id.eq(id).and(qFriendShip.allow.eq(false))).fetch();
    }

    public FriendShip findByFriend1AndFriend2(Long id1, Long id2) {
        return jpaQueryFactory.select(qFriendShip).from(qFriendShip).where(qFriendShip.friend1.id.eq(id1).and(qFriendShip.friend2.id.eq(id2))).fetchOne();
    }

    public List<FriendShip> findByFriendList(Long id1) {
        return jpaQueryFactory.select(qFriendShip).from(qFriendShip).where(qFriendShip.friend2.id.eq(id1).and(qFriendShip.allow.eq(true)).or(qFriendShip.friend1.id.eq(id1).and(qFriendShip.allow.eq(true)))).fetch();
    }
}

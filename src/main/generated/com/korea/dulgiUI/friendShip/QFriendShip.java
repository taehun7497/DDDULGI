package com.korea.dulgiUI.friendShip;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFriendShip is a Querydsl query type for FriendShip
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFriendShip extends EntityPathBase<FriendShip> {

    private static final long serialVersionUID = -1813264480L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFriendShip friendShip = new QFriendShip("friendShip");

    public final BooleanPath allow = createBoolean("allow");

    public final com.korea.dulgiUI.User.QSiteUser friend1;

    public final com.korea.dulgiUI.User.QSiteUser friend2;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QFriendShip(String variable) {
        this(FriendShip.class, forVariable(variable), INITS);
    }

    public QFriendShip(Path<? extends FriendShip> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFriendShip(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFriendShip(PathMetadata metadata, PathInits inits) {
        this(FriendShip.class, metadata, inits);
    }

    public QFriendShip(Class<? extends FriendShip> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.friend1 = inits.isInitialized("friend1") ? new com.korea.dulgiUI.User.QSiteUser(forProperty("friend1"), inits.get("friend1")) : null;
        this.friend2 = inits.isInitialized("friend2") ? new com.korea.dulgiUI.User.QSiteUser(forProperty("friend2"), inits.get("friend2")) : null;
    }

}


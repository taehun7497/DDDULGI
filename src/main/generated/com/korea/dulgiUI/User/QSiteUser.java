package com.korea.dulgiUI.User;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSiteUser is a Querydsl query type for SiteUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSiteUser extends EntityPathBase<SiteUser> {

    private static final long serialVersionUID = 1677332871L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSiteUser siteUser = new QSiteUser("siteUser");

    public final StringPath department = createString("department");

    public final StringPath email = createString("email");

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath mobile = createString("mobile");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath profile_image = createString("profile_image");

    public final com.korea.dulgiUI.calendar.QUserCalendar userCalendar;

    public final StringPath username = createString("username");

    public final StringPath userRole = createString("userRole");

    public QSiteUser(String variable) {
        this(SiteUser.class, forVariable(variable), INITS);
    }

    public QSiteUser(Path<? extends SiteUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSiteUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSiteUser(PathMetadata metadata, PathInits inits) {
        this(SiteUser.class, metadata, inits);
    }

    public QSiteUser(Class<? extends SiteUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userCalendar = inits.isInitialized("userCalendar") ? new com.korea.dulgiUI.calendar.QUserCalendar(forProperty("userCalendar"), inits.get("userCalendar")) : null;
    }

}


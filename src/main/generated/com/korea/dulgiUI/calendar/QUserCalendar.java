package com.korea.dulgiUI.calendar;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserCalendar is a Querydsl query type for UserCalendar
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserCalendar extends EntityPathBase<UserCalendar> {

    private static final long serialVersionUID = -565217781L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserCalendar userCalendar = new QUserCalendar("userCalendar");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final ListPath<com.korea.dulgiUI.Event.Event, com.korea.dulgiUI.Event.QEvent> eventList = this.<com.korea.dulgiUI.Event.Event, com.korea.dulgiUI.Event.QEvent>createList("eventList", com.korea.dulgiUI.Event.Event.class, com.korea.dulgiUI.Event.QEvent.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.korea.dulgiUI.User.QSiteUser siteUser;

    public QUserCalendar(String variable) {
        this(UserCalendar.class, forVariable(variable), INITS);
    }

    public QUserCalendar(Path<? extends UserCalendar> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserCalendar(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserCalendar(PathMetadata metadata, PathInits inits) {
        this(UserCalendar.class, metadata, inits);
    }

    public QUserCalendar(Class<? extends UserCalendar> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.siteUser = inits.isInitialized("siteUser") ? new com.korea.dulgiUI.User.QSiteUser(forProperty("siteUser"), inits.get("siteUser")) : null;
    }

}


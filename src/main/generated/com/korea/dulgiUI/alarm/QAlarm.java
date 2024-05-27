package com.korea.dulgiUI.alarm;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAlarm is a Querydsl query type for Alarm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlarm extends EntityPathBase<Alarm> {

    private static final long serialVersionUID = 528215014L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAlarm alarm = new QAlarm("alarm");

    public final BooleanPath accept = createBoolean("accept");

    public final com.korea.dulgiUI.chat.QChatRoom chatRoom;

    public final StringPath chatroomName = createString("chatroomName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath message = createString("message");

    public final StringPath sender = createString("sender");

    public QAlarm(String variable) {
        this(Alarm.class, forVariable(variable), INITS);
    }

    public QAlarm(Path<? extends Alarm> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAlarm(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAlarm(PathMetadata metadata, PathInits inits) {
        this(Alarm.class, metadata, inits);
    }

    public QAlarm(Class<? extends Alarm> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatRoom = inits.isInitialized("chatRoom") ? new com.korea.dulgiUI.chat.QChatRoom(forProperty("chatRoom")) : null;
    }

}


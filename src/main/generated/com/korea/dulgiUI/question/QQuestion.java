package com.korea.dulgiUI.question;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestion is a Querydsl query type for Question
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestion extends EntityPathBase<Question> {

    private static final long serialVersionUID = -635330784L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestion question = new QQuestion("question");

    public final ListPath<com.korea.dulgiUI.answer.Answer, com.korea.dulgiUI.answer.QAnswer> answerList = this.<com.korea.dulgiUI.answer.Answer, com.korea.dulgiUI.answer.QAnswer>createList("answerList", com.korea.dulgiUI.answer.Answer.class, com.korea.dulgiUI.answer.QAnswer.class, PathInits.DIRECT2);

    public final com.korea.dulgiUI.User.QSiteUser author;

    public final com.korea.dulgiUI.category.QCategory category;

    public final ListPath<com.korea.dulgiUI.comment.Comment, com.korea.dulgiUI.comment.QComment> commentList = this.<com.korea.dulgiUI.comment.Comment, com.korea.dulgiUI.comment.QComment>createList("commentList", com.korea.dulgiUI.comment.Comment.class, com.korea.dulgiUI.comment.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> modifyDate = createDateTime("modifyDate", java.time.LocalDateTime.class);

    public final StringPath subject = createString("subject");

    public final NumberPath<Integer> view = createNumber("view", Integer.class);

    public final SetPath<com.korea.dulgiUI.User.SiteUser, com.korea.dulgiUI.User.QSiteUser> voter = this.<com.korea.dulgiUI.User.SiteUser, com.korea.dulgiUI.User.QSiteUser>createSet("voter", com.korea.dulgiUI.User.SiteUser.class, com.korea.dulgiUI.User.QSiteUser.class, PathInits.DIRECT2);

    public QQuestion(String variable) {
        this(Question.class, forVariable(variable), INITS);
    }

    public QQuestion(Path<? extends Question> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestion(PathMetadata metadata, PathInits inits) {
        this(Question.class, metadata, inits);
    }

    public QQuestion(Class<? extends Question> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new com.korea.dulgiUI.User.QSiteUser(forProperty("author"), inits.get("author")) : null;
        this.category = inits.isInitialized("category") ? new com.korea.dulgiUI.category.QCategory(forProperty("category")) : null;
    }

}


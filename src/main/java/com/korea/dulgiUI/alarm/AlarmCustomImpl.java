package com.korea.dulgiUI.alarm;

import com.korea.dulgiUI.User.SiteUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AlarmCustomImpl implements AlarmCustom {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QAlarm qAlarm = QAlarm.alarm;


    @Override
    public List<Alarm> findByAcceptUser(SiteUser acceptUser) {
        return null;
    }
}
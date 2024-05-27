package com.korea.dulgiUI.alarm;

import com.korea.dulgiUI.User.SiteUser;

import java.util.List;

public interface AlarmCustom {
    List<Alarm> findByAcceptUser(SiteUser acceptUser);
}
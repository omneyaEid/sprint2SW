package com.example.demo.dao;

import com.example.demo.model.NotificationTemplat;

import java.util.List;
import java.util.Optional;

public interface CURD {
    int Create(NotificationTemplat notification);
    List<NotificationTemplat>Read();

    Optional<NotificationTemplat>selectNotificationById(int id);
    int Delete(int id);

    int Update(int id,NotificationTemplat notification);
}

package com.example.demo.service;

import com.example.demo.dao.CURD;
import com.example.demo.dao.CURDIMP;
import com.example.demo.model.NotificationTemplat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private final CURD curd;

    @Autowired
    public NotificationService(@Qualifier("database") CURD curd) {
        this.curd = curd;
    }

    public int Create(NotificationTemplat notification)
    {
        return curd.Create(notification);
    }
    public
    List<NotificationTemplat>Read()
    {
        return curd.Read();
    }
    public Optional<NotificationTemplat> getNotificationById(int id)
    {
        return curd.selectNotificationById(id);
    }
    public int Delete(int id)
    {
        return curd.Delete(id);
    }
    public int Update(int id,NotificationTemplat newNotification)
    {
        return curd.Update(id,newNotification);
    }
}

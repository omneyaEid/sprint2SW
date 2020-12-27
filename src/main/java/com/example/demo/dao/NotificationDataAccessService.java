package com.example.demo.dao;

import com.example.demo.model.NotificationTemplat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("database")
public class NotificationDataAccessService implements CURD{
    public static List<NotificationTemplat> DB=new ArrayList<>();

    DBConnect con = new DBConnect();
    @Override
    public int Create(NotificationTemplat notification) {
        con.add(notification);
        return 1;
    }

    @Override
    public List<NotificationTemplat> Read() {
        return con.selectAll();

    }

    @Override
    public Optional<NotificationTemplat> selectNotificationById(int id) {
       return con.selectNotificationById(id);
    }


    @Override
    public int Delete(int id) {
        return con.Delete(id);
    }
    @Override
    /*public int Update(int id, NotificationTemplat notification) {

        return con.update(id,notification);
    }*/
    public int Update(int id,NotificationTemplat notification) {

        return con.update2(id,notification);
    }
}

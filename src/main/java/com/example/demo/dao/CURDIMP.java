package com.example.demo.dao;

import com.example.demo.model.NotificationTemplat;
//import com.example.demo.model.language;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("fakeDao")
public class CURDIMP implements CURD {
    public static List<NotificationTemplat> DB=new ArrayList<>();
    @Override
    public int Create(NotificationTemplat notification) {
        DB.add(new NotificationTemplat(notification.getId(),notification.getContent()
                ,notification.getType(),notification.getLanguage(),notification.getSubject()));
        return 1;
    }

    @Override
    public List<NotificationTemplat>  Read() {
        return DB;
    }


    @Override
    public Optional<NotificationTemplat> selectNotificationById(int id) {
        return DB.stream().filter(notification -> notification.getId()==(id)).findFirst();
    }


    @Override
    public int Delete(int id) {
        Optional<NotificationTemplat>notificationMaybe=selectNotificationById(id);
        if(notificationMaybe.isEmpty())
        {
           // return 0;
        }
        DB.remove(notificationMaybe.get());
        return 1;
    }

    @Override
    public int Update(int id,NotificationTemplat update) {
        return selectNotificationById(id).map(notification->{
            int indexOfNotificationToUpdate = DB.indexOf(notification);
            if(indexOfNotificationToUpdate>=0)
            {
                DB.set(indexOfNotificationToUpdate,new NotificationTemplat(update.getId(),update.getContent()
                        ,update.getType(),update.getLanguage(),update.getSubject()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }

}

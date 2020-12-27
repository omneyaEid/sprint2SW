package com.example.demo.api;

import com.example.demo.model.NotificationTemplat;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/person")
@RestController
public class NotificationController {
    private final NotificationService notificationservice;

    @Autowired
    public NotificationController(NotificationService notificationservice) {
        this.notificationservice = notificationservice;
    }
    @PostMapping
    void Create(@RequestBody NotificationTemplat notification)
    {
        notificationservice.Create(notification);
    }

    @GetMapping
    public List<NotificationTemplat>Read()
    {
        return notificationservice.Read();
    }

    @GetMapping(path ="{id}")
    public NotificationTemplat getNotificationById(@PathVariable("id")  int id)
    {
        return notificationservice.getNotificationById(id).orElse(null);
    }

    @DeleteMapping(path ="{id}")
    public void Delete(@PathVariable("id") int id)
    {
        notificationservice.Delete(id);
    }

    @PutMapping(path="{id}")
    public void Update(@PathVariable("id") int id,@RequestBody NotificationTemplat NotificationToUpdate)
    {
        notificationservice.Update(id,NotificationToUpdate);
    }
}

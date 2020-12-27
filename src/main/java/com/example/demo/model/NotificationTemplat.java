package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class NotificationTemplat{
    private final int id;
    private final String Content;
    private final String Type;
    private final language Language;
    private final String Subject;


    public NotificationTemplat(@JsonProperty("id")int id,@JsonProperty("Content") String Content,
                               @JsonProperty("Type")String Type,@JsonProperty("Language") language Language,
                               @JsonProperty("Subject")String Subject) {
        this.id = id;
        this.Content = Content;
        this.Type = Type;
        this.Language = Language;
        this.Subject = Subject;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return Content;
    }

    public String getType() {
        return Type;
    }

    public language getLanguage() {
        return Language;
    }

    public String getSubject() {
        return Subject;
    }

}
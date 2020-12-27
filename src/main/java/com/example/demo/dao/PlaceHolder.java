package com.example.demo.dao;

public class PlaceHolder {
    private final int id;
    private final String name;
    private final String book;
    public PlaceHolder(int id, String name, String book) {
        this.id = id;
        this.name = name;
        this.book = book;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBook() {
        return book;
    }

}

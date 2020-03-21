package com.company.brandservice.entity;

public class Message {
    private String title;

    public Message(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Message{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

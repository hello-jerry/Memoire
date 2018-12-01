package com.example.administrator.memoire1;

public class Message {
    private int id;
    private String title;
    private String content;
    private String date;
    public Message() {     }
    public Message(int id, String date,String title, String content) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getDate() {
        return date;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setDate(String date) {
        this.date = date;
    }
}

package com.github.dronezcc.riser.gui.domain;

public class Task{
    int id;
    String title;
    String text;
    String dueTo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDueTo() {
        return dueTo;
    }

    public void setDueTo(String dueTo) {
        this.dueTo = dueTo;
    }
}
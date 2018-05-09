package com.github.dronezcc.riser.gui.module.base.models;

import javax.persistence.*;

@Entity
public class Pages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private
    int id;

    @Column
    private
    String headline;

    @Column
    private
    String path;

    @Column(columnDefinition = "TEXT")
    private
    String content;

    @Column
    private
    String author;

    @Column
    private
    String template;

    public Pages() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



    public String getTemplate() {
        return template;
    }
}

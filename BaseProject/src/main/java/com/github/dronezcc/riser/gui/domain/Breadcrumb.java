package com.github.dronezcc.riser.gui.domain;

public class Breadcrumb {

    private String url;
    private String name;

    public Breadcrumb(final String url, final String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
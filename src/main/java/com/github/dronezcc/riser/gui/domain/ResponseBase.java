package com.github.dronezcc.riser.gui.domain;

public class ResponseBase {

    private String content;
    private String page_name;
    private String path;

    public ResponseBase() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPage_name() {
        return page_name;
    }

    public void setPage_name(String page_name) {
        this.page_name = page_name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ResponseBase{" +
                "content='" + content + '\'' +
                ", page_name='" + page_name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}

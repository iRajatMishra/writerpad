package com.xebia.writerpad.bean;

public class ArticleRequest {

    private String title;
    private String description;
    private String body;
    private String[] tags;

    public ArticleRequest() {
    }

    public ArticleRequest(String title, String description, String body, String[] tags) {
        this.title = title;
        this.description = description;
        this.body = body;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getBody() {
        return body;
    }

    public String[] getTags() {
        return tags;
    }

}

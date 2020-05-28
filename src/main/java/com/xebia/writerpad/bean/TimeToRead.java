package com.xebia.writerpad.bean;

public class TimeToRead {
    private String articleId;
    private Time timeToRead;

    public String getArticleId() {
        return articleId;
    }

    public Time getTimeToRead() {
        return timeToRead;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public void setTimeToRead(Time timeToRead) {
        this.timeToRead = timeToRead;
    }
}

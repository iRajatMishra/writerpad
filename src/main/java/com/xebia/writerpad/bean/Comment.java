package com.xebia.writerpad.bean;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long Id;
    @Column(nullable = false)
    String slug;
    @Column(nullable = false)
    LocalDateTime createdAt;
    @Column(nullable = false)
    LocalDateTime updatedAt;
    @Column(nullable = false)
    String body;
    @Column(nullable = false)
    String ipAddress;

    public Comment(){
        this.ipAddress = ipAddress;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    public Comment(String slug, String body, String ipAddress){
        this.slug = slug;
        this.body = body;
        this.ipAddress = ipAddress;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getSlug() {
        return slug;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getBody() {
        return body;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "slug='" + slug + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", body='" + body + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}

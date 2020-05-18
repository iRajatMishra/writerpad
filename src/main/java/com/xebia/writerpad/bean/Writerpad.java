package com.xebia.writerpad.bean;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "writerpad")
public class Writerpad {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String body;
    @Column(nullable = true)
    private String[] tags;

    public Writerpad() {
    }

    public Writerpad(String title, String description, String body, String[] tags) {
        this.title = title;
        this.description = description;
        this.body = body;
        this.tags = tags;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Writerpad writerpad = (Writerpad) o;
        return Objects.equals(title, writerpad.title);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(title, description, body);
        result = 31 * result + Arrays.hashCode(tags);
        return result;
    }

    @Override
    public String toString() {
        return "Writerpad{" +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", body='" + body + '\'' +
                ", tags=" + Arrays.toString(tags) +
                '}';
    }
}

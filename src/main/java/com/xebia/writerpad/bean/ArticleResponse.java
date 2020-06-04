package com.xebia.writerpad.bean;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "articles")
public class ArticleResponse {

    @Id
    @Column(nullable = false)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String slug;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String body;
    @Column(nullable = true)
    private String[] tags;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    @Column(nullable = false)
    boolean favorite;
    @Column(nullable = false)
    int favoritesCount;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private String authorUsername;

    public ArticleResponse(ArticleRequest articleRequest) {
        this.id = UUID.randomUUID();
        this.title = articleRequest.getTitle();
        this.description = articleRequest.getDescription();
        this.body = articleRequest.getBody();
        this.tags = designTags(articleRequest.getTags());
        this.slug = articleRequest.getTitle().toLowerCase().replace(' ', '-')+id;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.favorite = false;
        this.favoritesCount = 0;
        this.status = "DRAFT";
    }

    public ArticleResponse() {
    }

    private String[] designTags(String[] tags) {
        String[] temp = new String[tags.length];
        int i = 0;
        for (String tag : tags){
            temp[i] = tag.toLowerCase();
            i++;
        }
        return temp;
    }

    public UUID getId(){
        return id;
    }

    public String getSlug() {
        return slug;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(int favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    public String getStatus() {
        return status;
    }

    public void setSlug() {
        this.slug = this.title.toLowerCase().replace(' ', '-')+this.id;
    }

    public void setTitle(String title) {
        this.title = title;
        setSlug();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTags(String[] tags) {
        this.tags = designTags(tags);
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Author getAuthorUsername() {
        Author author = new Author();
        author.setName(authorUsername);
        return author;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleResponse that = (ArticleResponse) o;
        return  Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(slug, title, description, body, createdAt, updatedAt, favorite, favoritesCount);
        result = 31 * result + Arrays.hashCode(tags);
        return result;
    }

}
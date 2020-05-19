package com.xebia.writerpad.bean;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "articles")
public class ArticleResponse {

    @Id
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

    public ArticleResponse(ArticleRequest articleRequest) {
        this.title = articleRequest.getTitle();
        this.description = articleRequest.getDescription();
        this.body = articleRequest.getBody();
        this.tags = designTags(articleRequest.getTags());
        this.slug = articleRequest.getTitle().toLowerCase().replace(' ', '-');
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.favorite = false;
        this.favoritesCount = 0;
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

    public int getFavoritesCount() {
        return favoritesCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleResponse that = (ArticleResponse) o;
        return favorite == Objects.equals(slug, that.slug);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(slug, title, description, body, createdAt, updatedAt, favorite, favoritesCount);
        result = 31 * result + Arrays.hashCode(tags);
        return result;
    }

    @Override
    public String toString() {
        return "ArticleResponse{" +
                "slug='" + slug + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", body='" + body + '\'' +
                ", tags=" + Arrays.toString(tags) +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", favorited=" + favorite +
                ", favoritesCount=" + favoritesCount +
                '}';
    }
}


package com.xebia.writerpad.service;

import com.xebia.writerpad.bean.ArticleRequest;
import com.xebia.writerpad.bean.ArticleResponse;
import com.xebia.writerpad.bean.TimeToRead;

import java.util.List;
import java.util.Map;

public interface BasicWriterpadService {

    public List<ArticleResponse> findAll();
    public ArticleResponse findBySlug(String slug);
    public ArticleResponse save(ArticleRequest articleRequest, String username);
    public String deleteBySlug(String slug, String[] login);
    public ArticleResponse updateBySlug(ArticleRequest articleRequest, String slug, String[] login);
    List<ArticleResponse> findAllByStatus(String status);
    public boolean publish(String slug);
    public TimeToRead getTimeToRead(String slug, int speed);
    public Map<String, Integer> getAllTagOccurrenceCounter();
    public void favoriteUnfavorite(String slug, boolean bool);
}

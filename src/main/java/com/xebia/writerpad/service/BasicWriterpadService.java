package com.xebia.writerpad.service;

import com.xebia.writerpad.bean.ArticleRequest;
import com.xebia.writerpad.bean.ArticleResponse;

import java.util.List;

public interface BasicWriterpadService {

    public List<ArticleResponse> findAll();
    public ArticleResponse findBySlug(String slug);
    public ArticleResponse save(ArticleRequest articleRequest);
    public void deleteBySlug(String slug);
    public ArticleResponse updateBySlug(ArticleRequest articleRequest, String slug);
    List<ArticleResponse> findAllByStatus(String status);
    public boolean publish(String slug);
}

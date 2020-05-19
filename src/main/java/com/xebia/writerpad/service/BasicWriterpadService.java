package com.xebia.writerpad.service;

import com.xebia.writerpad.bean.ArticleRequest;
import com.xebia.writerpad.bean.ArticleResponse;

import java.util.List;

public interface BasicWriterpadService {

    public List<ArticleResponse> findAll();
    public ArticleResponse findById(String slug);
    public ArticleResponse save(ArticleRequest articleRequest);
    public void deleteById(String slug);
    public ArticleResponse updateById(ArticleRequest articleRequest, String slug);

}

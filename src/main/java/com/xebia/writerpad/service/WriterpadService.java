package com.xebia.writerpad.service;

import com.xebia.writerpad.bean.ArticleRequest;
import com.xebia.writerpad.bean.ArticleResponse;
import com.xebia.writerpad.exception.WriterpadNotFoundException;
import com.xebia.writerpad.repository.WriterpadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class WriterpadService implements BasicWriterpadService{

    @Autowired
    private WriterpadRepository writerpadRepository;

    @Override
    public List<ArticleResponse> findAll() {
        PageRequest pageable = PageRequest.of(0,3);
        Page<ArticleResponse> paged = writerpadRepository.findAll(pageable);
        return paged.toList();
    }

    @Override
    public ArticleResponse findBySlug(String slug) {
        return writerpadRepository.findBySlug(slug);
    }

    @Override
    public ArticleResponse save(ArticleRequest articleRequest) {
        ArticleResponse articleResponse = new ArticleResponse(articleRequest);
        return writerpadRepository.save(articleResponse);
    }

    @Override
    public void deleteBySlug(String slug) {
        writerpadRepository.deleteBySlug(slug);
    }

    public ArticleResponse updateBySlug(ArticleRequest articleRequest, String slug){
        ArticleResponse articleResponse = findBySlug(slug);
        deleteBySlug(slug);
        if (articleRequest.getTitle()!=null)
            articleResponse.setTitle(articleRequest.getTitle());
        if (articleRequest.getBody()!=null)
            articleResponse.setBody(articleRequest.getBody());
        if (articleRequest.getDescription()!=null)
            articleResponse.setDescription(articleRequest.getDescription());
        if (articleRequest.getTags()!=null)
            articleResponse.setTags(articleRequest.getTags());
        return writerpadRepository.save(articleResponse);
    }
}

package com.xebia.writerpad.service;

import com.xebia.writerpad.bean.ArticleRequest;
import com.xebia.writerpad.bean.ArticleResponse;
import com.xebia.writerpad.exception.WriterpadNotFoundException;
import com.xebia.writerpad.repository.WriterpadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterpadService implements BasicWriterpadService{

    @Autowired
    private WriterpadRepository writerpadRepository;

    @Override
    public List<ArticleResponse> findAll() {
        return writerpadRepository.findAll();
    }

    @Override
    public ArticleResponse findById(String title) {
        return writerpadRepository.findById(title).orElseThrow(WriterpadNotFoundException:: new);
    }

    @Override
    public ArticleResponse save(ArticleRequest articleRequest) {
        ArticleResponse articleResponse = new ArticleResponse(articleRequest);
        return writerpadRepository.save(articleResponse);
    }
}

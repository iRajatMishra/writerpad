package com.xebia.writerpad.service;

import com.xebia.writerpad.bean.ArticleRequest;
import com.xebia.writerpad.bean.ArticleResponse;
import com.xebia.writerpad.bean.Time;
import com.xebia.writerpad.bean.TimeToRead;
import com.xebia.writerpad.repository.WriterpadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
//        deleteBySlug(slug);
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

    @Override
    public List<ArticleResponse> findAllByStatus(String status) {
        System.out.println("here"+writerpadRepository.findAllByStatus(status)+"here");
        return writerpadRepository.findAllByStatus(status);
    }

    @Override
    public boolean publish(String slug) {
        ArticleResponse articleResponse = findBySlug(slug);
        articleResponse.setStatus("PUBLISHED");
        articleResponse = writerpadRepository.save(articleResponse);
        if (articleResponse!=null)
            return true;
        return false;
    }

    @Override
    public TimeToRead getTimeToRead(String slug, int speed) {
        ArticleResponse articleResponse = findBySlug(slug);
        int numberOfWords = articleResponse.getBody().split(" ").length;
        double roughTime = (double) Math.round((numberOfWords/speed) * 100) / 100;
        System.out.println("roughTime   "+roughTime);
        int sec = (int) (60/((roughTime - ((int)roughTime))*100));
        System.out.println("sec "+sec);
        Time time = new Time();
        time.setMins((int)roughTime);
        time.setSeconds(sec);
        TimeToRead timeToRead = new TimeToRead();
        timeToRead.setArticleId(slug);
        timeToRead.setTimeToRead(time);
        return timeToRead;
    }
}

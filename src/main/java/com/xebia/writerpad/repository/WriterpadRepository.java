package com.xebia.writerpad.repository;

import com.xebia.writerpad.bean.ArticleRequest;
import com.xebia.writerpad.bean.ArticleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WriterpadRepository extends JpaRepository<ArticleResponse, Long> {
    ArticleResponse findBySlug(String slug);
    String deleteBySlug(String slug);
    List<ArticleResponse> findAllByStatus(String status);
}

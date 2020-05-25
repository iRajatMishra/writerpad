package com.xebia.writerpad.repository;

import com.xebia.writerpad.bean.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findBySlug(String slug);
}

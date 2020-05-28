package com.xebia.writerpad.service;

import com.xebia.writerpad.bean.Comment;
import com.xebia.writerpad.exception.CommentNotFoundExcepion;
import com.xebia.writerpad.exception.WriterpadNotFoundException;
import com.xebia.writerpad.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public Comment save(Comment comment, String slug, String ipAddress){
        comment.setSlug(slug);
        comment.setIpAddress(ipAddress);
        return commentRepository.save(comment);
    }

    public List<Comment> findBySlug(String slug){
        return commentRepository.findBySlug(slug);
    }

    public void delete(Long id){
        if (commentRepository.findById(id)==null)
            throw new CommentNotFoundExcepion();
        commentRepository.deleteById(id);
    }

}

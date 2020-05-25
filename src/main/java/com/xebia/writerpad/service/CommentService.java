package com.xebia.writerpad.service;

import com.xebia.writerpad.bean.Comment;
import com.xebia.writerpad.exception.WriterpadNotFoundException;
import com.xebia.writerpad.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public Comment save(Comment comment, String slug, String ipAddress){
        comment.setSlug(slug);
        comment.setIpAddress(ipAddress);
        return commentRepository.save(comment);
    }

    public Comment findById(String slug){
        return commentRepository.findById(slug).orElseThrow(WriterpadNotFoundException:: new);
    }

    public void delete(String slug){
        commentRepository.deleteById(slug);
    }

}

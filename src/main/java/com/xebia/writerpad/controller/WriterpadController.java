package com.xebia.writerpad.controller;

import com.xebia.writerpad.bean.ArticleRequest;
import com.xebia.writerpad.bean.ArticleResponse;
import com.xebia.writerpad.bean.Comment;
import com.xebia.writerpad.service.BasicWriterpadService;
import com.xebia.writerpad.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class WriterpadController {

    @Autowired
    BasicWriterpadService basicWriterpadService;

    @Autowired
    CommentService commentService;

    @RequestMapping("/api/articles")
    public List<ArticleResponse> findAll(){
        return basicWriterpadService.findAll();
    }

    @RequestMapping("/api/articles/{slug}")
    public ArticleResponse findById(@PathVariable String slug){
        return basicWriterpadService.findBySlug(slug);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/api/articles", consumes = "application/json", produces = "application/json")
    public ArticleResponse addWriterpad(@RequestBody ArticleRequest articleRequest){
        return basicWriterpadService.save(articleRequest);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/articles/{slug}")
    public String deleteById(@PathVariable String slug){
        basicWriterpadService.deleteBySlug(slug);
        return "The article has been successfully deleted";
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/api/articles/{slug}")
    public ArticleResponse updateById(@RequestBody ArticleRequest articleRequest,@PathVariable String slug){
        return basicWriterpadService.updateBySlug(articleRequest, slug);
    }

    @PostMapping(path = "/api/articles/{slug}/comments", consumes = "application/json", produces = "application/json")
    public Comment addComment(@RequestBody Comment comment, @PathVariable String slug, HttpServletRequest httpServletRequest){
        return commentService.save(comment, slug, httpServletRequest.getRemoteAddr());
    }

    @RequestMapping("/api/articles/{slug}/comments")
    public List<Comment> findCommentsBySlug(@PathVariable String slug){
        return commentService.findBySlug(slug);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/articles/{slug}/comments/{id}")
    public String deleteCommentsBySlug(@PathVariable String slug, @PathVariable Long id){
        commentService.delete(id);
        return "The comment has been successfully deleted";
    }

}

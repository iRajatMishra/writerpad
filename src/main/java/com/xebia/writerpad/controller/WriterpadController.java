package com.xebia.writerpad.controller;

import com.xebia.writerpad.bean.ArticleRequest;
import com.xebia.writerpad.bean.ArticleResponse;
import com.xebia.writerpad.bean.Comment;
import com.xebia.writerpad.bean.TimeToRead;
import com.xebia.writerpad.exception.CommentNotFoundExcepion;
import com.xebia.writerpad.service.BasicWriterpadService;
import com.xebia.writerpad.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
public class WriterpadController {

    @Autowired
    BasicWriterpadService basicWriterpadService;

    @Autowired
    CommentService commentService;

    @RequestMapping("/api/articles")
    public List<ArticleResponse> findAll(@RequestParam(required = false) String status){
        if (status!=null)
            return basicWriterpadService.findAllByStatus(status);
        return basicWriterpadService.findAll();
    }

    @RequestMapping("/api/articles/{slug}")
    public ArticleResponse findById(@PathVariable String slug){
        return basicWriterpadService.findBySlug(slug);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/api/articles", consumes = "application/json", produces = "application/json")
    public ArticleResponse create(@RequestBody ArticleRequest articleRequest, @RequestHeader(value="Authorization") String authorization){
        String base64Credentials = authorization.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        final String[] values = credentials.split(":", 2);
        return basicWriterpadService.save(articleRequest, values[0]);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/articles/{slug}")
    public ResponseEntity<?> deleteById(@PathVariable String slug, @RequestHeader(value="Authorization") String authorization){
        ResponseEntity<?> response;
        String base64Credentials = authorization.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        final String[] values = credentials.split(":", 2);
        String result = basicWriterpadService.deleteBySlug(slug, values);
        if(result==null)
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        else
            response = ResponseEntity.ok().body(result);
        return response;
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/api/articles/{slug}")
    public ResponseEntity<?> updateById(@RequestBody ArticleRequest articleRequest,@PathVariable String slug, @RequestHeader(value="Authorization") String authorization){
        ResponseEntity<?> response;
        String base64Credentials = authorization.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        final String[] values = credentials.split(":", 2);
        ArticleResponse articleResponse= basicWriterpadService.updateBySlug(articleRequest, slug, values);
        if(articleResponse==null)
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        else
            response = ResponseEntity.ok().body(articleResponse);
        return response;
    }

    @PostMapping(path = "/api/articles/{slug}/comments", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addComment(@RequestBody Comment comment, @PathVariable String slug, HttpServletRequest httpServletRequest){
        ResponseEntity<?> response;
        if (comment.getBody()==null)
            response = ResponseEntity.badRequest().build();
        else
            response = ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(comment, slug, httpServletRequest.getRemoteAddr()));
        return response;
    }

    @RequestMapping("/api/articles/{slug}/comments")
    public List<Comment> findCommentsBySlug(@PathVariable String slug){
        return commentService.findBySlug(slug);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/articles/{slug}/comments/{id}")
    public ResponseEntity<?> deleteCommentsBySlug(@PathVariable String slug, @PathVariable Long id){
        ResponseEntity<?> response;
        try {
            commentService.delete(id);
            response = ResponseEntity.noContent().build();
        }
        catch(CommentNotFoundExcepion e){
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @PostMapping(path = "/api/articles/{slug}/PUBLISH")
    public void publish(@PathVariable String slug){
        boolean responseCode = basicWriterpadService.publish(slug);
    }

    @GetMapping(path = "/api/articles/{slug}/timetoread")
    public TimeToRead getTimeToRead(@PathVariable String slug){
        return basicWriterpadService.getTimeToRead(slug, 3);
    }

    @GetMapping(path = "/api/articles/tags")
    public Map<String, Integer> getAllTagOccurrenceCounter(){
        return basicWriterpadService.getAllTagOccurrenceCounter();
    }

    @RequestMapping("/api/articles/{slug}/favorite")
    public ResponseEntity<?> findAll(@PathVariable String slug, @RequestParam boolean status){
        basicWriterpadService.favoriteUnfavorite(slug, status);
        return ResponseEntity.ok().build();
    }

}

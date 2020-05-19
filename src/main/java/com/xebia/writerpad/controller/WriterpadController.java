package com.xebia.writerpad.controller;

import com.xebia.writerpad.bean.ArticleRequest;
import com.xebia.writerpad.bean.ArticleResponse;
import com.xebia.writerpad.service.BasicWriterpadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WriterpadController {

    @Autowired
    BasicWriterpadService basicWriterpadService;

    @RequestMapping("/api/articles")
    public List<ArticleResponse> findAll(){
        return basicWriterpadService.findAll();
    }

    @RequestMapping("/api/articles/{slug}")
    public ArticleResponse findById(@PathVariable String slug){
        return basicWriterpadService.findById(slug);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/api/articles", consumes = "application/json", produces = "application/json")
    public ArticleResponse addWriterpad(@RequestBody ArticleRequest articleRequest){
        return basicWriterpadService.save(articleRequest);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/articles/{slug}")
    public String deleteById(@PathVariable String slug){
        basicWriterpadService.deleteById(slug);
        return "The article has been successfully deleted";
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/api/articles/{slug}")
    public ArticleResponse updateById(@RequestBody ArticleRequest articleRequest,@PathVariable String slug){
        return basicWriterpadService.updateById(articleRequest, slug);
    }

}

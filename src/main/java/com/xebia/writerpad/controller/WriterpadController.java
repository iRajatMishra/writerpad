package com.xebia.writerpad.controller;

import com.xebia.writerpad.bean.ArticleRequest;
import com.xebia.writerpad.bean.ArticleResponse;
import com.xebia.writerpad.service.BasicWriterpadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WriterpadController {

    @Autowired
    BasicWriterpadService basicWriterpadService;

    @RequestMapping("/all")
    public List<ArticleResponse> findAll(){
        return basicWriterpadService.findAll();
    }

    @RequestMapping("/{title}")
    public ArticleResponse findById(@PathVariable String title){
        return basicWriterpadService.findById(title);
    }

    @PostMapping(path = "/post", consumes = "application/json", produces = "application/json")
    public ArticleResponse addWriterpad(@RequestBody ArticleRequest articleRequest){
        return basicWriterpadService.save(articleRequest);
    }

}

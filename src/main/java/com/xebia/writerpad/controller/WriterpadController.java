package com.xebia.writerpad.controller;

import com.xebia.writerpad.bean.Writerpad;
import com.xebia.writerpad.service.BasicWriterpadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WriterpadController {

    @Autowired
    BasicWriterpadService basicWriterpadService;

    @RequestMapping("/all")
    public List<Writerpad> findAll(){
        return basicWriterpadService.findAll();
    }

    @RequestMapping("/{title}")
    public Writerpad findById(@PathVariable String title){
        return basicWriterpadService.findById(title);
    }

    @PostMapping(path = "/post", consumes = "application/json", produces = "application/json")
    public Writerpad addWriterpad(@RequestBody Writerpad writerpad){
        return basicWriterpadService.save(writerpad);
    }

}

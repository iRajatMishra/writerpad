package com.xebia.writerpad.service;

import com.xebia.writerpad.bean.Writerpad;

import java.util.List;

public interface BasicWriterpadService {

    public List<Writerpad> findAll();
    public Writerpad findById(String title);
    public Writerpad save(Writerpad writerpad);

}

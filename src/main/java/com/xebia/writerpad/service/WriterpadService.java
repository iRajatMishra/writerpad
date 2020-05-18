package com.xebia.writerpad.service;

import com.xebia.writerpad.bean.Writerpad;
import com.xebia.writerpad.exception.WriterpadNotFoundException;
import com.xebia.writerpad.repository.WriterpadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterpadService implements BasicWriterpadService{

    @Autowired
    private WriterpadRepository writerpadRepository;

    @Override
    public List<Writerpad> findAll() {
        return writerpadRepository.findAll();
    }

    @Override
    public Writerpad findById(String title) {
        return writerpadRepository.findById(title).orElseThrow(WriterpadNotFoundException:: new);
    }

    @Override
    public Writerpad save(Writerpad writerpad) {
        return writerpadRepository.save(writerpad);
    }
}

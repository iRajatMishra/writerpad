package com.xebia.writerpad.repository;

import com.xebia.writerpad.bean.Writerpad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterpadRepository extends JpaRepository<Writerpad, String> {

}

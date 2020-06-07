package com.xebia.writerpad.repository;

import com.xebia.writerpad.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}

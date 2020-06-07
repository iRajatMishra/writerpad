package com.xebia.writerpad.service;

import com.xebia.writerpad.bean.User;
import com.xebia.writerpad.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    private ProfileRepository profileRepository;

    public User findByUsername(String username){
        return profileRepository.findByUsername(username);
    }

    public User follow(String username, String currentUser){
        User user = profileRepository.findByUsername(username);
        user.setFollowersCount(user.getFollowersCount()+1);
        ArrayList<String> followers = user.getFollowers();
        followers.add(currentUser);
        user.setFollowers(followers);
        return user;
    }

    public User unfollow(String username, String currentUser){
        User user = profileRepository.findByUsername(username);
        user.setFollowersCount(user.getFollowersCount()-1);
        ArrayList<String> followers = user.getFollowers();
        followers.remove(currentUser);
        user.setFollowers(followers);
        return user;
    }

}

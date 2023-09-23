package com.example.examprep4.service;

import com.example.examprep4.model.entity.Song;
import com.example.examprep4.model.service.UserServiceModel;

import java.util.Set;

public interface UserService {
    void register(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);


    Set<Song> getUsersPlayList(UserServiceModel userServiceModel);
}
